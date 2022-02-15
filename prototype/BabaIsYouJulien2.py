

class Environment(list):

    def __init__(self):
    #on initialise la carte du jeu
        self.environment = [['x','x','x','x','x','x','x','x','x','x'],
                            ['x',' ',' ',' ',' ',' ',' ',' ',' ','x'],
                            ['x',' ',' ',' ',' ',' ',' ',' ',' ','x'],
                            ['x',' ',' ',' ',' ',' ',' ',' ',' ','x'],
                            ['x',' ',' ',' ',' ',' ',' ',' ',' ','x'],
                            ['x',' ',' ','@',' ',' ',' ',' ',' ','x'],
                            ['x',' ',' ',' ',' ',' ',' ',' ',' ','x'],
                            ['x',' ',' ',' ',' ',' ',' ',' ',' ','x'],
                            ['x',' ',' ',' ',' ',' ',' ',' ',' ','x'],
                            ['x',' ',' ',' ',' ',' ',' ',' ',' ','x'],
                            ['x',' ',' ',' ',' ',' ',' ',' ',' ','x'],
                            ['x','x','x','x','x','x','x','x','x','x']]
    def __str__(self):
    #ce qui nous permet d'afficher la carte    
        res = ''
        for line in self.environment:
            res += ' '.join(line) + '\n'
        return res    

class Wall(object):

    def __init__(self):
    #initialisation de notre mur
        self.wall = 'x'

    def isstopped(self,position1,position2):
    #délimiter la carte    
        if Environment().environment[position1][position2] == self.wall:
            return False
        return True 

class Goal(object):

    def __init__(self):
    #initialisation de notre objectif
        self.goal = '@'  

    def isreached(self, position1, position2):
    #nous permet de savoir si on a atteint l'objectif
        if Environment().environment[position1][position2] == self.goal:
            return True
        return False              


class Player(object):

    def __init__(self):
    #coordonnés de notre joueur (par défaut : milieu de la carte)
        self.x = (len(Environment().environment[0]) - 1) // 2
        self.y = (len(Environment().environment) - 1) // 2
    def move(self, direction):
    #ce qui permet de faire bouger le joue et de savoir si on a atteint l'objectif
        if direction == 'z' and Wall().isstopped(self.y - 1, self.x):

            if Goal().isreached(self.y - 1, self.x):
                print("You have reached the goal. You WON !")
                return True
             
            return self.y -1 ,self.x 
        elif direction == 's' and Wall().isstopped(self.y + 1,self.x):
            
            if Goal().isreached(self.y + 1, self.x):
                print("You have reached the goal. You WON !")
                return True
            
            return self.y + 1, self.x
        elif direction == 'q' and Wall().isstopped(self.y,self.x - 1):
            
            if Goal().isreached(self.y, self.x - 1):
                print("You have reached the goal. You WON !")
                return True
        
            return self.y, self.x - 1
        elif direction == 'd' and Wall().isstopped(self.y,self.x + 1):
            
            if Goal().isreached(self.y, self.x + 1):
                print("You have reached the goal. You WON !")
                return True    
            
            return self.y, self.x + 1    
        else:
            return self.y , self.x
    """
    on évalue la position relative du joueur après mouvement pour voir si le joueur
    ne retrouvera pas dans un mur
    """  




if __name__ == "__main__":
    player = Player()
    player_skin = 'O'
    goal = '@'
    while True:
        temp = Environment()
        temp.environment[player.y][player.x]= player_skin
        print(temp)
        intp = str(input("z : up q : left s : down d : right "))

        if type(player.move(intp)) == tuple:
            player.y , player.x = player.move(intp)
        else:
            break    
        print("\t" *  1000)


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
