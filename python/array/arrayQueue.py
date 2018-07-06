class ArrayQueue:
    arr = [None]*5
    write = 0
    read = 0
    
    def __init__(self):
        arr = [None]*5
        self.write = 0
        self.read = 0

    def toString(self):
        result = [str(d) for d in self.arr if d != None]
        return ','.join(result)

    def enqueue(self, input):
        nextIn = self.__nextWrite()

        if nextIn == self.read:
            raise IndexError
        else:
            self.arr[self.write] = input
            self.write = nextIn
    
    def __nextWrite(self):
        return 0 if self.write == len(self.arr) - 1 else self.write + 1

    def dequeue(self):
        if self.write == self.read:
            return None
        result = self.arr[self.read]
        self.arr[self.read] = None
        self.read = 0 if self.read == len(self.arr) - 1 else self.read + 1
        
        return result

    def empty(self):
        return self.read == self.write
