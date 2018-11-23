import unittest
from orderStatistics import OrderStatistics
from node import Node

class OrderStatisticsTest(unittest.TestCase):
    def setUp(self):
        self.stat = OrderStatistics()

    def test_find(self):
        root = Node(4, None, 2, 3)
        root.left = Node(3, root, 1, 1)
        root.right = Node(5, root, 1, 1)

        self.assertEqual(4, self.stat.find(4, root).key)
        self.assertEqual(3, self.stat.find(3, root).key)
        self.assertEqual(5, self.stat.find(5, root).key)
        self.assertEqual(3, self.stat.find(1, root).key)
        self.assertEqual(5, self.stat.find(6, root).key)

    def test_insert(self):
       root = self.stat.insert(1, None)
       root = self.stat.insert(2, root)
       root = self.stat.insert(3, root)

       self.assertEqual('1,2,3', self.stat.bfsPrint(root))

    def test_avlInsert(self):
       root = self.stat.avlInsert(1, None)
       root = self.stat.avlInsert(2, root)
       root = self.stat.avlInsert(3, root)
       root = self.stat.avlInsert(4, root)
       root = self.stat.avlInsert(5, root)
       root = self.stat.avlInsert(6, root)
       self.assertEqual('4,2,5,1,3,6', self.stat.bfsPrint(root))
       self.assertEqual('3,2,2,1,1,1', self.stat.bfsPrint(root, 'height'))
       self.assertEqual('6,3,2,1,1,1', self.stat.bfsPrint(root, 'size'))

       root = self.stat.avlInsert(6, None)
       root = self.stat.avlInsert(5, root)
       root = self.stat.avlInsert(4, root)
       root = self.stat.avlInsert(3, root)
       root = self.stat.avlInsert(2, root)
       root = self.stat.avlInsert(1, root)
       self.assertEqual('3,2,5,1,4,6', self.stat.bfsPrint(root))
       self.assertEqual('3,2,2,1,1,1', self.stat.bfsPrint(root, 'height'))
       self.assertEqual('6,2,3,1,1,1', self.stat.bfsPrint(root, 'size'))

       root = Node(3, None, 4, 4)
       root.right = Node(8, root, 3, 3)
       root.right.left = Node(6, root.right, 2, 2)
       root.right.left.right = Node(7, root.right.left, 1, 1)
       result = self.stat.rebalance(root)
       self.assertEqual('6,3,8,7', self.stat.bfsPrint(result))
       self.assertEqual('3,1,2,1', self.stat.bfsPrint(result, 'height'))
       self.assertEqual('4,1,2,1', self.stat.bfsPrint(result, 'size'))

       root = Node(8, None, 4, 4)
       root.left = Node(2, root, 3, 3)
       root.left.right = Node(7, root.left, 2, 2)
       root.left.right.left = Node(5, root.left.right, 1, 1)
       result = self.stat.rebalance(root)
       self.assertEqual('7,2,8,5', self.stat.bfsPrint(result))
       self.assertEqual('3,2,1,1', self.stat.bfsPrint(result, 'height'))
       self.assertEqual('4,2,1,1', self.stat.bfsPrint(result, 'size'))

    def test_orderStatistic(self):
        root = Node(15, None, 4, 9)
        root.left = left = Node(2, root, 3, 6)
        root.right = right = Node(16, root, 2, 2)
        left.left = Node(1, left, 1, 1)
        left.right = lr = Node(4, left, 2, 4)
        right.right = Node(17, right, 1, 1)
        lr.left = Node(3, lr, 1, 1)
        lr.right = lrr = Node(7, lr, 2, 2)
        lrr.left = Node(6, lrr, 1, 1)

        self.assertEqual(1, self.stat.orderStatistic(root, 1).key)
        self.assertEqual(2, self.stat.orderStatistic(root, 2).key)
        self.assertEqual(3, self.stat.orderStatistic(root, 3).key)
        self.assertEqual(4, self.stat.orderStatistic(root, 4).key)
        self.assertEqual(6, self.stat.orderStatistic(root, 5).key)
        self.assertEqual(7, self.stat.orderStatistic(root, 6).key)
        self.assertEqual(15, self.stat.orderStatistic(root, 7).key)
        self.assertEqual(16, self.stat.orderStatistic(root, 8).key)
        self.assertEqual(17, self.stat.orderStatistic(root, 9).key)

        self.assertEqual(1, self.stat.reverseOrderStatistic(root, 1))
        self.assertEqual(2, self.stat.reverseOrderStatistic(root, 2))
        self.assertEqual(3, self.stat.reverseOrderStatistic(root, 3))
        self.assertEqual(4, self.stat.reverseOrderStatistic(root, 4))
        self.assertEqual(5, self.stat.reverseOrderStatistic(root, 6))
        self.assertEqual(6, self.stat.reverseOrderStatistic(root, 7))
        self.assertEqual(7, self.stat.reverseOrderStatistic(root, 15))
        self.assertEqual(8, self.stat.reverseOrderStatistic(root, 16))
        self.assertEqual(9, self.stat.reverseOrderStatistic(root, 17))
        

if __name__ == '__main__':
    unittest.main()
