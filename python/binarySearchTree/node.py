class Node:
    def __init__(self, key, parent, height=None):
        self.key = key
        self.parent = parent
        self.left = None
        self.right = None
        if height is None:
            self.height = 1
        else:
            self.height = height
