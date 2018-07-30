import unittest
from binaryMinHeap import BinaryMinHeap

class BinaryMinHeapTest(unittest.TestCase):
    def setUp(self):
        self.heap = BinaryMinHeap()

    def test_insert(self):
        self.heap.insert(6)
        self.heap.insert(5)
        self.heap.insert(4)
        self.heap.insert(3)
        self.heap.insert(2)
        self.heap.insert(1)
        self.heap.insert(0)
        self.assertEqual(self.heap.toString(), '0,3,1,6,4,5,2')

        self.heap.insert(11)
        self.heap.insert(12)
        self.heap.insert(13)
        self.heap.insert(14)
        self.heap.insert(15)
        self.heap.insert(16)
        self.heap.insert(17)
        self.heap.insert(18)
        with self.assertRaises(IndexError):
            self.heap.insert(19)

    def test_extractMin(self):
        self.heap.insert(6)
        self.heap.insert(5)
        self.heap.insert(4)
        self.heap.insert(3)
        self.heap.insert(2)
        self.heap.insert(1)
        self.heap.insert(0)

        self.assertEqual(self.heap.extractMin(), 0)
        self.assertEqual(self.heap.extractMin(), 1)
        self.assertEqual(self.heap.extractMin(), 2)
        self.assertEqual(self.heap.extractMin(), 3)
        self.assertEqual(self.heap.extractMin(), 4)
        self.assertEqual(self.heap.extractMin(), 5)
        self.assertEqual(self.heap.extractMin(), 6)
        with self.assertRaises(IndexError):
            self.heap.extractMin()

    def test_remove(self):
        self.heap.insert(6)
        self.heap.insert(5)
        self.heap.insert(4)
        self.heap.insert(3)
        self.heap.insert(2)
        self.heap.insert(1)
        self.heap.insert(0)

        self.heap.remove(5)
        self.assertEqual(self.heap.toString(), '0,3,1,6,4,2')

        self.heap.remove(0)
        self.heap.remove(0)
        self.heap.remove(0)
        self.heap.remove(0)
        self.heap.remove(0)
        self.heap.remove(0)
        with self.assertRaises(IndexError):
            self.heap.remove(0)

    def test_changePriority(self):
        self.heap.insert(6)
        self.heap.insert(5)
        self.heap.insert(4)
        self.heap.insert(3)
        self.heap.insert(2)
        self.heap.insert(1)
        self.heap.insert(0)

        self.heap.changePriority(2, 7)
        self.assertEqual(self.heap.toString(), '0,3,2,6,4,5,7')
        self.heap.changePriority(3, 1)
        self.assertEqual(self.heap.toString(), '0,1,2,3,4,5,7')


if __name__ == '__main__':
    unittest.main()
