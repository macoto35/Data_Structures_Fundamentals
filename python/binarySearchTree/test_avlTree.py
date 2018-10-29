import unittest
from avlTree import AvlTree, Node

class avlTreeTest(unittest.TestCase):
    def setUp(self):
        self.tree = AvlTree()

    '''def test_find(self):
        root = Node(13, None)
        root.left = left = Node(10, root)
        root.right = right = Node(15, root)
        left.left = Node(5, left)
        left.right = Node(11, left)
        right.left = Node(14, right)
        right.right = Node(16, right)
        
        self.assertEqual(13, self.tree.find(13, root).key)
        self.assertEqual(5, self.tree.find(5, root).key)
        self.assertEqual(16, self.tree.find(16, root).key)
        self.assertEqual(16, self.tree.find(17, root).key)
        self.assertEqual(5, self.tree.find(3, root).key)

    def test_insert(self):
        root = Node(13, None)
        root = self.tree.insert(10, root)
        root = self.tree.insert(15, root)
        root = self.tree.insert(5, root)
        root = self.tree.insert(11, root)
        root = self.tree.insert(16, root)
        root = self.tree.insert(4, root)
        root = self.tree.insert(6, root)

        self.tree.result = []
        self.tree.getTree(root)
        self.assertEqual([4,5,6,10,11,13,15,16], self.tree.result)

    def test_avlInsertRightRotate(self):
        root = Node(13, None)
        root = self.tree.avlInsert(10, root)
        root = self.tree.avlInsert(5, root)
        
        self.assertEqual(10, root.key)
        self.assertEqual(2, root.height)
        self.assertEqual(5, root.left.key)
        self.assertEqual(13, root.right.key)

    def test_avlInsertLeftRotate(self):
        root = Node(13, None)
        root = self.tree.avlInsert(15, root)
        root = self.tree.avlInsert(16, root)
        
        self.assertEqual(15, root.key)
        self.assertEqual(2, root.height)
        self.assertEqual(13, root.left.key)
        self.assertEqual(16, root.right.key)'''

    def test_avlInsertLeftRightRotate(self):
        root = Node(13, None, 4)
        root.left = left = Node(10, root, 3)
        root.right = right = Node(15, root, 2)
        left.left = le = Node(5, left, 2)
        left.right = Node(11, left, 1)
        right.right = Node(16, right, 1)
        le.left = Node(4, le, 1)
        le.right = Node(6, le, 1)

        root = self.tree.avlInsert(7, root)
        
        print('root: ', root.key)
        print('1st 1: ', root.left.key)
        print('1st 2: ', root.right.key)
        print('2nd 1: ', root.left.left.key)
        print('2nd 2: ', root.left.right.key)
        print('2nd 3: ', root.right.left.key if root.right.left is not None else None)
        print('2nd 4: ', root.right.right.key if root.right.right is not None else None)
        print('3rd 1: ', root.left.left.left.key if root.left.left.left is not None else None)
        print('3rd 2: ', root.left.left.right.key if root.left.left.right is not None else None)
        print('3rd 3: ', root.left.right.left.key if root.left.right.left is not None else None)
        print('3rd 4: ', root.left.right.right.key if root.left.right.right is not None else None)
        print('3rd 5: ', root.right.left.left.key if root.right.left.left is not None else None)
        print('3rd 6: ', root.right.left.right.key if root.right.left.right is not None else None)
        print('3rd 7: ', root.right.right.left.key if root.right.right.left is not None else None)
        print('3rd 8: ', root.right.right.right.key if root.right.right.right is not None else None)
        
        print('root: ', root.height)
        print('1st 1: ', root.left.height)
        print('1st 2: ', root.right.height)
        print('2nd 1: ', root.left.left.height)
        print('2nd 2: ', root.left.right.height)
        print('2nd 3: ', root.right.left.height if root.right.left is not None else None)
        print('2nd 4: ', root.right.right.height if root.right.right is not None else None)
        print('3rd 1: ', root.left.left.left.height if root.left.left.left is not None else None)
        print('3rd 2: ', root.left.left.right.height if root.left.left.right is not None else None)
        print('3rd 3: ', root.left.right.left.height if root.left.right.left is not None else None)
        print('3rd 4: ', root.left.right.right.height if root.left.right.right is not None else None)
        print('3rd 5: ', root.right.left.left.height if root.right.left.left is not None else None)
        print('3rd 6: ', root.right.left.right.height if root.right.left.right is not None else None)
        print('3rd 7: ', root.right.right.left.height if root.right.right.left is not None else None)
        print('3rd 8: ', root.right.right.right.height if root.right.right.right is not None else None)

        self.assertEqual(6, root.left.key)
        self.assertEqual(5, root.left.left.key)
        self.assertIsNone(root.left.left.right)
        self.assertEqual(10, root.left.right.key)
        self.assertEqual(7, root.left.right.left.key)

    '''def test_avlInsertRightLeftRotate(self):
        root = Node(5, None, 4)
        root.left = left = Node(2, root, 3)
        root.right = right = Node(7, root, 3)
        left.left = Node(1, left, 1)
        left.right = lr = Node(4, left, 2)
        right.left = Node(6, right, 1)
        right.right = rr = Node(9, right, 2)
        lr.left = Node(3, lr, 1)
        rr.right = Node(16, rr, 1)

        root = self.tree.avlInsert(15, root)
        
        print('root: ', root.key)
        print('1st 1: ', root.left.key)
        print('1st 2: ', root.right.key)
        print('2nd 1: ', root.left.left.key)
        print('2nd 2: ', root.left.right.key)
        print('2nd 3: ', root.right.left.key)
        print('2nd 4: ', root.right.right.key)
        print('3rd 1: ', root.left.left.left.key if root.left.left.left is not None else None)
        print('3rd 2: ', root.left.left.right.key if root.left.left.right is not None else None)
        print('3rd 3: ', root.left.right.left.key if root.left.right.left is not None else None)
        print('3rd 4: ', root.left.right.right.key if root.left.right.right is not None else None)
        print('3rd 5: ', root.right.left.left.key if root.right.left.left is not None else None)
        print('3rd 6: ', root.right.left.right.key if root.right.left.right is not None else None)
        print('3rd 7: ', root.right.right.left.key if root.right.right.left is not None else None)
        print('3rd 8: ', root.right.right.right.key if root.right.right.right is not None else None)
        
        print('root: ', root.height)
        print('1st 1: ', root.left.height)
        print('1st 2: ', root.right.height)
        print('2nd 1: ', root.left.left.height)
        print('2nd 2: ', root.left.right.height)
        print('2nd 3: ', root.right.left.height)
        print('2nd 4: ', root.right.right.height)
        print('3rd 1: ', root.left.left.left.height if root.left.left.left is not None else None)
        print('3rd 2: ', root.left.left.right.height if root.left.left.right is not None else None)
        print('3rd 3: ', root.left.right.left.height if root.left.right.left is not None else None)
        print('3rd 4: ', root.left.right.right.height if root.left.right.right is not None else None)
        print('3rd 5: ', root.right.left.left.height if root.right.left.left is not None else None)
        print('3rd 6: ', root.right.left.right.height if root.right.left.right is not None else None)
        print('3rd 7: ', root.right.right.left.height if root.right.right.left is not None else None)
        print('3rd 8: ', root.right.right.right.height if root.right.right.right is not None else None)
        
        self.assertEqual(7, root.right.key)
        self.assertEqual(6, root.right.left.key)
        self.assertEqual(15, root.right.right.key)
        self.assertEqual(2, root.right.right.height)
        self.assertEqual(9, root.right.right.left.key)
        self.assertEqual(1, root.right.right.left.height)
        self.assertEqual(16, root.right.right.right.key)'''

if __name__ == '__main__':
    unittest.main()
