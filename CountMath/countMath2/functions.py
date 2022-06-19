import numpy as np


def get_function(num):
    if num == 1:
        return lambda x: x ** 3 + 2.28 * (x ** 2) - 1.934 * x - 3.907
    if num == 2:
        return lambda x: x ** 2 - 3 * x - 2
    if num == 3:
        return lambda x: np.sin(x) - np.cos(x) + 0.2*x


def get_function_name(num):
    if num == 1:
        return "x^3 + 2.28x^2 - 1.934x - 3.907"
    if num == 2:
        return "x^2 - 3x - 2"
    if num == 3:
        return "sin(x) - cos(x) + 0.2x"


def get_system_function(num):
    if num == 1:
        return lambda x, y: x + 0.1 * x ** 2 + 0.2 * y ** 2 - 0.3
    if num == 2:
        return lambda x, y: y + 0.2 * x ** 2 - 0.1 * x * y - 0.7
    if num == 3:
        return lambda x, y: x**2 - y - 3
    if num == 4:
        return lambda x, y: x**3 + y - 1


def get_system_function_name(num):
    if num == 1:
        return "x+0.1*x^2+0.2*y^2-0.3"
    if num == 2:
        return "y+0.2*x^2-0.1*x-0.7"
    if num == 3:
        return "x^2 - y - 3"
    if num == 4:
        return "x^3 + y - 1"