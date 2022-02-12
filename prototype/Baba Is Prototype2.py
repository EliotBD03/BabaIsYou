import random

def generate_level(length, height):
    generated_level = []
    temp = []
    for i in range(length):
        temp.append("x")
    bordure_NS = temp
    generated_level.append(bordure_NS)
    while len(generated_level) < height - 1:
        temp = []
        for i in range(length):
            if len(temp) == 0 or len(temp) == length - 1:
                temp.append("x")
                if len(temp) == length:
                    generated_level.append(temp)
            else:
                temp.append(" ")
    generated_level.append(bordure_NS)
    return generated_level

def print_level(level):
    for j in level:
        line = ""
        for i in j:
            line += i
        print(line)

if __name__ == '__main__':
    level_1 = generate_level(20, 20)
    print_level(level_1)