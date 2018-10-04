import unittest
from binarySearchTree import BinarySearchTree, Node

class BinarySearchTreeTest(unittest.TestCase):
    def setUp(self):
        self.tree = BinarySearchTree()
    
    def getRoot(self):
        root = Node(7, None)
        root.left = Node(4, root)
        root.right = Node(13, root)
        root.left.left = Node(1, root.left)
        root.left.right = Node(6, root.left)
        root.right.left = Node(10, root.right)
        root.right.right = Node(15, root.right)

        return root

    def test_find(self):
        root = self.getRoot()
        self.assertEqual(7, self.tree.find(7, root).key)
        self.assertEqual(6, self.tree.find(6, root).key)
        self.assertEqual(13, self.tree.find(13, root).key)
        self.assertEqual(6, self.tree.find(5, root).key)

    def test_next(self):
        root = self.getRoot()
        self.assertIsNone(self.tree.next(self.tree.find(15, root)))
        self.assertEqual(7, self.tree.next(self.tree.find(6, root)).key)
        self.assertEqual(10, self.tree.next(self.tree.find(7, root)).key)

    def test_rangeSearch(self):
        root = self.getRoot()
        self.assertEqual([6,7,10], self.tree.rangeSearch(5,12, root))
        self.assertEqual([1], self.tree.rangeSearch(1,3, root))
        self.assertEqual([], self.tree.rangeSearch(16,20, root))

    def test_insert(self):
        root = None
        root = self.tree.insert(7, root)
        root = self.tree.insert(4, root)
        root = self.tree.insert(13, root)
        root = self.tree.insert(1, root)
        root = self.tree.insert(6, root)
        root = self.tree.insert(10, root)
        root = self.tree.insert(15, root)
        root = self.tree.insert(3, root)

        self.assertEqual([1,3,4,6,7,10,13,15], self.tree.rangeSearch(1, 15, root))

    def test_delete(self):
        root = None
        root = self.tree.insert(7, root)
        root = self.tree.insert(4, root)
        root = self.tree.insert(13, root)
        root = self.tree.insert(1, root)
        root = self.tree.insert(6, root)
        root = self.tree.insert(10, root)
        root = self.tree.insert(15, root)
        root = self.tree.insert(3, root)
        
        self.tree.delete(root)
        self.tree.getTree(root)

        self.tree.delete(root)
        self.tree.getTree(root)

        self.tree.delete(root)
        self.tree.getTree(root)
        
        self.tree.delete(root)
        self.tree.getTree(root)

        self.tree.delete(root)
        self.tree.getTree(root)

        self.tree.delete(root)
        self.tree.getTree(root)

        self.tree.delete(root)
        self.tree.getTree(root)

        self.tree.delete(root)
        self.tree.getTree(root)

if __name__ == '__main__':
    unittest.main()
