class Node:
    def __init__(self, key, parent, height=1, size=1, colour='w'):
        self.key = key
        self.parent = parent
        self.left = None
        self.right = None
        self.height = height
        self.size = size

        self.colour = colour
