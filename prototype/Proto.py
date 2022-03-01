from tkinter import *
def change():
	global canv, img
	canv =Canvas(fen, bg="Black" , height=500, width=500 )
	canv.place(x=0, y=0)
	img = canv.create_image(250,250, image=Fichimg)
	fen.bind("<Left>", lambda e, x=-10, y=0 : bougeimg(e,x,y))
	fen.bind("<Right>", lambda e, x=10, y=0 : bougeimg(e,x,y))
	fen.bind("<Up>", lambda e, x=0, y=-10 : bougeimg(e,x,y))
	fen.bind("<Down>", lambda e, x=0, y=10 : bougeimg(e,x,y))

def bougeimg(e,x,y):
   global canv, img 
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
