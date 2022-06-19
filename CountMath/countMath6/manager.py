import matplotlib.pyplot as plt
from functions import *
from solver import *


def start():
    print("Функции для выбора: ")
    for i in range(1, 3):
        print(i, ": y' = ", get_function_name(i))
    num = int(input("\nВыберите функцию: "))
    function = get_function(num)
    x_0 = float(input("\nВведите x0: "))
    y_0 = float(input("Введите y0: "))
    a = float(input("\nВведите левую границу: "))
    b = float(input("Введите правую границу: "))
    h = float(input("\nВведите длину шага: "))
    e = float(input("\nВведите точность: "))
    ans_runge = runge_method(function, a, b, x_0, y_0, h, e)
    ans_adams = adamsMethod(function, a, b, x_0, y_0, h, e)
    plot(ans_runge, ans_adams, x_0, y_0)


def plot(runge, adams, x_0, y_0):
    x = [a[0] for a in runge]
    y = [a[1] for a in runge]
    plt.plot(x, y, color="red", linewidth=3, label="Runge Kutt Method")
    x = [a[0] for a in adams]
    y = [a[1] for a in adams]
    plt.plot(x, y, color="green", linewidth=1, label="Adams Method")
    plt.plot(x_0, y_0, marker="o", linewidth=0, label="Dot")
    plt.legend()
    plt.show()
