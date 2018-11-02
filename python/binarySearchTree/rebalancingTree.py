class RebalancingTree:
    def getRoot(self, node):
        if node.parent is None:
            return node
        return self.getRoot(node.parent)

    def rotateRight(self, node):
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

    def rotateLeft(self, node):
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

