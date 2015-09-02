package edu.cmu.chen;

public class CollisionDetector {
    
    double DECAY_FACTOR = 0.7;
    
    /**
     * @param ball - Instance of Ball
     * @param image - 2D array of Bitmap, where 1 represents a point, and 0 otherwise
     * @param outsideRadius - Together with Ball.radius defines size of the region to detect collision
     */
    public void collide(Ball ball, int image[][], int outsideRadius) {
        int totalRadius = ball.radius + outsideRadius;
        int nearestCollisionX = -1, nearestCollisionY = -1;
        int nearestCollisionDist = -1;
        
        if (ball.pos.x <= 5 || ball.pos.x >= image[0].length - 5) {
            ball.v.vX = -ball.v.vX;
            return;
        } else if (ball.pos.y <= 5 || ball.pos.y >= image.length) {
            ball.v.vY = -ball.v.vY;
            return;
        }
        
        for (int deltaX = -totalRadius; deltaX <= totalRadius; deltaX++) {
            for (int deltaY = -totalRadius; deltaY <= totalRadius; deltaY++) {
                int newX = (int) (ball.pos.x + deltaX), newY = (int) (ball.pos.y + deltaY);
                if (0 <= newX && newX < image[0].length &&
                        0 <= newY && newY < image.length) {
                    /* If within bounds */
                    /* If this is a point, and radius <= totalRadius */
                    double dist = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
                    if (image[newY][newX] == 1 && dist <= totalRadius) {
                        if (nearestCollisionDist == -1 || dist <= nearestCollisionDist) {
                            nearestCollisionDist = (int) dist;
                            nearestCollisionX = newX;
                            nearestCollisionY = newY;
                        }
                    }
                }
            }
        }

        /* If the angle between velocity vector and colliding point - center point vector > 90 degrees, ignore */
        int collisionVecX = nearestCollisionX - (int) ball.pos.x;
        int collisionVecY = nearestCollisionY - (int) ball.pos.y;

        double dotProduct = collisionVecX * ball.v.vX + collisionVecY * ball.v.vY;
        if (dotProduct <= 0)
            return;

        /* Reflect velocity vector with respect to collision point - ball center line and reverse direction */
        if (nearestCollisionX != -1 && nearestCollisionY != -1) {
            double lineSlopeX = nearestCollisionX - ball.pos.x;
            double lineSlopeY = nearestCollisionY - ball.pos.y;
            double vX = ball.v.vX, vY = ball.v.vY;
            double reflectionCoeff = 2 * (vX * lineSlopeX + vY * lineSlopeY) / (lineSlopeX * lineSlopeX + lineSlopeY * lineSlopeY);
            double newVelocityX = reflectionCoeff * lineSlopeX - vX;
            double newVelocityY = reflectionCoeff * lineSlopeY - vY;
            
            /* Reverse velocity */
            newVelocityX = -newVelocityX;
            newVelocityY = -newVelocityY;
    
            ball.v.vX = newVelocityX * DECAY_FACTOR;
            ball.v.vY = newVelocityY * DECAY_FACTOR;
        }
    }

    /**
     * Move ball to a new position defined by velocity and time step
     * @param ball
     * @param timeStep
     */
    public void step(Ball ball, double timeStep) {
        /**
         * Initialize/get accelerometer and get acceleration 
         */
        double aX = 0, aY = 0;
        Velocity v = ball.v;
        ball.pos.x += timeStep * v.vX;
        ball.pos.y += timeStep * v.vY;
        ball.v.vX += timeStep * aX;
        ball.v.vY += timeStep * aY;
    }
}
