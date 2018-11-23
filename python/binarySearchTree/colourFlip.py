from node import Node

class ColourFlip:
    t1 = None
    t2 = None
    
    def newArray(self, n):
        for i in range(1, n+1):
            self.t1 = self.avlInsert(self.t1, i, 'w')
            self.t2 = self.avlInsert(self.t2, i, 'b')

    def colour(self, key):
        node = self.find(self.t1, key)
        
        return node.colour

    def flip(self, x):
        # print('--------------->enter flip: ', x)
        l1, r1 = self.split(self.t1, x)
        # print('l1: ', self.bfsPrint(l1, 'key'))
        # print('r2: ', self.bfsPrint(r1, 'key'))
        l2, r2 = self.split(self.t2, x)
        # print('l2: ', self.bfsPrint(l2, 'key'))
        # print('r2: ', self.bfsPrint(r2, 'key'))
        self.t1 = self.merge(l1, r2)
        self.t2 = self.merge(l2, r1)

    def split(self, node, x):
        if node is None:
            return (None, None)

        if x < node.key:
            r1, r2 = self.split(node.left, x)
            r3 = self.avlTreeMergeWithRoot(r2, node.right, node)
            return (r1, r3)
        elif x > node.key:
            r1, r2 = self.split(node.right, x)
            r3 = self.avlTreeMergeWithRoot(node.left, r1, node)
            return (r3, r2)
        else:
            r1 = node.left
            node.left = None
            
            if r1 is not None:
                r1.parent = None
            node.parent = None
            return (r1, node)

    def merge(self, r1, r2):
        root = self.findLargestElement(r1)
        # print('largest element in left: ', root.key)
        r1 = self.getRoot(self.delete(root))
        # print('r1 root: ', r1.key if r1 is not None else r1)
        return self.avlTreeMergeWithRoot(r1, r2, root)

    def findLargestElement(self, node):
        if node.right is not None:
            return self.findLargestElement(node.right)
        return node

    def delete(self, node):
        if node.right is None:
            p = node.parent
            l = node.left

            if l is not None:
                l.parent = p
            
            if p is not None:
                if p.left is not None and p.left.key == node.key:
                    p.left = l
                if p.right is not None and p.right.key == node.key:
                    p.right = l
                return p
            else:
                return l
        else:
            nextNode = self.next(node)
            node.key = nextNode.key

            p = nextNode.parent
            r = nextNode.right

            if p.left is not None and p.left.key == nextNode.key:
                p.left = r
            if p.right is not None and p.right.key == nextNode.key:
                p.right = r

            if r is not None:
                r.parent = p
            
            return p

    def getRoot(self, node):
        if node is None:
            return None

        if node.parent is None:
            return node
        return self.getRoot(node.parent)

    def next(self, node):
        if node.right is not None:
            return self.leftDescendant(node.right)
        else:
            return self.rightAncestor(node.parent, node.key)

    def leftDescendant(self, node):
        if node.left is not None:
            return self.leftDescendant(node.left)
        return node

    def rightAncestor(self, node, key):
        if key < node.key:
            return node
        
        if node.parent is not None:
            return self.rightAncestor(node.parent, key)
        return None

    def avlTreeMergeWithRoot(self, r1, r2, root):
        if abs(self.get(r1, 'height') - self.get(r2, 'height')) <= 1:
            self.mergeWithRoot(r1, r2, root)
            self.adjustHeightAndSize(root)
            
            return root
        elif self.get(r1, 'height') > self.get(r2, 'height'):
            r = self.avlTreeMergeWithRoot(r1.right, r2, root)
            r1.right = r
            r.parent = r1
            r1.parent = None

            return self.rebalance(r1)
        elif self.get(r1, 'height') < self.get(r2, 'height'):
            r = self.avlTreeMergeWithRoot(r1, r2.left, root)
            r2.left = r
            r.parent = r2
            r2.parent = None

            return self.rebalance(r2)

    def mergeWithRoot(self, r1, r2, root):
        root.left = r1
        root.right = r2
        root.parent = None

        if r1 is not None:
            r1.parent = root
        if r2 is not None:
            r2.parent = root

        return root

    def bfsPrint(self, root, key):
        result = []
        q = []
        q.append(root)

        while (len(q) > 0):
            tmp = q.pop(0)
            result.append(str(getattr(tmp, key)))

            if tmp.left is not None:
                q.append(tmp.left)

            if tmp.right is not None:
                q.append(tmp.right)

        return ','.join(result)

    def avlInsert(self, root, key, colour):
       root = self.insert(root, key, colour)
       node = self.find(root, key)
       
       return self.rebalance(node)

    def insert(self, node, key, colour):
        if node is None:
            return Node(key, None, 1, 1, colour)

        p = self.find(node, key)
        new = Node(key, p, 1, 1, colour)
        if key > p.key:
            p.right = new
        else:
            p.left = new

        return node
    
    def find(self, node, key):
        if key == node.key:
            return node
        
        if key > node.key:
            if node.right is None:
                return node
            return self.find(node.right, key)
        else:
            if node.left is None:
                return node
            return self.find(node.left, key)

    def rebalance(self, node):
        if self.get(node.left, 'height') > self.get(node.right, 'height') + 1:
            self.rebalanceRight(node)

        if self.get(node.right, 'height') > self.get(node.left, 'height') + 1:
            self.rebalanceLeft(node)

        self.adjustHeightAndSize(node)

        p = node.parent
        if p is not None:
            return self.rebalance(p)
        return node

    def get(self, node, key):
        return 0 if node is None else getattr(node, key)

    def rebalanceRight(self, node):
        l = node.left
        if self.get(l.right, 'height') > self.get(l.left, 'height'):
            self.rotateLeft(l)
        self.rotateRight(node)

    def rebalanceLeft(self, node):
        r = node.right
        if self.get(r.left, 'height') > self.get(r.right, 'height'):
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

        self.adjustHeightAndSize(node)
        self.adjustHeightAndSize(l)

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

        self.adjustHeightAndSize(node)
        self.adjustHeightAndSize(r)

    def adjustHeightAndSize(self, node):
        node.height = max(self.get(node.left, 'height'), self.get(node.right, 'height')) + 1
        node.size = self.get(node.left, 'size') + self.get(node.right, 'size') + 1


