class DisJointSetTreeHeuristics:
    def __init__(self):
        self.parent = [None] * 6
        self.rank = [None] * 6

    def toString(self):
        return ','.join([str(i) for i in self.parent]) + '|' + ','.join([str(i) for i in self.rank])

    def makeSet(self, i):
        if (i < 0 or i >= len(self.parent)):
            raise IndexError
        self.parent[i] = i
        self.rank[i] = 0

    def find(self, i):
        if (i < 0 or i >= len(self.parent)):
            raise IndexError
        if (i != self.parent[i]):
            self.parent[i] = self.find(self.parent[i])

        return self.parent[i]

    def union(self, i, j):
        i_id = self.find(i)
        j_id = self.find(j)

        if (self.rank[i_id] > self.rank[j_id]):
            self.parent[j_id] = i_id
        else:
            self.parent[i_id] = j_id
            if (self.rank[i_id] == self.rank[j_id]):
                self.rank[j_id] += 1

