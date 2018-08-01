class DisJointSetTree:
    def __init__(self):
        self.parent = [None] * 9
        self.rank = [None] * 9

    def toString(self):
        return ','.join([str(p) for p in self.parent if p is not None])

    def toStringRank(self):
        return ','.join([str(p) for p in self.rank if p is not None])

    def makeSet(self, i):
        self.parent[i] = i
        self.rank[i] = 0

    def find(self, i):
        if i < 0 or i >= len(self.parent):
            raise IndexError
        
        while i != self.parent[i]:
            i = self.parent[i]
        
        return self.parent[i]

    def union(self, i, j):
        i_id = self.find(i)
        j_id = self.find(j)

        if self.rank[i_id] > self.rank[j_id]:
            self.parent[j_id] = i_id
        else:
            self.parent[i_id] = j_id
            if self.rank[i_id] == self.rank[j_id]:
                self.rank[j_id] += 1
