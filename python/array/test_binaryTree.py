import unittest
from binaryTree import BinaryTree
from treeNode import TreeNode

class BinaryTreeTest(unittest.TestCase):
    def setUp(self):
        cathy = TreeNode('Cathy')
        cathy.left = TreeNode('Alex')
        cathy.right = TreeNode('Frank')

        sam = TreeNode('Sam')
        sam.left = TreeNode('Nancy')
        violet = TreeNode('Violet')
        violet.left = TreeNode('Tony')
        violet.right = TreeNode('Wendy')
        sam.right = violet

        les = TreeNode('Les')
        les.left = cathy
        les.right = sam
        
        self.tree = BinaryTree(les)

    def test_depthFirstPreOrderTraverse(self):
        self.assertEqual(self.tree.depthFirstPreOrderTraverse(), 'Les,Cathy,Alex,Frank,Sam,Nancy,Violet,Tony,Wendy')

    def test_depthFirstInOrderTraverse(self):
        self.assertEqual(self.tree.depthFirstInOrderTraverse(), 'Alex,Cathy,Frank,Les,Nancy,Sam,Tony,Violet,Wendy')

    def test_depthFirstPostOrderTraverse(self):
        self.assertEqual(self.tree.depthFirstPostOrderTraverse(), 'Alex,Frank,Cathy,Nancy,Tony,Wendy,Violet,Sam,Les')

    def test_breathFirst(self):
        self.assertEqual(self.tree.breathFirst(), 'Les,Cathy,Sam,Alex,Frank,Nancy,Violet,Tony,Wendy')

if __name__ == '__main__':
    unittest.main()
