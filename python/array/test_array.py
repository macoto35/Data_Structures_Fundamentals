import unittest
from newarray import NewArray

class NewArrayTest(unittest.TestCase):
    def setUp(self):
        self.array = NewArray()

    def test_addLast(self):
        self.assertEqual(self.array.addLast('first'), True)
        self.assertEqual(self.array.toString(), 'first')

    def test_add(self):
        self.assertEqual(self.array.add(0, 'first'), True)
        self.assertEqual(self.array.add(1, 'third'), True)
        self.assertEqual(self.array.add(1, 'second'), True)
        self.assertEqual(self.array.toString(), 'first, second, third')

    def test_addFirst(self):
        self.assertEqual(self.array.addFirst('first'), True)
        self.assertEqual(self.array.toString(), 'first')
        self.assertEqual(self.array.addFirst('second'), True)
        self.assertEqual(self.array.toString(), 'second, first')

    def test_remove(self):
        self.array.addFirst('first')
        self.array.addFirst('second')
        self.array.addFirst('third')
        self.array.addFirst('fourth')
        self.array.addFirst('fifth')
        self.assertEqual(self.array.toString(), 'fifth, fourth, third, second, first')
        self.assertEqual(self.array.remove(1), 'fourth')
        self.assertEqual(self.array.toString(), 'fifth, third, second, first')

    def test_removeFirst(self):
        self.array.addFirst('first')
        self.array.addFirst('second')
        self.array.addFirst('third')
        self.array.addFirst('fourth')
        self.array.addFirst('fifth')
        self.assertEqual(self.array.removeFirst(), 'fifth')

    def test_removeLast(self):
        self.array.addFirst('first')
        self.array.addFirst('second')
        self.array.addFirst('third')
        self.assertEqual(self.array.removeLast(), 'first')
        self.assertEqual(self.array.removeLast(), 'second')
        self.assertEqual(self.array.removeLast(), 'third')
        self.assertIsNone(self.array.removeLast())

    def test_get(self):
        self.array.addFirst('first')
        self.array.addFirst('second')
        self.array.addFirst('third')
        self.assertEqual(self.array.get(0), 'third')
        self.assertEqual(self.array.get(1), 'second')
        self.assertEqual(self.array.get(2), 'first')
        self.assertIsNone(self.array.get(7))

    def test_size(self):
        self.assertEqual(self.array.length(), 0)
        self.array.addFirst('first')
        self.array.addFirst('second')
        self.array.addFirst('third')
        self.assertEqual(self.array.length(), 3)

    def test_indexof(self):
        self.array.addFirst('first')
        self.array.addFirst('second')
        self.array.addFirst('third')
        self.assertEqual(self.array.indexOf('third'), 0)
        self.assertEqual(self.array.indexOf('second'), 1)
        self.assertEqual(self.array.indexOf('first'), 2)
        self.assertEqual(self.array.indexOf('fourth'), -1)


if __name__ == '__main__':
    unittest.main()
