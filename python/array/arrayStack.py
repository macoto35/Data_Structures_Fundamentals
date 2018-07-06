class ArrayStack:
    arr = []
    size = 0

    def __init__(self):
        self.arr = []
        self.size = 0

    def toString(self):
        result = []
        for i in range(self.size-1, -1, -1):
            result.append(str(self.arr[i]))
        return ','.join(result)

    def push(self, input):
        self.arr.append(input)
        self.size += 1

    def top(self):
        if self.size == 0:
            return None
        return self.arr[self.size - 1]

    def pop(self):
        if self.size == 0:
            return None
        self.size -= 1
        return self.arr[self.size]

    def empty(self):
        return self.size == 0

