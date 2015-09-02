package hackathon;

public class GetColor {

	private int getTransparent(int color){
		return color & 0xFF000000;
	}
	private int getRed(int color){
		return color & 0x00FF0000;
	}

	private int getGreen(int color){
		return color & 0x0000FF00;
	}

	private int getBlue(int color){
		return color & 0x000000FF;
	}

}
