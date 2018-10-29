class Node:
    def __init__(self, key, parent, height = 1):
        self.key = key
        self.parent = parent
        self.left = None
        self.right = None
        self.height = height

class AvlTree:
    result = None

    def getTree(self, node):
        if node is None:
            return
        self.getTree(node.left)
        self.result.append(node.key)
        self.getTree(node.right)

    def find(self, key, node):
        if node.key == key:
            return node

        if node.key > key:
            if node.left is None:
                return node
            return self.find(key, node.left)
        else:
            if node.right is None:
                return node
            return self.find(key, node.right)

    def insert(self, key, root):
        node = self.find(key, root)
        newNode = Node(key, node)

        if node.key > key:
            node.left = newNode
        else:
            node.right = newNode

        return root

    def getRootNode(self, node):
        if node.parent is None:
            return node
        return self.getRootNode(node.parent)

    def avlInsert(self, key, root):
        root = self.insert(key, root)
        node = self.find(key, root)
        self.rebalance(node)

        return self.getRootNode(root)

    def rebalance(self, node):
        parent = node.parent
        leftHeight = self.defaultHeight(node.left)
        rightHeight = self.defaultHeight(node.right)
        print('enter rebalance: ', node.key, leftHeight, rightHeight)

        if leftHeight > rightHeight + 1:
            self.rebalanceRight(node)
        if leftHeight + 1 < rightHeight:
            self.rebalanceLeft(node)

        self.adjustHeight(node)

        if parent is not None:
            self.rebalance(parent)
    
    def rebalanceRight(self, node):
        print('enter rebalanceright: ', node.key)
        m = node.left
        leftHeight = self.defaultHeight(m.left)
        rightHeight = self.defaultHeight(m.right)

        if rightHeight > leftHeight:
            self.rotateLeft(m)
        self.rotateRight(node)

    def rotateRight(self, node):
        print('rotate right: ', node.key)
        z = node
        y = node.left
        p = node.parent

        y.parent = p
        if p is not None:
            if p.left is not None and p.left.key == node.key:
                p.left = y
            elif p.right is not None and p.right.key == node.key:
                p.right = y

        z.left = y.right
        if y.right is not None:
            z.left.parent = z

        y.right = z
        z.parent = y

        if p is not None:
            self.adjustHeight(p)
        else:
            self.adjustHeight(y)

    def rebalanceLeft(self, node):
        print('enter rebalanceLeft: ', node.key)
        m = node.right
        leftHeight = self.defaultHeight(m.left)
        rightHeight = self.defaultHeight(m.right)
        
        if leftHeight > rightHeight:
            self.rotateRight(m)
        self.rotateLeft(node)

    def rotateLeft(self, node):
        print('rotate left: ', node.key)
        z = node
        y = z.right
        p = node.parent

        y.parent = p
        if p is not None:
            if p.left is not None and p.left.key == node.key:
                p.left = y
            elif p.right is not None and p.right.key == node.key:
                p.right = y
        
        z.right = y.left
        if y.left is not None:
            y.left.parent = z

        y.left = z
        z.parent = y

        if p is not None:
            self.adjustHeight(p)
        else:
            self.adjustHeight(y)

    def adjustHeight(self, node):
        if node is None:
            return 0

        #print('enter height: ', node.key)
        node.height = 1 + max(self.adjustHeight(node.left), self.adjustHeight(node.right))
        print('----->set height: ', node.key, ' - ', node.height)
        
        return node.height
    
    def defaultHeight(self, node):
        return 0 if node is None else node.height

