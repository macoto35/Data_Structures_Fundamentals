class NewArray:
    array = []
    size = 0

    def __init__(self):
        self.array = [None] * 5
        self.size = 0

    def toString(self):
        return ', '.join([str(x) for x in self.array if x != None])

    def addLast(self, element):
        self.array[self.size] = element
        self.size += 1

        return True

    def add(self, idx, element):
        for i in range(self.size - 1, idx - 1, -1):
            self.array[i + 1] = self.array[i]

        self.array[idx] = element
        self.size += 1

        return True

    def addFirst(self, element):
        return self.add(0, element)

    def remove(self, idx):
        delete = self.array[idx]

        if delete != None:
            for i in range(idx, self.size):
                if i + 1 >= self.size:
                    self.array[i] = None
                else:
                    self.array[i] = self.array[i + 1]
            self.size -= 1
            self.array[self.size] = None

        return delete

    def removeFirst(self):
        return self.remove(0)

    def removeLast(self):
        if self.size > 0:
            return self.remove(self.size - 1)
        else:
            return None

    def get(self, idx):
        if idx >= len(self.array):
            return None
        return self.array[idx]

    def length(self):
        return self.size

    def indexOf(self, data):
        for i in range(self.size):
            if data == self.array[i]:
                return i
        return -1
