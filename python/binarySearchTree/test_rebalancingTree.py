import unittest
from rebalancingTree import RebalancingTree
from node import Node

class RebalancingTreeTest(unittest.TestCase):
    def setUp(self):
        self.tree = RebalancingTree()

    def test_rotateRight1(self):
        root = Node(12, None)
        left = root.left = Node(7, root)
        root.right = Node(15, root)
        left.left = Node(5, left)
        left.right = Node(9, left)

        self.tree.rotateRight(root)
        root = self.tree.getRoot(root)

        self.assertEqual(7, root.key)
        self.assertEqual(5, root.left.key)
        self.assertEqual(12, root.right.key)
        self.assertEqual(9, root.right.left.key)
        self.assertEqual(15, root.right.right.key)

    def test_rotateRight2(self):
        root = Node(12, None)
        left = root.left = Node(7, root)
        left.left = Node(5, left)

        self.tree.rotateRight(root)
        root = self.tree.getRoot(root)

        self.assertEqual(7, root.key)
        self.assertEqual(5, root.left.key)
        self.assertEqual(12, root.right.key)

    def test_rotateRight3(self):
        realRoot = Node(20, None)
        root = realRoot.left = Node(12, realRoot)
        left = root.left = Node(7, root)
        root.right = Node(15, root)
        left.left = Node(5, left)
        left.right = Node(9, left)

        self.tree.rotateRight(root)

        self.assertEqual(20, realRoot.key)
        self.assertEqual(7, realRoot.left.key)
        self.assertEqual(5, realRoot.left.left.key)
        self.assertEqual(12, realRoot.left.right.key)
        self.assertEqual(9, realRoot.left.right.left.key)
        self.assertEqual(15, realRoot.left.right.right.key)

    def test_rotateLeft1(self):
        root = Node(12, None)
        root.left = Node(7, root)
        right = root.right = Node(17, root)
        right.left = Node(14, right)
        right.right = Node(20, right)

        self.tree.rotateLeft(root)
        root = self.tree.getRoot(root)

        self.assertEqual(17, root.key)
        self.assertEqual(12, root.left.key)
        self.assertEqual(20, root.right.key)
        self.assertEqual(7, root.left.left.key)
        self.assertEqual(14, root.left.right.key)

    def test_rotateLeft2(self):
        root = Node(12, None)
        right = root.right = Node(17, root)
        right.right = Node(20, right)

        self.tree.rotateLeft(root)
        root = self.tree.getRoot(root)

        self.assertEqual(17, root.key)
        self.assertEqual(12, root.left.key)
        self.assertEqual(20, root.right.key)

    def test_rotateLeft3(self):
        realRoot = Node(6, None)
        root = realRoot.right = Node(12, realRoot)
        root.left = Node(7, root)
        right = root.right = Node(17, root)
        right.left = Node(14, right)
        right.right = Node(20, right)

        self.tree.rotateLeft(root)

        self.assertEqual(6, realRoot.key)
        self.assertEqual(17, realRoot.right.key)
        self.assertEqual(12, realRoot.right.left.key)
        self.assertEqual(20, realRoot.right.right.key)
        self.assertEqual(7, realRoot.right.left.left.key)
        self.assertEqual(14, realRoot.right.left.right.key)



if __name__ == '__main__':
    unittest.main()
