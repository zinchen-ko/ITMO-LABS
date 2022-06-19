def same_sign(first, second):
    return True if (first < 0 and second < 0) or (first > 0 and second > 0) or (first == second == 0) else False


def find_div(function, h=0.000000001):
    return lambda x: (function(x+h) - function(x-h)) / (2*h)


# Частная производная по х
def calc_dx(function, x, y, h=0.00000001):
    return (function(x + h, y) - function(x - h, y)) / (2 * h)


# Частная производная по у
def calc_dy(function, x, y, h=0.00000001):
    return (function(x, y + h) - function(x, y - h)) / (2 * h)


def chord_method(fun, a, b, accuracy):
    temp = a
    iteration = 0
    if same_sign(fun(a), fun(b)):
        print("Корней нет")
        return
    while True:
        iteration += 1
        x = a - fun(a) * ((b-a)/(fun(b) - fun(a)))
        if same_sign(fun(x), fun(a)):
            a = x
        else:
            b = x
        if abs(x - temp) < accuracy:
            print("x = ", round(x, 6))
            print("f(x) = ", round(fun(x), 6))
            break
        temp = x
    print("Кол-во итераций: ", iteration)


def iteration_method(fun, a, b, accuracy):
    dev_a = find_div(fun)(a)
    dev_b = find_div(fun)(b)
    lyambd_a = -(1 / dev_a)
    lyambd_b = - (1 / dev_b)
    if dev_a > dev_b:
        lyambd = lyambd_a
    else:
        lyambd = lyambd_b
    fi = lambda x: x + lyambd * fun(x)
    fi_s = find_div(fi)
    if abs(fi_s(a)) > 1 or abs(fi_s(b)) > 1:
        print("Не удовлетворяет достаточному условию сходимости")
    else:
        print("Удовлетворяет достаточному условию сходимости")
    x_current = a
    x_prev = a * 1000 + 10
    iterations = 0
    while (abs(x_prev - x_current) > accuracy) or (abs(fun(x_current)) > accuracy):
        x_prev = x_current
        x_current = x_prev + lyambd * fun(x_prev)
        iterations += 1
        if iterations > 1000:
            print("Алгоритм расходится")
            exit()
    print("x = ", round(x_current, 6))
    print("f(x) = ", round(fun(x_current), 6))
    print("Кол-во итераций: ", iterations)


def iteration_system_method(first_fun, second_fun, x_0, x_1, y_0, y_1, accuracy):
    fi_1 = lambda x, y: (first_fun(x, y) - x) * -1
    fi_2 = lambda x, y: (second_fun(x, y) - y) * -1
    f1_approx = x_1
    f2_approx = y_1
    if max(abs(calc_dx(fi_1, f1_approx, f2_approx)) + abs(calc_dy(fi_2, f1_approx, f2_approx)),
           abs(calc_dx(fi_2, f1_approx, f2_approx)) + abs(calc_dy(fi_1, f1_approx, f2_approx))) >= 1:
        print("Достаточное условие сходимости не выполнено")
        return
    last_x = x_1
    last_y = y_1
    iteration = 0
    while True:
        iteration += 1
        x = fi_1(last_x, last_y)
        y = fi_2(last_y, last_y)
        if max(abs(x - last_x), abs(y - last_y)) < accuracy:
            print("Ответ найдет в точке: ", "(", x, ";", y, ")")
            print("Значение первой функции в точке: ", first_fun(x, y))
            print("Значение второй функции в точке: ", second_fun(x, y))
            break
        last_x = x
        last_y = y
    return