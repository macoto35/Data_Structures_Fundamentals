import unittest
from singlyTailLinkedQueue import SinglyTailLinkedQueue

class SinglyTailLinkedQueueTest(unittest.TestCase):
    def setUp(self):
        self.queue = SinglyTailLinkedQueue()

    def test_enqueue(self):
        self.queue.enqueue(1)
        self.queue.enqueue(2)
        self.queue.enqueue(3)
        self.assertEqual(self.queue.toString(), '1,2,3')

    def test_dequeue(self):
        self.assertIsNone(self.queue.dequeue())
        self.queue.enqueue(1)
        self.queue.enqueue(2)
        self.queue.enqueue(3)
        self.assertEqual(self.queue.dequeue(), 1)
        self.assertEqual(self.queue.dequeue(), 2)
        self.assertEqual(self.queue.dequeue(), 3)
        self.assertIsNone(self.queue.dequeue())

    def test_empty(self):
        self.assertTrue(self.queue.empty())
        self.queue.enqueue(1)
        self.queue.enqueue(2)
        self.assertFalse(self.queue.empty())

if __name__ == '__main__':
    unittest.main()
