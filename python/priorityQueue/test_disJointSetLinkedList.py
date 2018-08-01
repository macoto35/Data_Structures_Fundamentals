import unittest
from disJointSetLinkedList import DisJointSetLinkedList

class DisJointSetLinkedListTest(unittest.TestCase):
    
    def setUp(self):
        self.set = DisJointSetLinkedList()

    def test_find(self):
        self.set.makeSet(0)
        self.set.makeSet(1)
        self.set.makeSet(2)
        self.set.makeSet(3)
        self.set.makeSet(4)
        self.set.makeSet(5)
        self.set.makeSet(6)
        self.set.makeSet(7)
        self.set.makeSet(8)

        self.assertEqual(0, self.set.find(0))
        self.assertEqual(1, self.set.find(1))
        self.assertEqual(2, self.set.find(2))
        self.assertEqual(3, self.set.find(3))
        self.assertEqual(4, self.set.find(4))
        self.assertEqual(5, self.set.find(5))
        self.assertEqual(6, self.set.find(6))
        self.assertEqual(7, self.set.find(7))
        self.assertEqual(8, self.set.find(8))

    def test_union(self):
        self.set.makeSet(0)
        self.set.makeSet(1)
        self.set.makeSet(2)
        self.set.makeSet(3)
        self.set.makeSet(4)
        self.set.makeSet(5)
        self.set.makeSet(6)
        self.set.makeSet(7)
        self.set.makeSet(8)

        self.set.union(8, 2)
        self.assertEqual(2, self.set.find(8))
        self.assertEqual(2, self.set.find(2))

        self.set.union(2, 1)
        self.assertEqual(1, self.set.find(8))
        self.assertEqual(1, self.set.find(2))
        self.assertEqual(1, self.set.find(1))
        
        self.set.union(1, 3)
        self.assertEqual(3, self.set.find(8))
        self.assertEqual(3, self.set.find(2))
        self.assertEqual(3, self.set.find(1))
        self.assertEqual(3, self.set.find(3))

        self.set.union(3, 6)
        self.assertEqual(6, self.set.find(8))
        self.assertEqual(6, self.set.find(2))
        self.assertEqual(6, self.set.find(1))
        self.assertEqual(6, self.set.find(3))
        self.assertEqual(6, self.set.find(6))

        self.set.union(5, 0)
        self.assertEqual(0, self.set.find(5))
        self.assertEqual(0, self.set.find(0))

        self.set.union(0, 7)
        self.assertEqual(7, self.set.find(0))
        self.assertEqual(6, self.set.find(1))
        self.assertEqual(6, self.set.find(2))
        self.assertEqual(6, self.set.find(3))
        self.assertEqual(4, self.set.find(4))
        self.assertEqual(7, self.set.find(5))
        self.assertEqual(6, self.set.find(6))
        self.assertEqual(7, self.set.find(7))
        self.assertEqual(6, self.set.find(8))


if __name__ == '__main__':
    unittest.main()
