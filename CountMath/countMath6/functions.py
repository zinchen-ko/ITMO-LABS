import math


def get_function(num):
    if num == 1:
        return lambda x, y: math.sin(x) + y
    if num == 2:
        return lambda x, y: (x - y) ** 2


def get_function_name(num):
    if num == 1:
        return "sin(x) + y"
    if num == 2:
        return "(x - y)^2"