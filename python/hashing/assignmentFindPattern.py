from random import *
import time

def naive(T, P):
    result = []
    for i in range(len(T) - len(P), -1, -1):
        t = T[i : i+len(P)]
        if P == t:
            result.insert(0, i)
    return result

def polyHash(S, p, x):
    h = 0
    for i in range(len(S) - 1, -1, -1):
        h = (h * x + ord(S[i])) % p
    return h

def sRabinKarp(T, P, p, x):
    result = []
    pHash = polyHash(P, p, x)

    for i in range(len(T) - len(P), -1, -1):
        t = T[i : i+len(P)]
        tHash = polyHash(t, p, x)
        if pHash == tHash:
            if P == t:
                result.insert(0, i)
    return result

def preprocessHashes(T, lenP, p, x):
    idx = len(T) - lenP
    H = [None for i in range(idx + 1)]
    H[idx] = polyHash(T[idx : idx+lenP], p, x)

    y = 1
    for i in range(lenP):
        y = y * x % p

    for i in range(idx - 1, -1, -1):
        H[i] = (x * H[i + 1] + ord(T[i]) - y * ord(T[i + lenP]) ) % p
    
    return H

def fRabinKarp(T, P, p, x):
    result = []
    pHash = polyHash(P, p, x)
    H = preprocessHashes(T, len(P), p, x)

    for i in range(len(T) - len(P), -1, -1):
        if pHash == H[i]:
            if P == T[i : i+len(P)]:
                result.insert(0, i)
    return result

p = 1000000007
x = 31

'''P = input()
T = input()
#print(*naive(T, P))
#print(*sRabinKarp(T, P, p, x))
print(*fRabinKarp(T, P, p, x))'''

start_time = time.time()
print('prepare P and T: ', start_time)
P = ''
for i in range(5 * (10 ** 3)):
    P += chr(choice([randint(65, 90), randint(97, 122)]))
T = ''
for i in range(10 ** 3):
    T += P
print('start rabin karp: ', time.time() - start_time)
print(*fRabinKarp(T, P, p, x))
print('end rabin karp: ', time.time() - start_time)
