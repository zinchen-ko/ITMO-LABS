import numpy as np
import matplotlib.pyplot as plt
import math
from algebra.degree_approximate import degree_approximate
from algebra.exp_approximate import exp_approximate
from algebra.linear_approximate import linear_approximate
from algebra.ln_approximate import ln_approximate
from algebra.сube_approximate import qub_approximate
from algebra.squad_approximate import squad_approximate


def start():
    if int(input("Введите 1 чтобы взять точки из файла и 2 чтобы взять точки из косноли")) == 1:
        points = file_points()
    else:
        points = console_points()

    if int(input("Введите 1 чтобы вывести результат в файл и 2 чтобы вывести результат в консоль")) == 1:
        file_answer(points)
    else:
        console_answer(points)


def console_points():
    n = 0
    while n <= 0:
        try:
            n = int(input("Ведите количество точек, которые хотите ввести: "))
        except Exception:
            print("Введите число")

    points = []
    print("Введите точки через пробел: ")
    while len(points) != n:
        try:
            for i in range(n):
                points.append(list(map(float, input().strip().split())))
                if len(points[i]) != 2:
                    raise ValueError
        except ValueError:
            print("Неправильный формат ввода")
            exit()
    return points


def file_points():
    # Получение точек из файла
    with open("input", 'rt') as file:
        try:
            points = []
            for line in file:
                new_row = list(map(float, line.strip().split()))
                if len(new_row) != 2:
                    raise ValueError
                points.append(new_row)
        except ValueError:
            print("Неверный формат файла")
            exit()
    return points


def console_answer(points):
    print(f"Полученные точки: {points}")
    print()
    linear_func, linear_str_func, linear_err, linear_squad_err, pirson = linear_approximate(points, 2)
    print(
        f"Линейной аппроксимацией получена функция: {linear_str_func}, S = {round(sum(linear_err), 3)}, sigma = {round(linear_squad_err, 3)}")
    print()
    squad_func, squad_str_func, squad_err, squad_squad_err = squad_approximate(points)
    print(
        f"Квадратичной аппроксимацией получена функция: {squad_str_func}, S = {round(sum(squad_err), 3)}, sigma = {round(squad_squad_err, 3)}")
    print()
    cub_func, cub_str_func, cub_err, cub_squad_err = qub_approximate(points)
    print(
        f"Кубической аппроксимацией получена функция: {cub_str_func}, S = {round(sum(cub_err), 3)}, sigma = {round(cub_squad_err, 3)}")
    print()
    exp_func, exp_str_func, exp_err, exp_squad_err = exp_approximate(points)
    if exp_func is None:
        print("Нет ни одной точки в области определения экспоненциальной функции")
        exp_squad_err = math.inf
    else:
        print(
            f"Экспоненциальной аппроксимацией получена функция: {exp_str_func}, S = {round(sum(exp_err), 3)}, sigma = {round(exp_squad_err, 3)}")
    print()
    log_func, log_str_func, log_err, log_squad_err = ln_approximate(points)
    if log_func is None:
        print("Нет ни одной точки в области определения логарифмический функции")
        log_squad_err = math.inf
    else:
        print(
            f"Логарифмической аппроксимацией получена функция: {log_str_func}, S = {round(sum(log_err), 3)}, sigma = {round(log_squad_err, 3)}")
    print()
    deg_func, deg_str_func, deg_err, deg_squad_err = degree_approximate(points)
    if deg_func is None:
        print("Нет ни одной точки в области опредения степенной функции")
        deg_squad_err = math.inf
    else:
        print(
            f"Степенной апроксимацией получена функция: {deg_str_func}, S = {round(sum(deg_err), 3)}, sigma = {round(deg_squad_err, 3)}")
    print()

    min_r = min(linear_squad_err, squad_squad_err, cub_squad_err, exp_squad_err, log_squad_err, deg_squad_err)

    print(f"Минимальное среднеквадратичное отклонение: {round(min_r, 3)}")
    if min_r == linear_squad_err:
        print("Лучшая аппроксимация: линейная")
    elif min_r == squad_squad_err:
        print("Лучшая аппроксимация: квадратичная")
    elif min_r == cub_squad_err:
        print("Лучшая аппроксимация: кубическая")
    elif min_r == exp_squad_err:
        print("Лучшая аппроксимация: экспоненциальная")
    elif min_r == log_squad_err:
        print("Лучшая аппроксимация: логарфимическая")
    elif min_r == deg_squad_err:
        print("Лучшая аппроксимация: степенная")

    plot(points, linear_func, squad_func, cub_func, exp_func, log_func, deg_func)


def file_answer(points):
    f = open("output", 'w')
    f.write(f"Полученные точки: {points}" + '\n')
    linear_func, linear_str_func, linear_err, linear_squad_err, pirson = linear_approximate(points, 1)
    f.write(pirson + '\n')
    f.write(
        f"Линейной аппроксимацией получена функция: {linear_str_func}, S = {round(sum(linear_err), 3)}, sigma = {round(linear_squad_err, 3)}" + '\n')
    squad_func, squad_str_func, squad_err, squad_squad_err = squad_approximate(points)
    f.write(
        f"Квадратичной аппроксимацией получена функция: {squad_str_func}, S = {round(sum(squad_err), 3)}, sigma = {round(squad_squad_err, 3)}" + '\n')
    cub_func, cub_str_func, cub_err, cub_squad_err = qub_approximate(points)
    f.write(
        f"Кубической аппроксимацией получена функция: {cub_str_func}, S = {round(sum(cub_err), 3)}, sigma = {round(cub_squad_err, 3)}" + '\n')
    exp_func, exp_str_func, exp_err, exp_squad_err = exp_approximate(points)
    if exp_func is None:
        f.write("Нет ни одной точки в области определения экспоненциальной функции" + '\n')
        exp_squad_err = math.inf
    else:
        f.write(
            f"Экспоненциальной аппроксимацией получена функция: {exp_str_func}, S = {round(sum(exp_err), 3)},sigma = {round(exp_squad_err, 3)}" + '\n')
    log_func, log_str_func, log_err, log_squad_err = ln_approximate(points)
    if log_func is None:
        f.write("Нет ни одной точки в области определения логарифмический функции" + '\n')
        log_squad_err = math.inf
    else:
        f.write(
            f"Логарифмической аппроксимацией получена функция: {log_str_func}, S = {round(sum(log_err), 3)}, sigma = {round(log_squad_err, 3)}" + '\n')
    deg_func, deg_str_func, deg_err, deg_squad_err = degree_approximate(points)
    if deg_func is None:
        f.write("Нет ни одной точки в области опредения степенной функции" + '\n')
        deg_squad_err = math.inf
    else:
        f.write(
            f"Степенной апроксимацией получена функция: {deg_str_func}, S = {round(sum(deg_err), 3)},sigma = {round(deg_squad_err, 3)}" + '\n')

    min_r = min(linear_squad_err, squad_squad_err, cub_squad_err, exp_squad_err, log_squad_err, deg_squad_err)

    f.write(f"Минимальное среднеквадратичное отклонение: {round(min_r, 3)}" + '\n')
    if min_r == linear_squad_err:
        f.write("Лучшая аппроксимация: линейная" + '\n')
    elif min_r == squad_squad_err:
        f.write("Лучшая аппроксимация: квадратичная" + '\n')
    elif min_r == cub_squad_err:
        f.write("Лучшая аппроксимация: кубическая" + '\n')
    elif min_r == exp_squad_err:
        f.write("Лучшая аппроксимация: экспоненциальная" + '\n')
    elif min_r == log_squad_err:
        f.write("Лучшая аппроксимация: логарфимическая" + '\n')
    elif min_r == deg_squad_err:
        f.write("Лучшая аппроксимация: степенная" + '\n')

    plot(points, linear_func, squad_func, cub_func, exp_func, log_func, deg_func)
    f.close()

def plot(points, lin_f, sqd_f, qub_f, exp_f, log_f, deg_f):
    minimum_x = 0
    maximum_x = 0

    minimum_y = 0
    maximum_y = 0

    points_x = []
    points_y = []

    for i in points:
        maximum_x = max(i[0], maximum_x)
        minimum_x = min(i[0], minimum_x)
        maximum_y = max(i[1], maximum_y)
        minimum_y = min(i[1], minimum_y)
        points_x.append(i[0])
        points_y.append(i[1])

    x = np.linspace(minimum_x - 0.5, maximum_x + 0.5, 10000)

    fig = plt.figure()
    ax = fig.add_subplot(1, 1, 1)
    ax.spines['left'].set_position('center')
    ax.spines['bottom'].set_position('center')
    ax.spines['right'].set_color('none')
    ax.spines['top'].set_color('none')
    ax.xaxis.set_ticks_position('bottom')
    ax.yaxis.set_ticks_position('left')

    ax.plot(x, lin_f(x), "r", linewidth=2.0, label="linear")
    ax.plot(x, sqd_f(x), "g", linewidth=2.0, label="squad")
    ax.plot(x, qub_f(x), "b", linewidth=2.0, label="cube")
    if exp_f is not None:
        ax.plot(x, exp_f(x), "pink", linewidth=2.0, label="exp")
    x = np.linspace(0.000001, maximum_x + 0.5, 10000)
    if log_f is not None:
        ax.plot(x, log_f(x), "darkred", linewidth=2.0, label="log")
    if deg_f is not None:
        ax.plot(x, deg_f(x), "purple", linewidth=2.0, label="deg")
    ax.legend()

    ax.plot(points_x, points_y, linewidth=0, marker="*", markersize=10, markeredgecolor="black", markerfacecolor="green")
    ax.set(xlim=(minimum_x - 0.5, maximum_x + 0.5), xticks=np.arange(minimum_x, maximum_x, 0.5),
           ylim=(minimum_y - 0.5, maximum_y + 0.5), yticks=np.arange(minimum_y, maximum_y, 0.5))

    plt.show()