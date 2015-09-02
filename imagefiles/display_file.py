from Tkinter import *
def displayImage(name):
	f = open(name)
	bm = []
	window = Tk()
	for line in f:
		tokens = line.strip(' \n').split()
		temp = []
		for t in tokens:
			if(t=='1'):
				temp.append(1)
			else:
				temp.append(0)
		bm.append(temp)
	width = len(bm[0])
	height = len(bm)
	img = PhotoImage(width=width, height=height)
	canvas = Canvas(window, width=width, height=height, bg="#FFFFFF")
	canvas.pack()
	canvas.create_image((width/2, height/2), image=img, state="normal")
	for r in range(len(bm)):
		for c in range(len(bm[r])):
			if(bm[r][c]==1):
				img.put("#000000", (c,r))
			else:
				img.put("#FFFFFF", (c,r))
	mainloop()