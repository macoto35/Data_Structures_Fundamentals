class BinaryTree:
    def __init__(self, root):
        self.root = root
        self.result = []

    def depthFirstPreOrderTraverse(self):
        self.__preDFT(self.root)
        return ','.join(self.result)

    def __preDFT(self, node):
        if node == None:
            return
        self.result.append(node.data)
        self.__preDFT(node.left)
        self.__preDFT(node.right)

    def depthFirstInOrderTraverse(self):
        self.__inDFT(self.root)
        return ','.join(self.result)

    def __inDFT(self, node):
        if node == None:
            return
        self.__inDFT(node.left)
        self.result.append(node.data)
        self.__inDFT(node.right)

    def depthFirstPostOrderTraverse(self):
        self.__postDFT(self.root)
        return ','.join(self.result)

    def __postDFT(self, node):
        if node == None:
            return
        self.__postDFT(node.left)
        self.__postDFT(node.right)
        self.result.append(node.data)

    class Queue:
        head = None
        tail = None

        class Node:
            def __init__(self, data):
                self.data = data
                self.next = None
        
        def enqueue(self, input):
            node = self.Node(input)

            if self.head is None:
                self.head = node

            if self.tail is not None:
                self.tail.next = node
            
            self.tail = node

        def dequeue(self):
            if self.head is None:
                return None

            delete = self.head
            self.head = delete.next
            result = delete.data
            delete = None

            return result

        def empty(self):
            return self.head is None

    def breathFirst(self):
        queue = self.Queue()
        queue.enqueue(self.root)

        while queue.empty() == False:
            treeNode = queue.dequeue()
            self.result.append(treeNode.data)
            if treeNode.left is not None:
                queue.enqueue(treeNode.left)
            if treeNode.right is not None:
                queue.enqueue(treeNode.right)
        
        return ','.join(self.result)


