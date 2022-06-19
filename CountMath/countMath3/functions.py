import math
from solver import трапеции, средние, левые, правые, симпсона

def choose_functions():
    номер = int(input("Введите пожалуйста номер уравнения которое хотите посчитать: "))
    return номер


def print_functions():
    print('\n' + "Функции без разрыва: ")
    print('\n' + "Функция под номером 1: -2x^3 - 3x^2 + x + 5")
    print("функция под номером 2: x^2 - 3x - 2")
    print("Функция под номером 3: sin(x) - cos(x) + 0.2x" + '\n')
    print('\n' + "Функции c разрывом: ")
    print('\n' + "Функция под номером 4: 1/x ")
    print("функция под номером 5: x^2 при x < 2, 3x при х >= 2")
    print("Функция под номером 6: x/|x|" + '\n')


def get_function(номер):
    if номер == 1:
        return lambda x: -2 * x ** 3 - 3 * x ** 2 + x + 5
    elif номер == 2:
        return lambda x: x ** 2 - 3 * x - 2
    elif номер == 3:
        return lambda x: math.sin(x) - math.cos(x) + 0.2 * x
    elif номер == 4:
        return lambda x: 1 / x, 0
    elif номер == 5:
        return lambda x: x ** 2 if x < 2 else 3 * x, 2
    elif номер == 6:
        return lambda x: x / abs(x), 0


def get_attribute():
    a = int(input("Введите нижнюю границу "))
    b = int(input("Введите верхнюю границу "))
    точность = float(input("Введите точность "))
    print('\n' + "Существующие методы для решения: " + '\n' +
          "1: Левого прямоугольника" + '\n' + "2: Правого прямоугольника" + '\n' +
          "3: Среднего прямоугольника" + '\n' + "4: Трапеций" + '\n' + "5: Симпсона" + '\n')

    метод = int(input("Введите номер метода для решения: "))
    if метод == 1:
        метод = левые
    elif метод == 2:
        метод = правые
    elif метод == 3:
        метод = средние
    elif метод == 4:
        метод = трапеции
    elif метод == 5:
        метод = симпсона
    else:
        print("Такого метода не существует")
        exit(0)

    return a, b, точность, метод


