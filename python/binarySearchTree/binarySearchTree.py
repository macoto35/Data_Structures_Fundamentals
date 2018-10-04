class Node:
    def __init__(self, key, parent):
        self.parent = parent
        self.left = None
        self.right = None
        self.key = key

class BinarySearchTree:
    def getTree(self, node):
        if node is None:
            return
        self.getTree(node.left)
        print(node.key)
        self.getTree(node.right)

    def find(self, key, node):
        if node.key == key:
            return node

        elif node.key > key:
            if node.left is None:
                return node
            return self.find(key, node.left)
        
        elif node.key < key:
            if node.right is None:
                return node
            return self.find(key, node.right)

    def next(self, node):
        if node.right is not None:
            return self.leftDescendant(node.right)
        else:
            return self.rightAncestor(node.key, node)

    def leftDescendant(self, node):
        if node.left is None:
            return node
        return self.leftDescendant(node.left)

    def rightAncestor(self, key, node):
        if node.parent is None:
            return None

        if key < node.parent.key:
            return node.parent
        return self.rightAncestor(key, node.parent)

    def rangeSearch(self, x, y, node):
        result = []
        
        n = self.find(x, node)
        while n is not None and n.key <= y:
            if n.key >= x:
                result.append(n.key)
            n = self.next(n)
        
        return result

    def insert(self, key, node):
        if node is None:
            return Node(key, None)

        p = self.find(key, node)
        if p.key > key:
            p.left = Node(key, p)
        elif p.key < key:
            p.right = Node(key, p)

        return node

    def delete(self, node):
        if node.right is None:
            print('case 1---------------------------------------------')
            x = node.left

            if x is not None:
                node.key = x.key
                if x.left is not None:
                    x.left.parent = node
                if x.right is not None:
                    x.right.parent = node
                node.left = x.left
                node.right = x.right
            else:
                # last node
                node.key = None

        else:
            print('case 2----------------------------------------------')
            x = self.next(node)
            y = None

            # replace delete by x
            node.key = x.key

            # promote x.right
            if x.right is not None:
                x.right.parent = x.parent
                y = x.right

            if x.parent.left is not None and x.parent.left.key == x.key:
                x.parent.left = y
            else:
                x.parent.right = y
            
            # remove x
            x = None

