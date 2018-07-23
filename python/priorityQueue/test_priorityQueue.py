import unittest
from priorityQueue import PriorityQueue

class PriorityQueueTest(unittest.TestCase):
    def setUp(self):
        self.pq = PriorityQueue()

    def test_parent(self):
        self.assertEqual(self.pq.parent(1), 0)
        self.assertEqual(self.pq.parent(2), 1)

    def test_leftChild(self):
        self.assertEqual(self.pq.leftChild(2), 4)
        self.assertEqual(self.pq.leftChild(3), 6)

    def test_rightChild(self):
        self.assertEqual(self.pq.rightChild(2), 5)
        self.assertEqual(self.pq.rightChild(3), 7)

    def test_shiftUp(self):
        self.pq.insert(42)
        self.pq.insert(29)
        self.pq.insert(18)
        self.pq.insert(14)
        self.pq.insert(7)
        self.pq.insert(32)
        self.pq.insert(12)
        self.pq.insert(11)

        self.pq.shiftUp(6)
        self.assertEqual(self.pq.toString(), '42,29,32,14,7,18,12,11')

    def test_shiftDown(self):
        self.pq.insert(13)
        self.pq.insert(29)
        self.pq.insert(18)
        self.pq.insert(14)
        self.pq.insert(7)
        self.pq.insert(18)
        self.pq.insert(12)
        self.pq.insert(11)
        
        self.pq.shiftDown(1)
        self.assertEqual(self.pq.toString(), '29,14,18,13,7,18,12,11')

    def test_insert(self):
        self.pq.insert(42)
        self.pq.insert(29)
        self.pq.insert(18)
        self.pq.insert(14)
        self.pq.insert(7)
        self.pq.insert(18)
        self.pq.insert(12)
        self.pq.insert(11)
        self.pq.insert(1)
        self.pq.insert(2)
        self.pq.insert(3)
        self.pq.insert(10)
        self.pq.insert(11)
        self.pq.insert(9)
        self.pq.insert(8)

        self.assertEqual(self.pq.toString(), '42,29,18,14,7,18,12,11,1,2,3,10,11,9,8')
        with self.assertRaises(IndexError):
            self.pq.insert(3)

    def test_extractMax(self):
        self.pq.insert(42)
        self.pq.insert(29)
        self.pq.insert(18)
        self.pq.insert(14)
        self.pq.insert(7)
        self.pq.insert(18)
        self.pq.insert(12)
        self.pq.insert(11)
        
        self.assertEqual(self.pq.extractMax(), 42)
        self.assertEqual(self.pq.toString(), '29,14,18,11,7,18,12')

    def test_remove(self):
        with self.assertRaises(IndexError):
            self.pq.remove(1)

        self.pq.insert(42)
        self.pq.insert(29)
        self.pq.insert(18)
        self.pq.insert(14)
        self.pq.insert(7)
        self.pq.insert(18)
        self.pq.insert(12)
        self.pq.insert(11)
        
        self.pq.remove(4)
        self.assertEqual(self.pq.toString(), '42,29,18,11,7,18,12')

    def test_changePriority(self):
        self.pq.insert(42)
        self.pq.insert(29)
        self.pq.insert(18)
        self.pq.insert(14)
        self.pq.insert(7)
        self.pq.insert(18)
        self.pq.insert(12)
        self.pq.insert(11)

        self.pq.changePriority(6, 32)
        self.assertEqual(self.pq.toString(), '42,29,32,14,7,18,12,11')

if __name__ == '__main__':
    unittest.main()
