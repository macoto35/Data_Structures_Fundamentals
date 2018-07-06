import unittest
from arrayQueue import ArrayQueue

class ArrayQueueTest(unittest.TestCase):
    def setUp(self):
        self.queue = ArrayQueue()

    def test_enqueue(self):
        self.queue.enqueue(1)
        self.queue.enqueue(2)
        self.queue.enqueue(3)
        self.assertEqual(self.queue.toString(), '1,2,3')
        self.queue.enqueue(4)
        with self.assertRaises(IndexError):
            self.queue.enqueue(5)

    def test_dequeue(self):
        self.assertIsNone(self.queue.dequeue())
        self.queue.enqueue(1)
        self.queue.enqueue(2)
        self.assertEqual(self.queue.dequeue(), 1)
        self.assertEqual(self.queue.dequeue(), 2)
        self.assertIsNone(self.queue.dequeue())

    def test_empty(self):
        self.assertTrue(self.queue.empty())
        self.queue.enqueue(1)
        self.queue.enqueue(2)
        self.assertFalse(self.queue.empty())

if __name__ == '__main__':
    unittest.main()
