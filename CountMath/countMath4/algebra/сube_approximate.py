import math
from algebra.util import calc_system


def qub_approximate(points):
    
    n = len(points)

    sum_x = 0
    sum_x_sqd = 0
    sum_x_qub = 0
    sum_x_forth = 0
    sum_x_fifth = 0
    sum_x_six = 0
    sum_y = 0
    sum_x_y = 0
    sum_x_sqd_y = 0
    sum_x_cub_y = 0
    
    for i in range(n):
        sum_x += points[i][0]
        sum_x_sqd += points[i][0] ** 2
        sum_x_qub += points[i][0] ** 3
        sum_x_forth += points[i][0] ** 4
        sum_x_fifth += points[i][0] ** 5
        sum_x_six += points[i][0] ** 6
        sum_y += points[i][1]
        sum_x_y += points[i][0] * points[i][1]
        sum_x_sqd_y += (points[i][0] ** 2) * points[i][1]
        sum_x_cub_y += (points[i][0] ** 3) * points[i][1]

    system = [
        [n, sum_x, sum_x_sqd, sum_x_qub, sum_y],
        [sum_x, sum_x_sqd, sum_x_qub, sum_x_forth, sum_x_y],
        [sum_x_sqd, sum_x_qub, sum_x_forth, sum_x_fifth, sum_x_sqd_y],
        [sum_x_qub, sum_x_forth, sum_x_fifth, sum_x_six, sum_x_cub_y]
    ]

    ans = calc_system(system, 4)
    result_func = lambda x: ans[3] * (x ** 3) + ans[2] * (x ** 2) + ans[1] * x + ans[0]
    str_result_func = f"{round(ans[3], 3)}x^3 + {round(ans[2], 3)}x^2 + {round(ans[1], 3)}x + {round(ans[0], 3)}"

    errors = [(points[i][1] - result_func(points[i][0])) ** 2 for i in range(n)]
    mid_sqd_err = math.sqrt(sum(errors) / n)

    return result_func, str_result_func, errors, mid_sqd_err
