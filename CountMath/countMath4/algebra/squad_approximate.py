import math
from algebra.util import calc_system


def squad_approximate(points):

    n = len(points)
    
    sum_x = 0
    sum_x_sqd = 0
    sum_x_qub = 0
    sum_x_forth = 0
    sum_y = 0
    sum_x_y = 0
    sum_x_sqd_y = 0

    for i in range(n):
        sum_x += points[i][0]
        sum_x_sqd += points[i][0] ** 2
        sum_x_qub += points[i][0] ** 3
        sum_x_forth += points[i][0] ** 4
        sum_y += points[i][1]
        sum_x_y += points[i][0] * points[i][1]
        sum_x_sqd_y += (points[i][0] ** 2) * points[i][1]

    system = [
        [n, sum_x, sum_x_sqd, sum_y],
        [sum_x, sum_x_sqd, sum_x_qub, sum_x_y],
        [sum_x_sqd, sum_x_qub, sum_x_forth, sum_x_sqd_y]
    ]

    ans = calc_system(system, 3)
    result_func = lambda x: ans[2] * (x ** 2) + ans[1] * x + ans[0]
    str_result_func = f"{round(ans[2], 3)}x^2 + {round(ans[1], 3)}x + {round(ans[0], 3)}"

    errors = [(points[i][1] - result_func(points[i][0])) ** 2 for i in range(n)]
    mid_sqd_err = math.sqrt(sum(errors) / n)

    return result_func, str_result_func, errors, mid_sqd_err