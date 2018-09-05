from random import *

class DynamicHashTable:
    class Pair:
        def __init__(self, key, value):
            self.key = key
            self.value = value

    def __init__(self):
        self.L = 10
        self.p = 10 ** 15 + 100011
        self.m = 2
        self._randomKeys()
        self.chains = [[] for i in range(self.m)]
        self.size = 0

    def _randomKeys(self):
        self.a = randint(1, self.p - 1)
        self.b = randint(0, self.p - 1)
        self.x = randint(1, self.p - 1)

    def _polyHash(self, key):
        h = 0
        for i in range(len(key) - 1, -1, -1):
            h = (h * self.x + ord(key[i])) % self.p
        return h

    def _universalHash(self, key):
        return ((self.a * key + self.b) % self.p) % self.m

    def _hash(self, key):
        return self._universalHash(self._polyHash(key))

    def _resize(self):
        if self.size / self.m > 0.9:
            self.m = 2 * self.m
            self._randomKeys()
            newChains = [[] for i in range(self.m)]

            for chain in self.chains:
                for pair in chain:
                    newChains[self._hash(pair.key)].append(pair)

            self.chains = newChains

    def add(self, key, value):
        self._resize()

        chain = self.chains[self._hash(key)]
        # update
        for pair in chain:
            if pair.key == key:
                pair.value = value
                return
        # insert
        chain.append(self.Pair(key, value))
        self.size += 1
