from node import Node

class BinarySearchTree:
    root = None

    def toString(self, root, arr):
        if root is None:
            return

        self.toString(root.left, arr)
        arr.append(root.key)
        self.toString(root.right, arr)

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

    def next(self, node):
        if node.right is not None:
            return self._leftDescendant(node.right)
        else:
            return self._rightAncestor(node.key, node.parent)

    def _leftDescendant(self, node):
        if node.left is not None:
            return self._leftDescendant(node.left)
        return node

    def _rightAncestor(self, key, node):
        if node is None:
            return None
        
        if key < node.key:
            return node
        else:
            return self._rightAncestor(key, node.parent)

    def rangeSearch(self, st, ed, root):
        result = []
        node = self.find(st, root)

        while (node is not None and node.key <= ed):
            if node.key >= st:
                result.append(node.key)
            node = self.next(node)

        return result

    def insert(self, key, root):
        if root is None:
            return Node(key, None)

        node = self.find(key, root)
        newNode = Node(key, node)

        if key < node.key:
            node.left = newNode
        else:
            node.right = newNode

        return root

    def remove(self, node):
        if node.right is None:
            l = node.left
            p = node.parent

            if l is None:
                self.root = None
                return
            
            l.parent = p

            if p is not None:
                if p.left is not None and p.left.key == node.key:
                    p.left = l
                if p.right is not None and p.right.key == node.key:
                    p.right = l
            else:
                self.root = l

        else:
            nextNode = self.next(node)
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
