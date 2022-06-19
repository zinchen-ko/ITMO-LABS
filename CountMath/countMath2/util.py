from functions import get_function_name, get_function, get_system_function, get_system_function_name
from solver import chord_method, iteration_method, iteration_system_method
import matplotlib.pyplot as plt
import numpy as np
import sympy as sp

def start():
    if console_or_file() == 1:
        if one_or_system() == 1:
            for i in range(1, 4):
                print(i, ":", get_function_name(i))
            number = int(input("Введите номер уравнения: "))
            function = get_function(number)
            a = float(input("Введите левый край интервала: "))
            b = float(input("Введите правый край интервала: "))
            accuracy = float(input("Введите точность: "))
            plot_function(function, -9, 9, -9, 9, 1)
            if choose_method() == 1:
                chord_method(function, a, b, accuracy)
            else:
                iteration_method(function, a, b, accuracy)
        else:
            print_system()
            number = int(input("Введите номер системы: "))
            x_0 = float(input("Введите левый край интервала: "))
            x_1 = float(input("Введите правый край интервала: "))
            y_0 = float(input("Введите левый край интервала: "))
            y_1 = float(input("Введите правый край интервала: "))
            accuracy = float(input("Введите точность: "))
            if number == 1:
                function1 = get_system_function(1)
                function2 = get_system_function(2)
                plot_system(1, 2)
            elif number == 2:
                function1 = get_system_function(3)
                function2 = get_system_function(4)
                plot_system(3, 4)
            iteration_system_method(function1, function2, x_0, x_1, y_0, y_1, accuracy)
    else:
        with open('input') as f:
            lines = f.readlines()
            if int(lines[0]) == 1:
                fun = get_function(int(lines[1]))
                a = float(lines[2])
                b = float(lines[3])
                accuracy = float(lines[4])
                plot_function(fun, -9, 9, -9, 9, 1)
                if int(lines[5]) == 1:
                    chord_method(fun, a, b, accuracy)
                else:
                    iteration_method(fun, a, b, accuracy)
            else:
                if int(lines[1]) == 1:
                    fun_1 = get_system_function(1)
                    fun_2 = get_system_function(2)
                    plot_system(1, 2)
                else:
                    fun_1 = get_system_function(3)
                    fun_2 = get_system_function(4)
                    plot_system(3, 4)
                x_0 = float(lines[2])
                x_1 = float(lines[3])
                y_0 = float(lines[4])
                y_1 = float(lines[5])
                accuracy = float(lines[6])
                iteration_system_method(fun_1, fun_2, x_0, x_1, y_0, y_1, accuracy)


def console_or_file():
    choice = int(input("Введите 1 для ввода с консоли или 2 для ввода из файла: "))
    return choice


def one_or_system():
    choice = int(input("Введите 1 для решения уравнения или 2 для решения системы уравнений:"))
    return choice


def print_system():
    print("Доступные системы уравнений: ")
    print("Система №1: ")
    print(get_system_function_name(1))
    print(get_system_function_name(2))
    print("Система №2: ")
    print(get_system_function_name(3))
    print(get_system_function_name(4))


def choose_method():
    print("Доступные методы для решения уравнения: ")
    print("1: Метод хорд ")
    print("2: Метод простых итераций ")
    return int(input("Выберите метод для решения уравнения "))


def plot_function(func, min_x, max_x, min_y, max_y, step):
    x = np.linspace(min_x, max_x, 10000)

    fig = plt.figure()
    ax = fig.add_subplot(1, 1, 1)
    ax.spines['left'].set_position('center')
    ax.spines['bottom'].set_position('center')
    ax.spines['right'].set_color('none')
    ax.spines['top'].set_color('none')
    ax.xaxis.set_ticks_position('bottom')
    ax.yaxis.set_ticks_position('left')

    ax.plot(x, func(x), "g", linewidth=2.0)

    ax.set(xlim=(min_x, max_x), xticks=np.arange(min_x, max_x, step),
           ylim=(min_y, max_y), yticks=np.arange(min_y, max_y, step))

    plt.show()


def plot_system(num1, num2):
    x, y = sp.symbols("x y")
    sp.plot_implicit(
        sp.Or(sp.Eq(get_system_function(num1)(x, y), 0), sp.Eq(get_system_function(num2)(x, y), 0)))