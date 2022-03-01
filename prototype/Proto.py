from tkinter import *
def change():
	global canv, img
	compteur = 0
	canv =Canvas(fen, bg="Black" , height=500, width=500 )
	canv.place(x=0, y=0)
	img = canv.create_image(250,250, image=Fichimg)
	fen.bind("<Left>", lambda e, x=-50, y=0 : bougeimg(e,x,y, compteur))
	fen.bind("<Right>", lambda e, x=50, y=0 : bougeimg(e,x,y,compteur))
	fen.bind("<Up>", lambda e, x=0, y=-50 : bougeimg(e,x,y,compteur))
	fen.bind("<Down>", lambda e, x=0, y=50 : bougeimg(e,x,y,compteur))
	rect = canv.create_rectangle(125,125,175,275, fill="Blue")
	T = Label(fen, text="Nombre de mort:")
	h =Label(fen , text = compteur)
	h.place(x = 455, y =50)
	T.place(x = 350 , y = 50)

def mort(e,x,y):
	global canv, img
	canv.coords(img,300,200)

def bougeimg(e,x,y, compteur):
	global canv, img 
	if x >=125 and x <=175 and y >=175 and y <= 275:
		mort(e,x, y)
		compteur=+1
	canv.move(img, x, y)	


fen  =Tk()
fen.geometry("500x500")
fen.title('Baba is You')
L = Label(fen, text="Baba is You")
L.place(x = 200, y=50)
B1 = Button(fen, text="Play", command= change)
B1.place(x= 200, y=200)
fen.resizable(width=False, height = False)	
Fichimg= PhotoImage(file="baba.gif")
fen.mainloop()
