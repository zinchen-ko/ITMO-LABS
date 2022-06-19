import math
import numpy as np
from algebra.util import calc_system


def exp_approximate(input_points):
    
    points = []

    for i in input_points:
        if i[1] > 0:
            points.append(i)

    n = len(points)
    
    sum_x = 0
    sum_x_sqd = 0
    sum_y = 0
    sum_x_y = 0
    
    for i in range(n):
        sum_x += points[i][0]
        sum_x_sqd += points[i][0] ** 2
        sum_y += math.log(points[i][1])
        sum_x_y += points[i][0] * math.log(points[i][1])

    try:
        ans = calc_system([[sum_x_sqd, sum_x, sum_x_y], [sum_x, n, sum_y]], 2)
    except Exception:
        return None, None, None, None

    result_func = lambda x: np.exp(ans[1]) * np.exp(ans[0] * x)
    str_result_func = f"{round(math.exp(ans[1]), 3)}e^{round(ans[0], 3)}*x"

    errors = [(points[i][1] - result_func(points[i][0])) ** 2 for i in range(n)]
    mid_sqd_err = math.sqrt(sum(errors) / n)

    return result_func, str_result_func, errors, mid_sqd_err
