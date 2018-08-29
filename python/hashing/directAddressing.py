import re

class DirectAddressing:
    def __init__(self):
        self.phoneBook = [None] * 10000000

    def _getInt(self, number):
        return int(re.sub('\D', '', number))

    def setName(self, number, name):
        idx = self._getInt(number)
        self.phoneBook[idx] = name

    def getName(self, number):
        idx = self._getInt(number)
        return self.phoneBook[idx]
