import re

class InternationalPhoneBookDynamicArray:
    def __init__(self):
        self.arr = [None] * 1
        self.size = 0
        self.maxSize = 1

    class Pair:
        def __init__(self, number, name):
            self.number = number
            self.name = name

    def _binarySearch(self, number, st, ed):
        if st == ed:
            if self._getInt(self.arr[st].number) < number:
                return st + 1
            else:
                return st

        mid = st + (ed - st) // 2
        val = self._getInt(self.arr[mid].number)
        
        if val == number:
            return mid
        elif val < number:
            return self._binarySearch(number, mid + 1, ed)
        else:
            return self._binarySearch(number, st, mid - 1)

    def _getInt(self, number):
        return re.sub('\D', '', number)

    def setName(self, number, name):
        # resize
        if self.size == self.maxSize:
            newMaxSize = self.maxSize * 2
            newArr = [None] * newMaxSize
            
            for i in range(self.maxSize):
                newArr[i] = self.arr[i]
            
            self.arr = newArr
            self.maxSize = newMaxSize
        
        # append
        pair = self.Pair(number, name)
        if self.size == 0:
            self.arr[self.size] = pair
        else:
            idx = self._binarySearch(self._getInt(number), 0, self.size - 1)
            for i in range(self.size - 1, idx - 1):
                self.arr[i + 1] = self.arr[i]
            self.arr[idx] = pair

        self.size += 1

    def getName(self, number):
        idx = self._binarySearch(self._getInt(number), 0, self.size - 1)
        val = self.arr[idx]

        if val is not None and self.arr[idx].number == number:
            return self.arr[idx].name
        else:
            return None
