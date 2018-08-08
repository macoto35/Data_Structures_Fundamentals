
from random import randint

def find(i, P):
    if i != P[i]:
        P[i] = find(P[i], P)
    return P[i]

def union(d, s, P, R):
    d_id = find(d, P)
    s_id = find(s, P)

    if d_id != s_id:
        R[d_id] += R[s_id]
        R[s_id] = 0
        P[s_id] = d_id

    return R[d_id]

def merge(n, m, O, P, R):
    maxSize = max(R)

    for item in O:
        size = union(item[0] - 1, item[1] - 1, P, R)
        
        if size > maxSize:
            maxSize = size
        print(maxSize, end = ' ')

# input
n, m = map(int, input().split())
R = [*map(int, input().split())]
O = []
exec("O.append([*map(int, input().split())]);" * m)
P = [i for i in range(n)]

merge(n, m, O, P, R)


# random check
'''
N, M = map(int, input().split())

while True:
    n = randint(1, N)
    m = randint(1, M)

    R = [None] * n
    P = [None] * n
    for i in range(n):
        R[i] = randint(0, 10000)
        P[i] = i

    O = [None] * m
    for i in range(m):
        O[i] = [randint(1, n), randint(1, n)]

    merge(n, m, O, P, R)
'''
