import turtle
import random

# Game Window
wn = turtle.Screen()
wn.title("BabaIsYou")
wn.bgcolor("Black")
wn.setup(width = 800, height = 600)
wn.tracer(0)

# Baba Config
Baba = turtle.Turtle()
Baba.speed(0)
Baba.shape("square")
Baba.shapesize(stretch_wid = 1, stretch_len = 1)
Baba.color("White")
Baba.penup()
Baba.goto(0, 0)

# Functions

def randomWallCoordinate(wallPosition):
	horizontalLimit = 266
	verticalLimit = 200
	if wallPosition == "h" or wallPosition == "horizontal":
		Limit = horizontalLimit
	elif wallPosition == "v" or wallPosition == "vertical":
		Limit = verticalLimit
	randomWallCoordinate = random.randrange(-Limit, Limit )
	if randomWallCoordinate < Limit // 4 and randomWallCoordinate > Limit * (-1) // 4:
		randomWallCoordinate(wallPosition)
	return randomWallCoordinate

def baba_move_up():
	y = Baba.ycor()
	y += 20
	Baba.sety(y)
def baba_move_down():
	y = Baba.ycor()
	y -= 20
	Baba.sety(y)
def baba_move_left():
	x = Baba.xcor()
	x -= 20
	Baba.setx(x)
def baba_move_right():
	x = Baba.xcor()
	x += 20
	Baba.setx(x)

# Map Config

## Map Borders/Walls

### Left Border
leftWall = turtle.Turtle()
leftWall.penup()
leftWall.shape("square")
leftWall.color("green")
leftWall.goto(-395, 0)
leftWall.shapesize(stretch_wid = 30, stretch_len = 1)

### Right Border
rightWall = turtle.Turtle()
rightWall.penup()
rightWall.shape("square")
rightWall.color("green")
rightWall.goto(388, 0)
rightWall.shapesize(stretch_wid = 30, stretch_len = 1)

### Top Border
topWall = turtle.Turtle()
topWall.penup()
topWall.shape("square")
topWall.color("green")
topWall.goto(0, 295)
topWall.shapesize(stretch_wid = 1, stretch_len = 45)

### Bottom Border
bottomWall = turtle.Turtle()
bottomWall.penup()
bottomWall.shape("square")
bottomWall.color("green")
bottomWall.goto(0, -290)
bottomWall.shapesize(stretch_wid = 1, stretch_len = 45)

## Obstacles
randomWall1 = turtle.Turtle()
randomWall1Coordinate = randomWallCoordinate("h")
randomWall1.penup()
randomWall1.shape("square")
randomWall1.color("blue")
randomWall1.goto(randomWall1Coordinate, 0)
randomWall1.shapesize(stretch_wid = 10, stretch_len = 2)

# Flag
flag = turtle.Turtle()
flag.color("yellow")
flag.shape("square")
flag.penup()
flag.goto(150, 250)

# Rule
rule1 = turtle.Turtle()
rule1.speed(0)
rule1.color("white")
rule1.penup()
rule1.hideturtle()
rule1.goto(0, 240)
rule1.write("River Is Die", align="center", font=("Sans comic", 12, "normal"))

rule2 = turtle.Turtle()
rule2.speed(0)
rule2.color("white")
rule2.penup()
rule2.hideturtle()
rule2.goto(0, -240)
rule2.write("Gold Is Win", align="center", font=("Sans comic", 12, "normal"))


# Baba movement
wn.listen()
wn.onkeypress(baba_move_up, "z")
wn.onkeypress(baba_move_down, "s")
wn.onkeypress(baba_move_left, "q")
wn.onkeypress(baba_move_right, "d")
wn.onkeypress(baba_move_up, "Up")
wn.onkeypress(baba_move_down, "Down")
wn.onkeypress(baba_move_left, "Left")
wn.onkeypress(baba_move_right, "Right")

while True:
	wn.update()

	if Baba.xcor() <= 160 and Baba.xcor() >= 140 and Baba.ycor() <= 260 and Baba.ycor() >= 240:
		turtle.bye()

	elif Baba.xcor() <= randomWall1Coordinate + 15 and Baba.xcor() >= randomWall1Coordinate - 15 and Baba.ycor() <= 100 and Baba.ycor() >= -100:
		Baba.goto(0,0)
	elif Baba.xcor() <= -379:
		Baba.setx(-378)
	elif Baba.xcor() >= 370:
		Baba.setx(369)
	elif Baba.ycor() >= 280:
		Baba.sety(279)
	elif Baba.ycor() <= -270:
		Baba.sety(-269)
