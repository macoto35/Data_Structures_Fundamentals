from node import Node

class OrderStatistics:
    def bfsPrint(self, node, key='key'):
        result = []
        q = []
        q.append(node)

        while(len(q) > 0):
            tmp = q.pop(0)
            result.append(str(getattr(tmp, key)))

            if tmp.left is not None:
                q.append(tmp.left)
            if tmp.right is not None:
                q.append(tmp.right)
        
        return ','.join(result)

    def find(self, key, node):
        if key == node.key:
            return node

        if key > node.key:
            if node.right is None:
                return node
            return self.find(key, node.right)
        else:
            if node.left is None:
                return node
            return self.find(key, node.left)

    def insert(self, key, root):
        if root is None:
            return Node(key, None)

        node = self.find(key, root)
        new = Node(key, node)

        if key > node.key:
            node.right = new
        else:
            node.left = new

        return root

    def avlInsert(self, key, root):
        root = self.insert(key, root)
        node = self.find(key, root)

        return self.rebalance(node)

    def rebalance(self, node):
        if self.getHeight(node.left) > self.getHeight(node.right) + 1:
            self.rebalanceRight(node)
        
        if self.getHeight(node.right) > self.getHeight(node.left) + 1:
            self.rebalanceLeft(node)

        self.adjustHeightSize(node)

        p = node.parent
        if p is not None:
            return self.rebalance(p)
        
        return node

    def rebalanceRight(self, node):
        l = node.left
        if self.getHeight(l.right) > self.getHeight(l.left):
            self.rotateLeft(l)
        self.rotateRight(node)

    def rebalanceLeft(self, node):
        r = node.right
        if self.getHeight(r.left) > self.getHeight(r.right):
            self.rotateRight(r)
        self.rotateLeft(node)

    def rotateRight(self, node):
        p = node.parent
        l = node.left
        r = l.right

        if p is not None:
            if p.left is not None and p.left.key == node.key:
                p.left = l
            if p.right is not None and p.right.key == node.key:
                p.right = l
        l.parent = p

        l.right = node
        node.parent = l

        node.left = r
        if r is not None:
            r.parent = node

        self.adjustHeightSize(node) # old root
        self.adjustHeightSize(l) # new root

    def rotateLeft(self, node):
        p = node.parent
        r = node.right
        l = r.left

        if p is not None:
            if p.left is not None and p.left.key == node.key:
                p.left = r
            if p.right is not None and p.right.key == node.key:
                p.right = r
        r.parent = p

        r.left = node
        node.parent = r

        node.right = l
        if l is not None:
            l.parent = node

        self.adjustHeightSize(node) # old root
        self.adjustHeightSize(r) # new root

    def getHeight(self, node):
        return 0 if node is None else node.height
    
    def getSize(self, node):
        return 0 if node is None else node.size
    
    def adjustHeightSize(self, node):
        node.height = max(self.getHeight(node.left), self.getHeight(node.right)) + 1
        node.size = self.getSize(node.left) + self.getSize(node.right) + 1

    def orderStatistic(self, root, n):
        size = self.getSize(root.left)

        if n == size + 1:
            return root
        elif n < size + 1:
            return self.orderStatistic(root.left, n)
        elif n > size + 1:
            return self.orderStatistic(root.right, n - size - 1)

    def reverseOrderStatistic(self, root, key):
        if key < root.key:
            return self.reverseOrderStatistic(root.left, key)
        else:
            tmp = 1 + self.getSize(root.left)
            if key == root.key:
                return tmp
            else:
                return tmp + self.reverseOrderStatistic(root.right, key)

