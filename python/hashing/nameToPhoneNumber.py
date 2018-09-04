from random import *

class NameToPhoneNumber:
    class Pair:
        def __init__(self, key, value):
            self.key = key
            self.value = value

    def __init__(self):
        self.L = 15
        self.p = 10 ** 15 + 100011
        self.m = 1000
        self._setRandomKeys()
        self.chains = [None] * self.m
    
    def _setRandomKeys(self):
        self.x = randint(1, self.p - 1)
        self.a = randint(1, self.p - 1)
        self.b = randint(0, self.p - 1)

    def _polyHash(self, S):
        h = 0
        for i in range(len(S) - 1, -1, -1):
            h = (h * self.x + ord(S[i])) % self.p

        return h

    def _universalFamilyHash(self, x):
        return ((self.a * x + self.b) % self.p) % self.m

    def _hash(self, S):
        return self._universalFamilyHash(self._polyHash(S))

    def add(self, key, value):
        chain = self.chains[self._hash(key)]
        
        if chain is None:
            self.chains[self._hash(key)] = chain = []

        for pair in chain:
            if pair.key == key:
                pair.value = value
        chain.append(self.Pair(key, value))

    def get(self, key):
        chain = self.chains[self._hash(key)]

        if chain is not None:
            for pair in chain:
                if pair.key == key:
                    return pair.value
        return None
