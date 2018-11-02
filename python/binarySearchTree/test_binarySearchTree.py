import unittest
from binarySearchTree import BinarySearchTree
from node import Node

class BinarySearchTreeTest(unittest.TestCase):
    def setUp(self):
        self.tree = BinarySearchTree()

    def getRoot(self):
        root = Node(7, None)
        left = root.left = Node(4, root)
        right = root.right = Node(13, root)
        left.left = Node(1, left)
        left.right = Node(6, left)
        right.left = Node(10, right)
        right.right = Node(15, right)

        return root
        
    def test_find(self):
        root = self.getRoot()
        self.assertEqual(7, self.tree.find(7, root).key)
        self.assertEqual(13, self.tree.find(13, root).key)
        self.assertEqual(6, self.tree.find(6, root).key)
        self.assertEqual(10, self.tree.find(10, root).key)
        self.assertEqual(1, self.tree.find(0, root).key)
        self.assertEqual(1, self.tree.find(2, root).key)
        self.assertEqual(10, self.tree.find(8, root).key)
        self.assertEqual(15, self.tree.find(16, root).key)

    def test_next(self):
        root = self.getRoot()
        self.assertEqual(4, self.tree.next(self.tree.find(1, root)).key)
        self.assertEqual(7, self.tree.next(self.tree.find(6, root)).key)
        self.assertEqual(6, self.tree.next(self.tree.find(4, root)).key)
        self.assertEqual(10, self.tree.next(self.tree.find(7, root)).key)
        self.assertIsNone(self.tree.next(self.tree.find(15, root)))

    def test_rangeSearch(self):
        root = self.getRoot()
        self.assertEqual([6,7,10], self.tree.rangeSearch(5, 12, root))
        self.assertEqual([1], self.tree.rangeSearch(1,3, root))
        self.assertEqual([], self.tree.rangeSearch(16,20, root))

    '''
                7
            4       13
         1    6   10   15
          3
    '''
    def test_insert(self):
        root = self.tree.insert(7, None)
        root = self.tree.insert(4, root)
        root = self.tree.insert(13, root)
        root = self.tree.insert(1, root)
        root = self.tree.insert(6, root)
        root = self.tree.insert(10, root)
        root = self.tree.insert(15, root)
        root = self.tree.insert(3, root)

        self.assertEqual(7, root.key)
        self.assertEqual(4, root.left.key)
        self.assertEqual(13, root.right.key)
        self.assertEqual(1, root.left.left.key)
        self.assertEqual(6, root.left.right.key)
        self.assertEqual(10, root.right.left.key)
        self.assertEqual(15, root.right.right.key)
        self.assertEqual(3, root.left.left.right.key)

        result = []
        self.tree.toString(root, result)
        self.assertEqual([1,3,4,6,7,10,13,15], result)

    def test_remove(self):
        root = self.tree.insert(7, None)
        root = self.tree.insert(4, root)
        root = self.tree.insert(13, root)
        root = self.tree.insert(1, root)
        root = self.tree.insert(6, root)
        root = self.tree.insert(10, root)
        root = self.tree.insert(15, root)
        root = self.tree.insert(3, root)
        self.tree.root = root

        for i in range(8):
            self.tree.remove(self.tree.root)
            result = []
            self.tree.toString(self.tree.root, result)
            print(result)

if __name__ == '__main__':
    unittest.main()
