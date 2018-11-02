from node import Node

class AvlTree:
    
    def getRoot(self, node):
        if node.parent is None:
            return node
        return self.getRoot(node.parent)

    def _find(self, key, node):
        if key == node.key:
            return node

        if key > node.key:
            if node.right is None:
                return node
            return self._find(key, node.right)
        else:
            if node.left is None:
                return node
            return self._find(key, node.left)

    def _insert(self, key, root):
        if root is None:
            return Node(key, None)

        node = self._find(key, root)
        newNode = Node(key, node)
        
        if key > node.key:
            node.right = newNode
        else:
            node.left = newNode
        
        return root

    def avlInsert(self, key, root):
        #print('AVL Insert: ', key)
        root = self._insert(key, root)
        node = self._find(key, root)
        self._rebalance(node)

        return root

    def _rebalance(self, node):
        #print('Rebalance: ', node.key, ' | ', self._getHeight(node.left) , '/', self._getHeight(node.right))
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
        #print('Rebalance Right: ', node.key)
        pivot = node.left
        if self._getHeight(pivot.right) > self._getHeight(pivot.left):
            self._rotateLeft(pivot)
        self._rotateRight(node)

    def _rebalanceLeft(self, node):
        #print('Rebalance Left: ', node.key)
        pivot = node.right
        if self._getHeight(pivot.left) > self._getHeight(pivot.right):
            self._rotateRight(pivot)
        self._rotateLeft(node)

    def _rotateRight(self, node):
        #print('Rotate Right: ', node.key)
        p = node.parent
        b = node.left
        e = b.right

        if p is not None:
            if p.left is not None and p.left.key == node.key:
                p.left = b
            if p.right is not None and p.right.key == node.key:
                p.right = b
        b.parent = p

        b.right = node
        node.parent = b

        node.left = e
        if e is not None:
            e.parent = node
    
    def _rotateLeft(self, node):
        #print('Rotate Left: ', node.key)
        p = node.parent
        c = node.right
        d = c.left

        if p is not None:
            if p.left is not None and p.left.key == node.key:
                p.left = c
            if p.right is not None and p.right.key == node.key:
                p.right = c
        c.parent = p

        c.left = node
        node.parent = c

        node.right = d
        if d is not None:
            d.parent = node

    def _adjustHeight(self, node):
        if node is None:
            return 0
        
        node.height = max(self._adjustHeight(node.left), self._adjustHeight(node.right)) + 1
        #print('Adjust Height: ', node.key, ' / ', node.height)
        return node.height

    def bfsPrint(self, root):
        if root is None:
            return

        result = []
        result.append(root)

        while (len(result) > 0):
            node = result.pop(0)
            print(node.key, '(', node.height, ')', end=' ')
            
            if node.left is not None:
                result.append(node.left)
            if node.right is not None:
                result.append(node.right)

    def avlRemove(self, node):
        tmp = self._remove(node)
        
        if tmp is None:
            return None

        self._rebalance(tmp)
        
        return self.getRoot(tmp)

    def _remove(self, node):
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

    def _next(self, node):
        if node.right is not None:
            return self._leftDecendant(node.right)
        else:
            return self._rightAncestor(node.key, node.parent)

    def _leftDecendant(self, node):
        if node.left is None:
            return node
        return self._leftDecendant(node.left)

    def _rightAncestor(self, key, node):
        if node is None:
            return None

        if key < node.key:
            return node
        else:
            return self._rightAncestor(key, node.parent)


