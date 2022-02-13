import random
import copy

def generate_level(length, height):
    generated_level = []
    bordures_NS = generate_bordures(length, height, generated_level)
    bordures_OE = generate_corps(length, height, generated_level)
    generated_level.append(bordures_NS)
    for i in range(height - 1):
        generated_level.append(copy.copy(bordures_OE))
    generated_level.append(bordures_NS)
    obstacles_data = generate_obstacles(length, height, generated_level)
    objectives_data = generate_objective(length, height, generated_level)
    player_data = generate_player(length, height, generated_level)
    objective_height = objectives_data[1]
    objective_length = objectives_data[2]
    player_height = player_data[1]
    player_length = player_data[2]
    return generated_level, height, length, objective_height, objective_length, player_height, player_length

def generate_bordures(length, height, generated_level):
    bordure_NS = []
    for i in range(length):
        bordure_NS.append("x")
    return bordure_NS

def generate_corps(length, height, generated_level):
    bordure_OE = ["x"]
    for i in range(length - 1):
        if len(bordure_OE) == length - 1:
            bordure_OE.append("x")
        else:
            bordure_OE.append(" ")
    return bordure_OE


def print_level(level):
    for j in level:
        line = ""
        for i in j:
            line += i
        print(line)

def generate_obstacles(length, height, generated_level):
    for i in range(1, height):
        for j in range(1, length - 1):
            random_number = random.randrange(2, 25)
            if random_number >= 23:
                generated_level[i][j] = "&"
    return generated_level

def generate_objective(length, height, generated_level):
    random_objective_height = random.randrange(1, height - 1)
    random_objective_length = random.randrange(1, length - 1)
    if generated_level[random_objective_height][random_objective_length] != " ":
        return generate_objective(length, height, generated_level)
    generated_level[random_objective_height][random_objective_length] = "@"
    test_message = "Objective Coordinates : (" + str(random_objective_height) + ", " + str(random_objective_length) + ")"
    print(test_message)
    return generated_level, random_objective_height, random_objective_length

def generate_player(length, height, generated_level):
    random_player_height = random.randrange(1, height - 1)
    random_player_length = random.randrange(1, length - 1)
    if generated_level[random_player_height][random_player_length] != " ":
        return generate_player(length, height, generated_level)
    generated_level[random_player_height][random_player_length] = "O"
    test_message = "Player Coordinates : (" + str(random_player_height) + ", " + str(random_player_length) + ")"
    print(test_message)
    return generated_level, random_player_height, random_player_length

def play(generated_level, height, length, objective_height, objective_length, player_height, player_length, game_state):
    move = input("Q: Gauche D: Droite Z: Avancer S: Reculer")
    if move == "q" or move == "Q":
        if player_length >= 2 and (generated_level[player_height][player_length - 1] == ' ' or generated_level[player_height][player_length - 1] == '@'):
            generated_level[player_height][player_length] = ' '
            player_length -= 1
            if generated_level[player_height][player_length] == '@':
                generated_level[player_height][player_length] = 'O'
                print("Well played, you won the game!")
                game_state = "Stop"
            generated_level[player_height][player_length] = 'O'
    if move == "d" or move == "D":
        if player_length <= length - 2  and (generated_level[player_height][player_length + 1] == ' ' or generated_level[player_height][player_length + 1] == '@'):
            generated_level[player_height][player_length] = ' '
            player_length += 1
            if generated_level[player_height][player_length] == '@':
                generated_level[player_height][player_length] = 'O'
                print("Well played, you won the game!")
                game_state = "Stop"
            generated_level[player_height][player_length] = 'O'
    if move == "z" or move == "Z":
        if player_height >= 2 and (generated_level[player_height - 1][player_length] == ' ' or generated_level[player_height - 1][player_length] == '@'):
            generated_level[player_height][player_length] = ' '
            player_height -= 1
            if generated_level[player_height][player_length] == '@':
                generated_level[player_height][player_length] = 'O'
                print("Well played, you won the game!")
                game_state = "Stop"
            generated_level[player_height][player_length] = 'O'
    if move == "s" or move == "S":
        if player_height <= height - 2 and (generated_level[player_height + 1][player_length] == ' ' or generated_level[player_height + 1][player_length] == '@'):
            generated_level[player_height][player_length] = ' '
            player_height += 1
            if generated_level[player_height][player_length] == '@':
                generated_level[player_height][player_length] = 'O'
                print("Well played, you won the game!")
                game_state = "Stop"
            generated_level[player_height][player_length] = 'O'
    else:
        print("You're a fucking moron.")
    return player_height, player_length, game_state

"""def find_player_coordinates(generated_level):
    player_height = "No Player Height Found"
    for i in range(len(generated_level)):
        for j in range(len(generated_level[0])):
            if generated_level[i][j] == 'O':
                player_height = i
                player_length = j
    return player_height, player_length"""





if __name__ == '__main__':
    level_1 = generate_level(20, 20)
    generated_level = level_1[0]
    height = level_1[1]
    length = level_1[2]
    objective_height = level_1[3]
    objective_length = level_1[4]
    player_height = level_1[5]
    player_length = level_1[6]
    game_state = "Continue"
    print_level(generated_level)
    while game_state == "Continue":
        print(game_state)
        if game_state == "Continue":
            print_level(generated_level)
        current_game = play(generated_level, height, length, objective_height, objective_length, player_height, player_length, game_state)
        player_height = current_game[0]
        player_length = current_game[1]
        game_state = current_game[2]
