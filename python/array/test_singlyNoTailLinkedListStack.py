import unittest
from singlyNoTailLinkedListStack import Stack

class StackTest(unittest.TestCase):
    def setUp(self):
        self.stack = Stack()

    def test_push(self):
        self.stack.push(1)
        self.stack.push(2)
        self.stack.push(3)
        self.assertEqual(self.stack.toString(), '3,2,1')

    def test_top(self):
        self.assertIsNone(self.stack.top())
        self.stack.push(1)
        self.stack.push(2)
        self.assertEqual(self.stack.top(), 2)

    def test_pop(self):
        self.assertIsNone(self.stack.pop())
        self.stack.push(1)
        self.stack.push(2)
        self.assertEqual(self.stack.pop(), 2)
        self.assertEqual(self.stack.pop(), 1)
        self.assertIsNone(self.stack.pop())

    def test_empty(self):
        self.assertTrue(self.stack.empty())
        self.stack.push(1)
        self.stack.push(2)
        self.assertFalse(self.stack.empty())

if __name__ == '__main__':
    unittest.main()
