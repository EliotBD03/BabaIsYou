

class Environment(list):

    def __init__(self):
    #on initialise la carte du jeu
        self.environment = [['x','x','x','x','x','x','x','x','x','x'],
                            ['x',' ',' ',' ',' ',' ',' ',' ',' ','x'],
                            ['x',' ',' ',' ',' ',' ',' ',' ',' ','x'],
                            ['x',' ',' ',' ',' ',' ',' ',' ',' ','x'],
                            ['x',' ',' ',' ',' ',' ',' ',' ',' ','x'],
                            ['x',' ',' ',' ',' ',' ',' ',' ',' ','x'],
                            ['x',' ',' ',' ',' ',' ',' ',' ',' ','x'],
                            ['x',' ',' ',' ',' ',' ',' ',' ',' ','x'],
                            ['x',' ',' ',' ',' ',' ',' ',' ',' ','x'],
                            ['x',' ',' ',' ',' ',' ',' ',' ',' ','x'],
                            ['x',' ',' ',' ',' ',' ',' ',' ',' ','x'],
                            ['x','x','x','x','x','x','x','x','x','x']]
    def __str__(self):
    #on peut afficher la carte    
        res = ''
        for line in self.environment:
            res += ' '.join(line) + '\n'
        return res    

class Wall(Environment):

    def __init__(self):
        self.wall = 'x'

    def stop(self,position1,position2):
    #délimiter la carte    
        if not(Environment().environment[position1][position2] == self.wall):
            return True
        else:
            return False    


class Player(Wall):

    def __init__(self):
    #coordonnés de notre joueur + toutes les positions possibles après mouvement
        self.x = (len(Environment().environment[0]) - 1) // 2
        self.y = (len(Environment().environment) - 1) // 2
        self.xleft = self.x - 1
        self.xright = self.x + 1
        self.yup = self.y - 1
        self.ydown = self.y + 1

    def move(self, direction):
    #ce qui permet de faire bouger le joueur
        if direction == 'z' and Wall().stop(self.yup, self.x):
            return self.x ,self.y - 1 
        elif direction == 's' and Wall().stop(self.yup,self.x):
            return self.x ,self.y + 1
        elif direction == 'q' and Wall().stop(self.y,self.xleft):
            return self.x - 1 ,self.y
        elif direction == 'd' and Wall().stop(self.y,self.xright):
            return self.x + 1 , self.y    




if __name__ == "__main__":
    player = Player()
    player_skin = 'O'
    goal = '@'
    while True:
        temp = Environment()
        temp.environment[player.y][player.x]= player_skin
        print(temp)
        intp = str(input("z : up q : left s : down d : right"))
        player.x , player.y = player.move(intp)


    """
        self.environment = []
        length = 10
        topandbottom = ['x','x','x','x','x','x','x','x','x','x']
        width = ['x',' ',' ',' ',' ',' ',' ',' ',' ','x']
        self.environment.append(topandbottom)
        while length >= 0:
            self.environment.append(width)
            length -= 1 
        self.environment.append(topandbottom)
    """