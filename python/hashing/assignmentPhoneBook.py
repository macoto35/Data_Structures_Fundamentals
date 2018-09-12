class Phonebook:
    def __init__(self):
        self.book = [None] * 10000000
    
    def add(self, number, name):
        self.book[int(number)] = name
    
    def delete(self, number):
        self.book[int(number)] = None

    def find(self, number):
        result = self.book[int(number)]
        if result is None:
            return 'not found'
        return result

book = Phonebook()
result = []

N = int(input())
for i in range(N):
    func, number, *name = input().split()
    tmp = getattr(book, func)(number, *name)
    if tmp is not None:
        result.append(tmp)

print(*result)
#print('\n'.join(result))
