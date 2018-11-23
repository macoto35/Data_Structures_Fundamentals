import unittest
from colourFlip import ColourFlip

class ColourFlipTest(unittest.TestCase):
    def setUp(self):
        self.cf = ColourFlip()

    def test_newArray(self):
        self.cf.newArray(7)
        
        self.assertEqual('4,2,6,1,3,5,7', self.cf.bfsPrint(self.cf.t1, 'key'))
        self.assertEqual('3,2,2,1,1,1,1', self.cf.bfsPrint(self.cf.t1, 'height'))
        self.assertEqual('7,3,3,1,1,1,1', self.cf.bfsPrint(self.cf.t1, 'size'))
        self.assertEqual('w,w,w,w,w,w,w', self.cf.bfsPrint(self.cf.t1, 'colour'))
        
        self.assertEqual('4,2,6,1,3,5,7', self.cf.bfsPrint(self.cf.t2, 'key'))
        self.assertEqual('3,2,2,1,1,1,1', self.cf.bfsPrint(self.cf.t2, 'height'))
        self.assertEqual('7,3,3,1,1,1,1', self.cf.bfsPrint(self.cf.t2, 'size'))
        self.assertEqual('b,b,b,b,b,b,b', self.cf.bfsPrint(self.cf.t2, 'colour'))

    def test_merge(self):
        self.cf.t1 = self.cf.avlInsert(self.cf.t1, 1, 'w')
        self.cf.t1 = self.cf.avlInsert(self.cf.t1, 2, 'w')
        self.cf.t1 = self.cf.avlInsert(self.cf.t1, 3, 'w')
        self.cf.t2 = self.cf.avlInsert(self.cf.t2, 4, 'w')
        self.cf.t2 = self.cf.avlInsert(self.cf.t2, 5, 'w')
        self.cf.t2 = self.cf.avlInsert(self.cf.t2, 6, 'w')
        self.assertEqual('3,2,5,1,4,6', self.cf.bfsPrint(self.cf.merge(self.cf.t1, self.cf.t2), 'key'))

    def test_split(self):
        root = self.cf.avlInsert(None, 1, 'w')
        root = self.cf.avlInsert(root, 2, 'w')
        root = self.cf.avlInsert(root, 3, 'w')
        root = self.cf.avlInsert(root, 4, 'w')
        root = self.cf.avlInsert(root, 5, 'w')
        root = self.cf.avlInsert(root, 6, 'w')
        root = self.cf.avlInsert(root, 7, 'w')
        r1, r2 = self.cf.split(root, 5)

        self.assertEqual('4,2,1,3', self.cf.bfsPrint(r1, 'key'))
        self.assertEqual('6,5,7', self.cf.bfsPrint(r2, 'key'))


    def test_flip(self):
        self.cf.newArray(7)

        self.cf.flip(5)
        self.assertEqual('w,w,b,w,w,b,b', self.cf.bfsPrint(self.cf.t1, 'colour'))
        self.assertEqual('b,b,w,b,b,w,w', self.cf.bfsPrint(self.cf.t2, 'colour'))
        
        self.cf.flip(2)
        self.assertEqual('b,b,w,w,b,w,w', self.cf.bfsPrint(self.cf.t1, 'colour'))
        self.assertEqual('w,w,b,b,w,b,b', self.cf.bfsPrint(self.cf.t2, 'colour'))
        
        '''
        err case
        self.cf.flip(4)
        self.assertEqual('w,b,b,w,b,b,b', self.cf.bfsPrint(self.cf.t1, 'key'))
        self.assertEqual('w,b,b,w,b,b,b', self.cf.bfsPrint(self.cf.t1, 'colour'))
        self.assertEqual('b,w,w,b,w,w,w', self.cf.bfsPrint(self.cf.t2, 'colour'))'''

if __name__ == '__main__':
    unittest.main()
