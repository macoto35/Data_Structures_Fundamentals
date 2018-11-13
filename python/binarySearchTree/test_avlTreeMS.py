import unittest
from avlTreeMs import AvlTreeMs
from node import Node

class avlTreeMsTest(unittest.TestCase):
    def setUp(self):
        self.tree = AvlTreeMs()

    def _getR1Left(self):
        root = Node(3, None, 4)
        root.left = left = Node(2, root, 2)
        root.right = right = Node(5, root, 3)
        left.left = Node(1, left, 1)
        right.left = Node(4, right, 1)
        right.right = rr = Node(7, right, 2)
        rr.left = Node(6, rr, 1)

        return root

    def _getR1WithRootLeft(self):
        root = Node(3, None, 5)
        root.left = left = Node(2, root, 2)
        root.right = right = Node(5, root, 4)
        left.left = Node(1, left, 1)
        right.left = Node(4, right, 1)
        right.right = rr = Node(8, right, 3)
        rr.left = Node(7, rr, 2)
        rr.left.left = Node(6, rr, 1)

        return root
    
    def _getR2Left(self):
        root = Node(9, None, 3)
        root.right = Node(11, root, 2)
        root.right.left = Node(10, root.right, 1)

        return root
    
    def _getR2WithSmallHeightLeft(self):
        root = Node(9, None, 1)

        return root

    def test_mergeWithRootLeft(self):
        root = Node(8, Node, 1)
        self.assertEqual([8, 3, 9, 2, 5, 11, 1, 4, 7, 10, 6], self.tree.printBfs(self.tree.mergeWithRoot(self._getR1Left(), self._getR2Left(), root)))

    def test_mergeLeft(self):
        self.assertEqual([8, 3, 9, 2, 5, 11, 1, 4, 7, 10, 6], self.tree.printBfs(self.tree.merge(self._getR1WithRootLeft(), self._getR2Left())))

    def test_avlTreeMergeWithRootBigLeft(self):
        result = self.tree.avlMerge(self._getR1WithRootLeft(), self._getR2WithSmallHeightLeft())
        self.assertEqual([3, 2, 7, 1, 5, 8, 4, 6, 9], self.tree.printBfs(result))
        self.assertEqual([4, 2, 3, 1, 2, 2, 1, 1, 1], self.tree.printBfsHeight(result))

    def _getR1Right(self):
        root = Node(1, None, 2)
        root.right = Node(2, root, 1)
        
        return root

    def _getR1WithRootRight(self):
        root = Node(1, None, 3)
        root.right = Node(3, root, 2)
        root.right.left = Node(2, root.right, 1)
        
        return root

    def _getR2Right(self):
        root = Node(6, None, 5)
        root.left = left = Node(5, root, 2)
        root.right = right = Node(8, root, 4)
        left.left = Node(4, left, 1)
        right.left = Node(7, right, 1)
        right.right = rr = Node(11, right, 3)
        rr.left = Node(10, rr, 2)
        rr.left.left = Node(9, rr.left, 1)
        
        return root

    def test_mergeWithRootRight(self):
        root = Node(3, None, 1)
        self.assertEqual([3, 1, 6, 2, 5, 8, 4, 7, 11, 10, 9], self.tree.printBfs(self.tree.mergeWithRoot(self._getR1Right(), self._getR2Right(), root)))

    def test_mergeRight(self):
        self.assertEqual([3, 1, 6, 2, 5, 8, 4, 7, 11, 10, 9], self.tree.printBfs(self.tree.merge(self._getR1WithRootRight(), self._getR2Right())))

    def test_avlTreeMergeWithRootBigRight(self):
        result = self.tree.avlMerge(self._getR1WithRootRight(), self._getR2Right())

        self.assertEqual([6, 3, 8, 1, 5, 7, 11, 2, 4, 10, 9], self.tree.printBfs(result))
        self.assertEqual([5, 3, 4, 2, 2, 1, 3, 1, 1, 2, 1], self.tree.printBfsHeight(result))

    def test_split(self):
        root = Node(20, None, 6)
        root.left = left = Node(8, root, 5)
        root.right = Node(22, root, 1)
        left.left = Node(5, left, 1)
        left.right = right = Node(12, left, 4)
        right.left = Node(9, right, 1)
        right.right = rr = Node(16, right, 3)
        rr.left = rrl = Node(14, rr, 2)
        rr.right = Node(17, rr, 1)
        rrl.left = Node(13, rrl, 1)
        rrl.right = Node(15, rrl, 1)

        result1, result2 = self.tree.split(root, 14)
        self.assertEqual([8,5,12,9,13], self.tree.printBfs(result1))
        self.assertEqual([20,16,22,15,17], self.tree.printBfs(result2))
    
    def test_avlSplit(self):
        root = Node(20, None, 6)
        root.left = left = Node(8, root, 5)
        root.right = Node(22, root, 1)
        left.left = Node(5, left, 1)
        left.right = right = Node(12, left, 4)
        right.left = Node(9, right, 1)
        right.right = rr = Node(16, right, 3)
        rr.left = rrl = Node(14, rr, 2)
        rr.right = Node(17, rr, 2)
        rr.right.right = Node(18, rr.right, 1)
        rrl.left = Node(13, rrl, 1)
        rrl.right = Node(15, rrl, 1)

        result1, result2 = self.tree.avlSplit(root, 14)
        self.assertEqual([8,5,12,9,13], self.tree.printBfs(result1))
        self.assertEqual([17,16,20,15,18,22], self.tree.printBfs(result2))


if __name__ == '__main__':
    unittest.main()
