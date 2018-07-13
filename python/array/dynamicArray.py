class DynamicArray:
    def __init__(self):
        self.arr = None
        self.size = 0
        self.capacity = 0

    def toString(self):
        if self.arr is None:
            return ''
        else:
            return ','.join([str(data) for data in self.arr if data is not None])

    def length(self):
        return self.size

    def cap(self):
        return self.capacity

    def get(self, idx):
        if idx < 0 or idx >= self.size:
            raise IndexError
        return self.arr[idx]

    def set(self, idx, input):
        if idx < 0 or idx >= self.size:
            raise IndexError
        self.arr[idx] = input

    def pushBack(self, input):
        if self.size == self.capacity:
            length = 1 if self.capacity == 0 else self.capacity * 2
            newArray = [None] * length
            
            for i in range(self.size):
                newArray[i] = self.arr[i]

            self.arr = newArray
            self.capacity = length
        
        self.arr[self.size] = input
        self.size += 1

    def remove(self, idx):
        if idx < 0 or idx >= self.size:
            return None
        
        result = self.arr[idx]
        for i in range(idx, self.size - 1):
            self.arr[i] = self.arr[i + 1]
        self.size -= 1
        return result



