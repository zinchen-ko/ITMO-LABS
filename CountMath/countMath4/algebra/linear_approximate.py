import math
from algebra.util import calc_system


def linear_approximate(points, input):
    
    n = len(points)

    sum_x = 0
    sum_x_x = 0
    sum_y = 0
    sum_x_y = 0

    for i in range(n):
        sum_x += points[i][0]
        sum_x_x += points[i][0] ** 2
        sum_y += points[i][1]
        sum_x_y += points[i][0] * points[i][1]

    mid_x = sum_x / n
    mid_y = sum_y / n
    
    sum_1 = 0
    sum_2 = 0
    sum_3 = 0
    
    for i in range(n):
        sum_1 += (points[i][0] - mid_x) * (points[i][1] - mid_y)
        sum_2 += (points[i][0] - mid_x) ** 2
        sum_3 += (points[i][1] - mid_y) ** 2

    try:
        if input == 1:
            r = sum_1 / (math.sqrt(sum_2 * sum_3))
            pirson = f"Коэффициент корреляции Пирсона равен: {round(r, 3)}"
        else:
            pirson = f"Коэффициент корреляции Пирсона равен:"
            r = sum_1 / (math.sqrt(sum_2 * sum_3))
            print(f"Коэффициент корреляции Пирсона равен: {round(r, 3)}")
    except Exception:
        if input == 1:
            pirson = "Не получилось посчитать коэффициент корреляции Пирсона"
        else:
            pirson = "Не получилось посчитать коэффициент корреляции Пирсона"
            print(pirson)

    ans = calc_system([[sum_x_x, sum_x, sum_x_y], [sum_x, n, sum_y]], 2)

    result_func = lambda x: ans[0] * x + ans[1]

    str_result_func = f"{round(ans[0], 3)}x + {round(ans[1], 3)}"

    # среднеквадратичное отклонение
    errors = [(points[i][1] - result_func(points[i][0])) ** 2 for i in range(n)]
    mid_sqd_err = math.sqrt(sum(errors) / n)

    return result_func, str_result_func, errors, mid_sqd_err, pirson
