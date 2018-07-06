import unittest
from singlyLinkedList import SinglyLinkedList

class SinglyLinkedListTest(unittest.TestCase):
    def setUp(self):
        self.linkedList = SinglyLinkedList()

    def test_addFirst(self):
        self.linkedList.addFirst('first')
        self.linkedList.addFirst('second')
        self.assertEqual(self.linkedList.toString(), 'second,first')

    def test_addLast(self):
        self.linkedList.addLast('first')
        self.linkedList.addLast('second')
        self.assertEqual(self.linkedList.toString(), 'first,second')

    def test_addFront(self):
        self.linkedList.addFront(2, 'end')
        self.linkedList.addFront(0, 'first')
        self.linkedList.addFront(2, 'end2')
        self.linkedList.addFront(1, 'second')
        self.linkedList.addFront(2, 'third')
        self.linkedList.addFront(1, 'second1')
        self.assertEqual(self.linkedList.toString(), 'first,second1,second,third,end,end2')

    def test_addBack(self):
        self.linkedList.addBack(2, 'end')
        self.linkedList.addBack(0, 'first')
        self.linkedList.addBack(2, 'end2')
        self.linkedList.addBack(1, 'second')
        self.linkedList.addBack(2, 'third')
        self.linkedList.addBack(1, 'second1')
        self.assertEqual(self.linkedList.toString(), 'end,first,second1,second,third,end2')

    def test_removeFirst(self):
        self.linkedList.addFirst('first')
        self.linkedList.addFirst('second')
        self.assertEqual(self.linkedList.removeFirst(), 'second')
        self.assertEqual(self.linkedList.toString(), 'first')
        self.assertEqual(self.linkedList.removeFirst(), 'first')
        self.assertIsNone(self.linkedList.removeFirst())

    def test_remove(self):
        self.linkedList.addFirst('third')
        self.linkedList.addFirst('second')
        self.linkedList.addFirst('first')
        self.assertIsNone(self.linkedList.remove(3))
        self.assertEqual(self.linkedList.remove(0), 'first')
        self.assertEqual(self.linkedList.remove(1), 'third')
        self.assertEqual(self.linkedList.toString(), 'second')

    def test_removeLast(self):
        self.assertIsNone(self.linkedList.removeLast())
        self.linkedList.addFirst('third')
        self.linkedList.addFirst('second')
        self.linkedList.addFirst('first')
        self.assertEqual(self.linkedList.removeLast(), 'third')
        self.assertEqual(self.linkedList.removeLast(), 'second')
        self.assertEqual(self.linkedList.removeLast(), 'first')
        self.assertIsNone(self.linkedList.removeLast())

    def test_size(self):
        self.assertEqual(self.linkedList.size(), 0)
        self.linkedList.addFirst('third')
        self.linkedList.addFirst('second')
        self.linkedList.addFirst('first')
        self.assertEqual(self.linkedList.size(), 3)

    def test_empty(self):
        self.assertTrue(self.linkedList.empty())
        self.linkedList.addFirst('third')
        self.linkedList.addFirst('second')
        self.linkedList.addFirst('first')
        self.assertFalse(self.linkedList.empty())

    def test_get(self):
        self.assertIsNone(self.linkedList.get(1))
        self.linkedList.addFirst('third')
        self.linkedList.addFirst('second')
        self.linkedList.addFirst('first')
        self.assertEqual(self.linkedList.get(1), 'second')
        self.assertIsNone(self.linkedList.get(3))

    def test_indexOf(self):
        self.assertEqual(self.linkedList.indexOf('test'), -1)
        self.linkedList.addFirst('third')
        self.linkedList.addFirst('second')
        self.linkedList.addFirst('first')
        self.assertEqual(self.linkedList.indexOf('first'), 0)
        self.assertEqual(self.linkedList.indexOf('second'), 1)
        self.assertEqual(self.linkedList.indexOf('third'), 2)
        self.assertEqual(self.linkedList.indexOf('thirdt'), -1)


if __name__ == '__main__':
    unittest.main()
