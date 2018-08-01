class DisJointSetArray:
    def __init__(self):
        self.smallest = [None] * 9
        self.size = -1
        self.maxSize = 9

    def toString(self):
        return ','.join([str(self.smallest[i]) for i in range(self.size + 1)])

    def makeSet(self, p):
        if self.size == self.maxSize - 1:
            raise IndexError
        self.size += 1
        self.smallest[self.size] = p

    def find(self, i):
        if i < 0 or i > self.size:
            raise IndexError
        return self.smallest[i]

    def union(self, i, j):
        i_id = self.find(i)
        j_id = self.find(j)

        if i_id == j_id:
            return

        m = min(i_id, j_id)
        
        for idx in range(self.size + 1):
            if self.smallest[idx] in [i_id, j_id]:
                self.smallest[idx] = m
