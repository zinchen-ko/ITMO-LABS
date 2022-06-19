import sys


def rungeKutt(func, x_0, y_0, h, n):
    y_prev = y_0
    y_current = 0
    answer_x = [x_0]
    answer_y = [y_0]
    for i in range(n):
        x_prev = x_0 + i * h
        y_current = get_odds(func, x_prev, y_prev, h)
        answer_y.append(y_current)
        answer_x.append(x_0 + (i + 1) * h)
        y_prev = y_current
        if i == n - 1 and abs(h) > 0.09:
            print("\nРезультаты метода Рунге при шаге равном ", h, ": ")
            print(answer_x)
            print(answer_y)
    return list(zip(answer_x, answer_y))


def runge_method(func, a, b, x_0, y_0, h, e):

    R = sys.maxsize
    ans = rungeKutt(func, x_0, y_0, -h, int((x_0 - a) / h))
    ans = ans + rungeKutt(func, x_0, y_0, h, int((b - x_0) / h))
    y_old = ans[-1][1]

    while R > e:
        h /= 2
        ans = rungeKutt(func, x_0, y_0, -h, int((x_0 - a) / h))
        ans = ans + rungeKutt(func, x_0, y_0, h, int((b - x_0) / h))
        y_now = ans[-1][1]
        R = abs((y_old - y_now))/(2**4 - 1)
        y_old = y_now

    ans.sort(key=sorter)

    return ans

def get_odds(func, x, y, h):
    k1 = h * func(x, y)
    k2 = h * func(x + h / 2, y + k1 / 2)
    k3 = h * func(x + h / 2, y + k2 / 2)
    k4 = h * func(x + h, y + k3)
    return y + (1 / 6) * (k1 + 2 * k2 + 2 * k3 + k4)


def adamsMethod(func, a, b, x_0, y_0, h, accuracy):
    R = sys.maxsize
    ans = adams_util(func, x_0, y_0, -h, int((x_0 - a) / h) + 1)
    ans = ans + adams_util(func, x_0, y_0, h, int((b - x_0) / h) + 1)
    y_old = ans[-1][1]

    while R > accuracy:
        h /= 2
        ans = adams_util(func, x_0, y_0, -h, int((x_0 - a) / h) + 1)
        ans = ans + adams_util(func, x_0, y_0, h, int((b - x_0) / h) + 1)
        y_now = ans[-1][1]
        R = abs((y_old - y_now)) / (2 ** 4 - 1)
        y_old = y_now

    ans.sort(key=sorter)

    return ans


def adams_util(func, x_0, y_0, h, n):

    answer_x = []
    answer_y = []

    y_1 = get_odds(func, x_0, y_0, h)
    y_2 = get_odds(func, x_0 + h, y_1, h)
    y_3 = get_odds(func, x_0 + 2 * h, y_2, h)
    y_4 = get_odds(func, x_0 + 3 * h, y_3, h)

    answer_x.append(x_0 + 0 * h)
    answer_y.append(y_0)
    answer_x.append(x_0 + 1 * h)
    answer_y.append(y_1)
    answer_x.append(x_0 + 2 * h)
    answer_y.append(y_2)
    answer_x.append(x_0 + 3 * h)
    answer_y.append(y_3)

    func_prev_1 = func(x_0 + h, y_1)
    func_prev_2 = func(x_0 + 2 * h, y_2)
    func_prev_3 = func(x_0 + 3 * h, y_3)
    func_prev_4 = func(x_0 + 4 * h, y_4)

    for i in range(4, n):
        delta_fi = func_prev_4 - func_prev_3
        delta_fi2 = func_prev_4 - 2 * func_prev_3 + func_prev_2
        delta_fi3 = func_prev_4 - 3 * func_prev_3 + 3 * func_prev_2 - func_prev_1
        x_next = x_0 + i * h
        x_prev = x_0 + (i - 1) * h
        y_next = y_4 + h * func(x_prev, y_4) + ((h ** 2) / 2) * delta_fi + ((5 * (h ** 3)) / 12) * delta_fi2 + ((3 * (h ** 4)) / 8) * delta_fi3
        answer_x.append(x_next)
        answer_y.append(y_next)
        func_prev_1 = func_prev_2
        func_prev_2 = func_prev_3
        func_prev_3 = func_prev_4
        func_prev_4 = func(x_next, y_next)
        y_4 = y_next
        if i == n-1 and abs(h) > 0.09:
            print("\nРезультаты метода Адамса при шаге равном ", h, ": ")
            print(answer_x)
            print(answer_y)
    return list(zip(answer_x, answer_y))


def sorter(s):
    return s[0]