class AvlTreeMs:
    def printBfs(self, node):
        queue = []
        result = []
        queue.append(node)

        while (len(queue) > 0):
            tmp = queue.pop(0)
            result.append(tmp.key)

            if tmp.left is not None:
                queue.append(tmp.left)
            if tmp.right is not None:
                queue.append(tmp.right)
        return result
    
    def printBfsHeight(self, node):
        queue = []
        result = []
        queue.append(node)

        while (len(queue) > 0):
            tmp = queue.pop(0)
            result.append(tmp.height)

            if tmp.left is not None:
                queue.append(tmp.left)
            if tmp.right is not None:
                queue.append(tmp.right)
        return result


    def mergeWithRoot(self, r1, r2, root):
        root.left = r1
        root.right = r2
        root.parent = None

        r1.parent = root
        r2.parent = root

        return root

    def merge(self, r1, r2):
        root = self._largestNode(r1)
        r1 = self._getRoot(self._remove(root))
        return self.mergeWithRoot(r1, r2, root)

    def _largestNode(self, node):
        if node.right is None:
            return node
        return self._largestNode(node.right)

    def _remove(self, node):
        if node.right is None:
            p = node.parent
            l = node.left

            if p is not None:
                if p.left is not None and p.left.key == node.key:
                    p.left = l
                if p.right is not None and p.right.key == node.key:
                    p.right = l
            
            if l is not None:
                l.parent = p
                return l
            else:
                return p

        else:
            nextNode = self._next(node)
            node.key = nextNode.key

            p = nextNode.parent
            r = nextNode.right

            if p is not None:
                if p.left is not None and p.left.key == nextNode.key:
                    p.left = r
                if p.right is not None and p.right.key == nextNode.key:
                    p.right = r
            
            if r is not None:
                r.parent = p

            return p

    def _getRoot(self, node):
        if node.parent is None:
            return node
        return self._getRoot(node.parent)

    def _next(self, node):
        if node.right is not None:
            return self._leftDescendant(node.right)
        else:
            return self._rightAncestor(node.key, node.parent)

    def _leftDescendant(self, node):
        if node.left is None:
            return node
        return self._leftDescendant(node.left)

    def _rightAncestor(self, key, node):
        if node is None:
            return None

        if key == node.key:
            return node
        else:
            return self._rightAncestor(key, node.parent)

    def avlMerge(self, r1, r2):
        root = self._largestNode(r1)
        r1 = self._getRoot(self._remove(root))

        return self._avlTreeMergeWithRoot(r1, r2, root)

    def _avlTreeMergeWithRoot(self, r1, r2, root):
        if abs(r1.height - r2.height) <= 1:
            self.mergeWithRoot(r1, r2, root)
            root.height = max(r1.height, r2.height) + 1
            
            return root
        elif r1.height > r2.height:
            r = self._avlTreeMergeWithRoot(r1.right, r2, root)
            r1.right = r
            r.parent = r1

            r1.parent = None
            self._rebalance(r1)
            
            return self._getRoot(r1)

        elif r1.height < r2.height:
            r = self._avlTreeMergeWithRoot(r1, r2.left, root)
            r2.left = r
            r.parent = r2

            r2.parent = None
            self._rebalance(r2)
            return self._getRoot(r2)

    def _rebalance(self, node):
        if self._getHeight(node.left) > self._getHeight(node.right) + 1:
            self._rebalanceRight(node)
        
        if self._getHeight(node.right) > self._getHeight(node.left) + 1:
            self._rebalanceLeft(node)

        self._adjustHeight(node)

        p = node.parent
        if p is not None:
            self._rebalance(p)

    def _getHeight(self, node):
        return 0 if node is None else node.height

    def _rebalanceRight(self, node):
        l = node.left
        if self._getHeight(l.right) > self._getHeight(l.left):
            self._rotateLeft(l)
        self._rotateRight(node)

    def _rebalanceLeft(self, node):
        r = node.right
        if self._getHeight(r.left) > self._getHeight(r.right):
            self._rotateRight(r)
        self._rotateLeft(node)

    def _rotateRight(self, node):
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

    def _rotateLeft(self, node):
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

    def _adjustHeight(self, node):
        if node is None:
            return 0
        node.height = max(self._adjustHeight(node.left), self._adjustHeight(node.right)) + 1
        return node.height

    def split(self, root, x):
        if root is None:
            return (None, None)

        if x < root.key:
            r1, r2 = self.split(root.left, x)
            r3 = self.mergeWithRoot(r2, root.right, root)
            
            return (r1, r3)
        elif x > root.key:
            r1, r2 = self.split(root.right, x)
            r3 = self.mergeWithRoot(root.left, r1, root)
            
            return (r3, r2)
        else:
            return (root.left, root.right)
    
    def avlSplit(self, root, x):
        if root is None:
            return (None, None)

        if x < root.key:
            r1, r2 = self.avlSplit(root.left, x)
            r3 = self._avlTreeMergeWithRoot(r2, root.right, root)
            
            return (r1, r3)
        elif x > root.key:
            r1, r2 = self.avlSplit(root.right, x)
            r3 = self._avlTreeMergeWithRoot(root.left, r1, root)
            
            return (r3, r2)
        else:
            return (root.left, root.right)
