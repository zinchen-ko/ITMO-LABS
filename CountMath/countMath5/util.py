from functions import func
from solver import lagrange_method, bessel_method, stirling_method, newton_method, gauss_method
import numpy as np
import matplotlib.pyplot as plt


def start():
    if console_or_file() == 1:
        a = float(input("Введите левую границу интервала: "))
        b = float(input("Введите правую границу интервала: "))
        step = float(input("Введите шаг: "))
        x = []
        y = []
        x_now = a
        while x_now <= b:
            x.append(x_now)
            y.append(func(x_now))
            x_now += step
    else:
        FILE = "file"
        with open(FILE, 'rt') as file:
            try:
                x = []
                y = []
                for line in file:
                    new_row = list(map(float, line.strip().split()))
                    if len(new_row) != 2:
                        raise ValueError
                    x.append(new_row[0])
                    y.append(new_row[1])
            except ValueError:
                print("Неверный формат файла")
                exit()
    create(x, y)
    solve(x, y)


def console_or_file():
    wish = int(input("Введите 1 для получения точек из консоли и 2 для получения точек из файла: "))
    if wish == 1:
        return 1
    else:
        return 2


def solve(x, y):
    points = []
    for i in range(len(x)):
        points.append([x[i], y[i]])
    ask_x = float(input("Введите X для которого нужно посчитать f(x): "))
    print("Результат по методу Лагранжа: ")
    print(lagrange_method(x, y, ask_x))
    print("Результат по методу Ньютона: ")
    print(newton_method(x, y, ask_x))
    print("Результат по методу Гаусса: ")
    print(gauss_method(points, ask_x))
    print("Результат по методу Стирлинга: ")
    print(stirling_method(x, y, ask_x))
    print("Результат по методу Бесселя: ")
    print(bessel_method(x, y, ask_x))


def create(x, y, ):
    points = []
    for i in range(len(x)):
        points.append([x[i], y[i]])
    x_plot = np.linspace(np.min(x) - 2, np.max(x) + 2, 1000)
    plotting(x_plot, func,
             [lagrange_method(x, y, x_now) for x_now in x_plot],
             [newton_method(x, y, x_now) for x_now in x_plot],
             [stirling_method(x, y, x_now) for x_now in x_plot],
             [bessel_method(x, y, x_now) for x_now in x_plot],
             [gauss_method(points, x_now) for x_now in x_plot], x, y)


def plotting(x, f_y, lag_y, newton_y, stirling_y, bessel_y, gauss_y, point_x, point_y):
    if f_y is not None:
        plt.plot(x, f_y(x), linewidth=2.0, label="function")
    plt.plot(x, lag_y, linewidth=2.0, label="lagrange")
    plt.plot(x, newton_y, linewidth=2.0, label="newton")
    plt.plot(x, gauss_y, linewidth=2.0, label="gauss")
    plt.plot(x, stirling_y, linewidth=2.0, label="stirling")
    plt.plot(x, bessel_y, linewidth=2.0, label="bessel")
    plt.plot(point_x, point_y, '*', linewidth=0, label="points")
    plt.legend()
    plt.grid(True)
    minimum_x = min(point_x)
    minimum_y = min(point_y)
    maximum_x = max(point_x)
    maximum_y = max(point_y)
    plt.xlim(minimum_x - 2, maximum_x + 2)
    plt.ylim(minimum_y - 2, maximum_y + 2)
    plt.show()
