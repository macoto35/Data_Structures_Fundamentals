class HashTable:
    def __init__(self, p, x, m):
        self.p = p
        self.x = x
        self.m = m
        self.chains = [[] for i in range(m)];

    def hash(self, string):
        hash = 0
        for i in range(len(string) - 1, -1 , -1):
            hash = hash * self.x + ord(string[i])
        return hash % self.p % self.m

    def add(self, string):
        chain = self.chains[self.hash(string)]
        for tmp in chain:
            if tmp == string:
                return
        chain.insert(0, string)

    def delete(self, string):
        chain = self.chains[self.hash(string)]
        for tmp in chain:
            if tmp == string:
                chain.remove(string)

    def find(self, string):
        for tmp in self.chains[self.hash(string)]:
            if tmp == string:
                return 'yes'
        return 'no'

    def check(self, i):
        print(self.chains)
        return ' '.join(self.chains[int(i)])

m = int(input())
N = int(input())
table = HashTable(1000000007, 263, m)

result = []
for i in range(N):
    func, arg = input().split()
    tmp = getattr(table, func)(arg)
    if tmp is not None:
        result.append(tmp)

#print('\n'.join(result))
print(*result)
