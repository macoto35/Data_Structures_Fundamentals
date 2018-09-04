import re
from random import *

class PhoneNumberToName:
    class Pair:
        def __init__(self, key, value):
            self.key = key
            self.value = value

    def __init__(self):
        self.L = 15
        self.p = 10 ** 15 + 100011
        self.m = 1000
        self._generateRandomKeys()

        self.chains = [None] * self.m

    def _toInt(self, key):
        return int(re.sub('\D', '', key))

    def _generateRandomKeys(self):
        self.a = randint(1, self.p - 1)
        self.b = randint(0, self.p - 1)

    def _hash(self, key):
        x = self._toInt(key)
        return ((self.a * x + self.b) % self.p) % self.m
    
    def find(self, key):
        chain = self.chains[self._hash(key)]
        if chain is None:
            return False

        for pair in chain:
            if pair.key == key:
                return True
        return False
        
    def add(self, key, value):
        chain = self.chains[self._hash(key)]
        pair = self.Pair(key, value)

        if chain is None:
            self.chains[self._hash(key)] = chain = []

        for pair in chain:
            if pair.key == key:
                pair.value = value

        chain.append(pair)

    def get(self, key):
        chain = self.chains[self._hash(key)]
        for pair in chain:
            if pair.key == key:
                return pair.value
        return None

