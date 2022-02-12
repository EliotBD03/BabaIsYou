import random

def generate_level(length, height):
    generated_level = []
    bordures_NS = generate_bordures(length, height, generated_level)
    bordures_OE = generate_corps(length, height, generated_level)
    generated_level.append(bordures_NS)
    for i in range(height - 1):
        generated_level.append(bordures_OE)
    generated_level.append(bordures_NS)
    return generated_level

def generate_bordures(length, height, generated_level):
    bordure_NS = []
    for i in range(length):
        bordure_NS.append("x")
    return bordure_NS

def generate_corps(length, height, generated_level):
    bordure_OE = ["x"]
    for i in range(length):
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

if __name__ == '__main__':
    level_1 = generate_level(20, 20)
    print_level(level_1)
