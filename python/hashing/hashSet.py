class HashSet:
    def __init__(self):
        self.chains = [None] * 1000

    def find(self, obj):
        chain = self.chains[obj.hash()]
        
        if chain is None:
            return False

        for key in chain:
            if key.equals(obj):
                return True

        return False

    def add(self, obj):
        if self.find(obj):
            return
        
        chain = self.chains[obj.hash()]
        if chain is None:
            self.chains[obj.hash()] = chain = []
        chain.append(obj)


    def remove(self, obj):
        if self.find(obj) == False:
            return None

        chain = self.chains[obj.hash()]
        chain.remove(obj)

        return obj
