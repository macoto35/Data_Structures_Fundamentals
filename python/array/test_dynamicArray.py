import unittest
from dynamicArray import DynamicArray

class DynamicArrayTest(unittest.TestCase):
    def setUp(self):
        self.array = DynamicArray()

    def test_get(self):
        with self.assertRaises(IndexError):
            self.array.get(2)
            self.array.get(-1)
        self.array.pushBack('a')
        self.array.pushBack('b')
        self.array.pushBack('c')
        self.assertEqual(self.array.get(1), 'b')
        self.assertEqual(self.array.get(0), 'a')
        self.assertEqual(self.array.get(2), 'c')
        with self.assertRaises(IndexError):
            self.array.get(3)
    
    def test_pushBack(self):
        self.array.pushBack(1)
        self.assertEqual(self.array.length(), 1)
        self.assertEqual(self.array.cap(), 1)
        self.array.pushBack(2)
        self.assertEqual(self.array.length(), 2)
        self.assertEqual(self.array.cap(), 2)
        self.array.pushBack(3)
        self.assertEqual(self.array.length(), 3)
        self.assertEqual(self.array.cap(), 4)
        self.array.pushBack(4)
        self.assertEqual(self.array.length(), 4)
        self.assertEqual(self.array.cap(), 4)
        self.array.pushBack(5)
        self.assertEqual(self.array.toString(), '1,2,3,4,5')
        self.assertEqual(self.array.length(), 5)
        self.assertEqual(self.array.cap(), 8)

    def test_set(self):
        with self.assertRaises(IndexError):
            self.array.set(0, 'a')
            self.array.set(-1, 'a')
        self.array.pushBack('a')
        self.array.pushBack('b')
        self.array.pushBack('c')
        
        self.array.set(1, 'b1')
        self.assertEqual(self.array.toString(), 'a,b1,c')
        self.array.set(0, 'a1')
        self.assertEqual(self.array.toString(), 'a1,b1,c')
        self.array.set(2, 'c1')
        self.assertEqual(self.array.toString(), 'a1,b1,c1')
        with self.assertRaises(IndexError):
            self.array.set(3, 'd')

    def test_remove(self):
        self.assertIsNone(self.array.remove(1))
        self.assertIsNone(self.array.remove(-1))
        self.array.pushBack('a')
        self.array.pushBack('b')
        self.array.pushBack('c')
        self.array.pushBack('d')
        self.array.pushBack('e')
        self.assertEqual(self.array.remove(0), 'a')
        self.assertEqual(self.array.remove(3), 'e')
        self.assertEqual(self.array.remove(2), 'd')
        self.assertEqual(self.array.remove(0), 'b')
        self.assertEqual(self.array.remove(0), 'c')
        self.assertIsNone(self.array.remove(0))

if __name__ == '__main__':
    unittest.main()
