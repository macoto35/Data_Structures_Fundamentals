import unittest
from avlTree import AvlTree
from node import Node

class AvlTreeTest(unittest.TestCase):
    def setUp(self):
        self.tree = AvlTree()

    '''def test_insert(self):
                  13
             10        15
          5     11        16
        4   6
        root = self.tree.avlInsert(13, None)
        root = self.tree.avlInsert(10, root)
        root = self.tree.avlInsert(15, root)
        root = self.tree.avlInsert(5, root)
        root = self.tree.avlInsert(11, root)
        root = self.tree.avlInsert(16, root)
        root = self.tree.avlInsert(4, root)
        root = self.tree.avlInsert(6, root)

        self.assertEqual(13, root.key)
        self.assertEqual(10, root.left.key)
        self.assertEqual(15, root.right.key)
        self.assertEqual(5, root.left.left.key)
        self.assertEqual(11, root.left.right.key)
        self.assertEqual(16, root.right.right.key)
        self.assertEqual(4, root.left.left.left.key)
        self.assertEqual(6, root.left.left.right.key)'''

    def test_avlInsertRightRotate(self):
        root = self.tree.avlInsert(13, None)
        root = self.tree.avlInsert(10, root)
        root = self.tree.avlInsert(5, root)

        root = self.tree.getRoot(root)

        self.assertEqual(10, root.key)
        self.assertEqual(2, root.height)
        self.assertEqual(5, root.left.key)
        self.assertEqual(13, root.right.key)

    def test_avlInsertLeftRotate(self):
        root = self.tree.avlInsert(13, None)
        root = self.tree.avlInsert(15, root)
        root = self.tree.avlInsert(16, root)

        root = self.tree.getRoot(root)

        self.assertEqual(15, root.key)
        self.assertEqual(2, root.height)
        self.assertEqual(13, root.left.key)
        self.assertEqual(16, root.right.key)

    '''
         13
     10      15
   5   11      16
 4  6
     (7)
    '''
    def test_avlInsertLeftRightRotate(self):
        root = Node(13, None, 4)
        left = root.left = Node(10, root, 3)
        right = root.right = Node(15, root, 2)
        le = left.left = Node(5, left, 2)
        left.right = Node(11, left, 1)
        right.right = Node(16, right, 1)
        le.left = Node(4, le, 1)
        le.right = Node(6, le, 1)

        root = self.tree.avlInsert(7, root)

        root = self.tree.getRoot(root)
        print("avlInsertLeftRightRotate: ")
        self.tree.bfsPrint(root)

        self.assertEqual(6, root.left.key)
        self.assertEqual(5, root.left.left.key)
        self.assertIsNone(root.left.left.right)
        self.assertEqual(10, root.left.right.key)
        self.assertEqual(7, root.left.right.left.key)

    '''
         5
     2       7
   1   4   6   9
     3          16
              (15)
    '''
    def test_avlInsertRightLeftRotate(self):
        root = Node(5, None, 4)
        left = root.left = Node(2, root, 3)
        right = root.right = Node(7, root, 3)
        left.left = Node(1, left, 1)
        lr = left.right = Node(4, left, 2)
        right.left = Node(6, right, 1)
        rr = right.right = Node(9, right, 2)
        lr.left = Node(3, lr, 1)
        rr.right = Node(16, rr, 1)

        root = self.tree.avlInsert(15, root)

        root = self.tree.getRoot(root)
        print('avlInsertRightLeftRotate: ')
        self.tree.bfsPrint(root)

        self.assertEqual(7, root.right.key)
        self.assertEqual(6, root.right.left.key)
        self.assertEqual(15, root.right.right.key)
        self.assertEqual(2, root.right.right.height)
        self.assertEqual(9, root.right.right.left.key)
        self.assertEqual(1, root.right.right.left.height)
        self.assertEqual(16, root.right.right.right.key)

    '''
         5
     2       7
   1   4   6   9
    '''
    def test_avlRemove(self):
        root = Node(5, None, 3)
        left = root.left = Node(2, root, 2)
        right = root.right = Node(7, root, 2)
        left.left = Node(1, left, 1)
        left.right = Node(4, left, 1)
        right.left = Node(6, right, 1)
        right.right = Node(9, right, 1)

        for i in range(7):
            root = self.tree.avlRemove(root)
            print('avlRemove: ')
            self.tree.bfsPrint(root)


if __name__ == '__main__':
    unittest.main()
