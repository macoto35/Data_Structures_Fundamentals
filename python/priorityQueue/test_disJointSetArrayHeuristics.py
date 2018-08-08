import unittest
from disJointSetTreeHeuristics import DisJointSetTreeHeuristics

class DisJointSetTreeHeuristicsTest (unittest.TestCase):
    def setUp(self):
        self.set = DisJointSetTreeHeuristics()

    def test_makeSet(self):
        self.set.makeSet(0)
        self.set.makeSet(1)
        self.set.makeSet(2)
        self.set.makeSet(3)
        self.set.makeSet(4)
        self.set.makeSet(5)
        self.assertEqual('0,1,2,3,4,5|0,0,0,0,0,0', self.set.toString())

        with self.assertRaises(IndexError):
            self.set.makeSet(-1)
            self.set.makeSet(6)

    def test_union(self):
        self.set.makeSet(0)
        self.set.makeSet(1)
        self.set.makeSet(2)
        self.set.makeSet(3)
        self.set.makeSet(4)
        self.set.makeSet(5)

        self.set.union(0, 1)
        self.assertEqual('1,1,2,3,4,5|0,1,0,0,0,0', self.set.toString())

        self.set.union(1, 2)
        self.assertEqual('1,1,1,3,4,5|0,1,0,0,0,0', self.set.toString())

        self.set.union(3, 4)
        self.assertEqual('1,1,1,4,4,5|0,1,0,0,1,0', self.set.toString())

        self.set.union(1, 4)
        self.assertEqual('1,4,1,4,4,5|0,1,0,0,2,0', self.set.toString())

        self.set.union(0, 5)
        self.assertEqual('4,4,1,4,4,4|0,1,0,0,2,0', self.set.toString())

if __name__ == '__main__':
    unittest.main()
