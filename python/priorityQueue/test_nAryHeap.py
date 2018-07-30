import unittest
from nAryHeap import NAryHeap

class NAryHeapTest (unittest.TestCase):
    def setUp(self):
        #self.heap = NAryHeap(3)
        self.heap = NAryHeap(5)

    def test_insert(self):
        self.heap.insert(0)
        self.heap.insert(1)
        self.heap.insert(2)
        self.heap.insert(3)
        self.heap.insert(4)
        self.heap.insert(5)
        self.heap.insert(6)
        self.heap.insert(7)
        self.heap.insert(8)
        self.heap.insert(9)

        #self.assertEqual('9,5,8,2,0,3,4,1,6,7', self.heap.toString())
        self.assertEqual('9,8,1,2,3,4,0,5,6,7', self.heap.toString())

    def test_extractMax(self):
        self.heap.insert(0)
        self.heap.insert(1)
        self.heap.insert(2)
        self.heap.insert(3)
        self.heap.insert(4)
        self.heap.insert(5)
        self.heap.insert(6)
        self.heap.insert(7)
        self.heap.insert(8)
        self.heap.insert(9)

        self.assertEqual(9, self.heap.extractMax())
        self.assertEqual(8, self.heap.extractMax())
        self.assertEqual(7, self.heap.extractMax())
        self.assertEqual(6, self.heap.extractMax())

    def test_remove(self):
        with self.assertRaises(IndexError):
            self.heap.remove(0)

        self.heap.insert(0)
        self.heap.insert(1)
        self.heap.insert(2)
        self.heap.insert(3)
        self.heap.insert(4)
        self.heap.insert(5)
        self.heap.insert(6)
        self.heap.insert(7)
        self.heap.insert(8)
        self.heap.insert(9)

        self.heap.remove(2)
        #self.assertEqual('9,5,7,2,0,3,4,1,6', self.heap.toString())
        self.assertEqual('9,8,7,2,3,4,0,5,6', self.heap.toString())

    def test_changePriority(self):
        self.heap.insert(0)
        self.heap.insert(1)
        self.heap.insert(2)
        self.heap.insert(3)
        self.heap.insert(4)
        self.heap.insert(5)
        self.heap.insert(6)
        self.heap.insert(7)
        self.heap.insert(8)
        self.heap.insert(9)

        self.heap.changePriority(11, 2)
        #self.assertEqual('11,5,9,2,0,3,4,1,6,7', self.heap.toString())
        self.assertEqual('11,8,9,2,3,4,0,5,6,7', self.heap.toString())

if __name__ == '__main__':
    unittest.main()
