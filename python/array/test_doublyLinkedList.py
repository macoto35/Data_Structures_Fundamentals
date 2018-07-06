import unittest
from doublyLinkedList import DoublyLinkedList

class DoublyLinkedListTest(unittest.TestCase):
    def setUp(self):
        self.linkedList = DoublyLinkedList()

    def test_addFirst(self):
        self.linkedList.addFirst('first')
        self.linkedList.addFirst('second')
        self.linkedList.addFirst('third')
        self.assertEqual(self.linkedList.toString(), 'third,second,first')

    def test_addLast(self):
        self.linkedList.addLast('first')
        self.linkedList.addLast('second')
        self.linkedList.addLast('third')
        self.assertEqual(self.linkedList.toString(), 'first,second,third')

    def test_addFront(self):
        self.linkedList.addFront(2, 'first')
        self.linkedList.addFront(0, 'second')
        self.linkedList.addFront(1, 'third')
        self.linkedList.addFront(1, 'fourth')
        self.linkedList.addFront(0, 'fifth')
        self.linkedList.addFront(7, 'sixth')
        self.assertEqual(self.linkedList.toString(), 'fifth,second,fourth,third,first,sixth')

    def test_addBack(self):
        self.linkedList.addBack(2, 'first')
        self.linkedList.addBack(0, 'second')
        self.linkedList.addBack(1, 'third')
        self.linkedList.addBack(1, 'fourth')
        self.linkedList.addBack(0, 'fifth')
        self.linkedList.addBack(7, 'sixth')
        self.assertEqual(self.linkedList.toString(), 'first,fifth,second,fourth,third,sixth')

    def test_removeFront(self):
        self.assertIsNone(self.linkedList.removeFront())
        self.linkedList.addLast('first')
        self.linkedList.addLast('second')
        self.linkedList.addLast('third')
        self.assertEqual(self.linkedList.removeFront(), 'first')
        self.assertEqual(self.linkedList.toString(), 'second,third')

    def test_removeBack(self):
        self.assertIsNone(self.linkedList.removeBack())
        self.linkedList.addLast('first')
        self.linkedList.addLast('second')
        self.linkedList.addLast('third')
        self.assertEqual(self.linkedList.removeBack(), 'third')
        self.assertEqual(self.linkedList.toString(), 'first,second')

    def test_remove(self):
        self.assertIsNone(self.linkedList.remove(1))
        self.linkedList.addLast('first')
        self.linkedList.addLast('second')
        self.linkedList.addLast('third')
        self.assertIsNone(self.linkedList.remove(3))
        self.assertEqual(self.linkedList.remove(2), 'third')
        self.assertEqual(self.linkedList.remove(0), 'first')
        self.assertEqual(self.linkedList.remove(0), 'second')
        self.assertIsNone(self.linkedList.remove(0))

    def test_size(self):
        self.assertEqual(self.linkedList.size(), 0)
        self.linkedList.addLast('first')
        self.linkedList.addLast('second')
        self.linkedList.addLast('third')
        self.assertEqual(self.linkedList.size(), 3)
    
    def test_empty(self):
        self.assertTrue(self.linkedList.empty())
        self.linkedList.addLast('first')
        self.linkedList.addLast('second')
        self.linkedList.addLast('third')
        self.assertFalse(self.linkedList.empty())

    def test_get(self):
        self.assertIsNone(self.linkedList.get(1))
        self.linkedList.addLast('first')
        self.linkedList.addLast('second')
        self.linkedList.addLast('third')
        self.assertEqual(self.linkedList.get(0), 'first')
        self.assertEqual(self.linkedList.get(1), 'second')
        self.assertEqual(self.linkedList.get(2), 'third')
        self.assertIsNone(self.linkedList.get(3))

    def test_indexOf(self):
        self.assertEqual(self.linkedList.indexOf('test'), -1)
        self.linkedList.addLast('first')
        self.linkedList.addLast('second')
        self.linkedList.addLast('third')
        self.assertEqual(self.linkedList.indexOf('first'), 0)
        self.assertEqual(self.linkedList.indexOf('second'), 1)
        self.assertEqual(self.linkedList.indexOf('third'), 2)
        self.assertEqual(self.linkedList.indexOf('test'), -1)

if __name__ == '__main__':
    unittest.main()
