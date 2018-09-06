from random import *

def areEqual(s1, s2):
    if len(s1) != len(s2):
        return False

    for i in range(len(s1) - 1):
        if s1[i] != s2[i]:
            return False
    return True

# Naive
def findSubstringNaive(T, P):
    positions = []

    for i in range(len(T) - len(P)):
        if areEqual(T[i : i + len(P)], P):
            positions.append(i)

    return positions

def polyHash(s, p, x):
    hashResult = 0

    for i in range(len(s) - 1, -1, -1):
        hashResult = (hashResult * x + ord(s[i])) % p

    return hashResult

# Rabin-Karp
def rabinKarp(T, P):
    p = 10 ** 15 + 100011
    x = randint(1, p - 1)
    pHash = polyHash(P, p, x)
    positions = []

    for i in range(len(T) - len(P)):
        t = T[i : i + len(P)]
        tHash = polyHash(t, p, x)
        if pHash != tHash:
            continue
        if areEqual(t, P):
            positions.append(i)

    return positions

def precomputeHashes(T, lenP, p, x):
    idx = len(T) - lenP
    H = [None for i in range(idx + 1)]
    H[idx] = polyHash(T[idx : idx + lenP], p, x)

    y = 1
    for i in range(1, lenP + 1):
        y = y * x % p

    for i in range(idx - 1, -1, -1):
        H[i] = ( x * H[i+1] + ord(T[i]) - y * ord(T[i + lenP]) ) % p
    
    return H

# Optimized Rabin-Karp 
def optimizedRabinKarp(T, P):
    p = 10 ** 15 + 100011
    x = randint(1, p - 1)
    pHash = polyHash(P, p, x)
    H = precomputeHashes(T, len(P), p, x)
    positions = []

    for i in range(len(T) - len(P), -1, -1):
        if pHash != H[i]:
            continue
        if areEqual(T[i : i + len(P)], P):
            positions.append(i)

    return positions


print([0, 14] == findSubstringNaive('Hello, World! Hello, User!', 'Hello'))
print([] == findSubstringNaive('hello, World! hello, User!', 'Hello'))
print([] == findSubstringNaive('python has a great built-in list type named list.', 'key'))

print([0, 14] == rabinKarp('Hello, World! Hello, User!', 'Hello'))
print([] == rabinKarp('hello, World! hello, User!', 'Hello'))
print([] == rabinKarp('python has a great built-in list type named list.', 'key'))

print([14, 0] == optimizedRabinKarp('Hello, World! Hello, User!', 'Hello'))
print([] == optimizedRabinKarp('hello, World! hello, User!', 'Hello'))
print([] == optimizedRabinKarp('python has a great built-in list type named list.', 'key'))



