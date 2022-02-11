# -*- encoding:utf-8 -*-

import random
import copy

carte = ["x","x","x","x","x","x","x","x","x","x"
        ,"x"," "," "," "," "," "," "," "," ","x"
        ,"x"," "," "," "," "," "," "," "," ","x"
        ,"x"," "," "," "," "," "," "," "," ","x"
        ,"x"," "," "," "," "," "," "," "," ","x"
        ,"x"," "," "," "," "," "," "," "," ","x"
        ,"x"," "," "," "," "," "," "," "," ","x"
        ,"x"," "," "," "," "," "," "," "," ","x"
        ,"x"," "," "," "," "," "," "," "," ","x"
        ,"x","x","x","x","x","x","x","x","x","x"]

def placement(entre,mid,carte):
    if mouvement == 'z' and avancer(mid) > 10:
        return avancer(mid)
    elif mouvement == 's' and reculer(mid) < 90:
        return reculer(mid)
    elif mouvement == 'q' and gauche(mid)  % 10 != 0:
        return gauche(mid)
    elif mouvement == 'd' and (droite(mid) -9) % 10 != 0:
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
    mid = 54
    goalplace = random.randint(0,len(carte))
    temp = copy.copy(carte)
    while True:
        try:
            temp[mid] = player
            temp[goalplace] = goal
            print(listetostr(temp))
            mouvement = input("\n z:avancer s:reculer q:aller à gauche d:aller à droite ")
            mid = placement(mouvement,mid,temp)
            temp = copy.copy(carte)
            if mid == goalplace:
                print("bravo !")  
                print(listetostr(temp))
                break    
            print("\t" *  1000)
            
        except ValueError:
            print("Commande non valide")

