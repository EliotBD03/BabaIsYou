# -*- encoding:utf-8 -*-

import random
import copy

carte = ["x","x","x","x","x","x","x","x","x","x"
        ,"x"," "," "," "," "," "," "," "," ","x"
        ,"x"," "," ","&"," "," "," "," "," ","x"
        ,"x"," ","@","&"," "," "," "," "," ","x"
        ,"x","&","&","&"," "," ","W"," "," ","x"
        ,"x"," "," "," "," "," "," "," "," ","x"
        ,"x"," "," "," "," "," "," "," "," ","x"
        ,"x"," "," "," "," "," "," "," "," ","x"
        ,"x"," "," "," "," "," "," "," "," ","x"
        ,"x","x","x","x","x","x","x","x","x","x"]

def placement(entre,mid,carte):
    if mouvement == 'z' and avancer(mid) > 10 and carte[avancer(mid)] != wall :
        return avancer(mid)
    elif mouvement == 's' and reculer(mid) < 90 and carte[reculer(mid)] != wall:
        return reculer(mid)
    elif mouvement == 'q' and gauche(mid)  % 10 != 0 and carte[gauche(mid)] != wall:
        return gauche(mid)
    elif mouvement == 'd' and (droite(mid) -9) % 10 != 0 and carte[droite(mid)] != wall:
        return droite(mid)
    else:
        raise ValueError
 
def avancer(mid):
    return mid - 10

def reculer(mid):
    return mid + 10

def gauche(mid):
    return mid - 1

def droite(mid):
    return mid + 1           
    
def listetostr(carte):
    res = ''
    indice = 0
    for elem in carte:
        res += elem
        indice += 1 
        if indice % 10 == 0:
            res += '\n'
    return res 

if __name__ == "__main__":
    player = 'O'
    goal = '@'
    wall = '&'
    mid = 54
    temp = copy.copy(carte)
    while True:
        try:
            temp[mid] = player
            print(listetostr(temp) + "\nWallIsStop(&)\nFlagIsWin(@)")
            mouvement = input("\n Z:Avancer S:Reculer Q:Gauche D:Droite ")
            mid = placement(mouvement,mid,temp)
            temp = copy.copy(carte)
            if temp[mid] == goal:
                print("Bravo, vous avez capturé le drapeau !")  
                print(listetostr(temp))
                break    
            print("\t" *  1000)
            
        except ValueError:
            print("Commande non valide ou bloqué par un mur")

