import unittest
from disJointSetTree import DisJointSetTree

class DisJointSetTreeTest(unittest.TestCase):
    def setUp(self):
        self.set = DisJointSetTree()

    def test_makeSet(self):
        self.set.makeSet(0)
        self.set.makeSet(1)
        self.set.makeSet(2)
        self.set.makeSet(3)
        self.set.makeSet(4)
        self.set.makeSet(5)
        self.set.makeSet(6)
        self.set.makeSet(7)
        self.set.makeSet(8)

        self.assertEqual('0,1,2,3,4,5,6,7,8', self.set.toString())

        with self.assertRaises(IndexError):
            self.set.makeSet(9)

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
        
        with self.assertRaises(IndexError):
            self.set.find(-1)
            self.set.find(9)


    def test_union(self):
        self.set.makeSet(0)
        self.set.makeSet(1)
        self.set.makeSet(2)
        self.set.makeSet(3)
        self.set.makeSet(4)
        self.set.makeSet(5)

        self.set.union(1, 3)
        self.assertEqual('0,3,2,3,4,5', self.set.toString())

        self.set.union(4, 1)
        self.assertEqual('0,3,2,3,3,5', self.set.toString())

        self.set.union(2, 0)
        self.assertEqual('0,3,0,3,3,5', self.set.toString())

        self.set.union(1, 2)
        self.assertEqual('0,3,0,0,3,5', self.set.toString())

        self.set.union(1, 5)
        self.assertEqual('0,3,0,0,3,0', self.set.toString())
        self.assertEqual('2,0,0,1,0,0', self.set.toStringRank())


if __name__ == '__main__':
    unittest.main()
