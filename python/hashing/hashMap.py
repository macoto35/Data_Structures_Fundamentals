class HashMap:
    def __init__(self):
        self.chains = [None] * 1000


    class Chain:
        def __init__(self):
            self.head = None
            self.tail = None

        class Pair:
            def __init__(self, key, value):
                self.key = key
                self.value = value
                self.prev = None
                self.next = None

        def push(self, key, value):
            pair = self.Pair(key, value)
            if self.head is None:
                self.head = pair
            if self.tail is not None:
                self.tail.next = pair
                pair.prev = self.tail
            self.tail = pair

        def find(self, key):
            pair = self.head
            while pair is not None:
                if pair.key == key:
                    return pair
                pair = pair.next
            return None

        def pop(self, key):
            pair = self.find(key)

            if pair is None:
                return pair

            prev = pair.prev
            next = pair.next
            if prev is not None:
                prev.next = next
            else:
                self.head = next

            if next is not None:
                next.prev = prev
            else:
                self.tail = prev
            
            result = pair.value
            pair = None
            return result


    def hash(self, key):
        return int(key[:3])


    def hashKey(self, key):
        chain = self.chains[self.hash(key)]
        if chain is None:
            return False
        return (False if chain.find(key) is None else True)


    def set(self, key, value):
        chain = self.chains[self.hash(key)]

        if chain is None:
            self.chains[self.hash(key)] = chain = self.Chain()
        
        exist = chain.find(key)
        if exist is not None:
            exist.value = value
        else:
            chain.push(key, value)


    def get(self, key):
        chain = self.chains[self.hash(key)]

        if chain is None:
            return None

        result = chain.find(key)

        if result is None:
            return None
        else:
            return result.value

    def remove(self, key):
        chain = self.chains[self.hash(key)]

        if chain is None:
            return None

        return chain.pop(key)
