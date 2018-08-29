import unittest
from directAddressing import DirectAddressing

class DirectAddressingTest(unittest.TestCase):
    def setUp(self):
        self.da = DirectAddressing()

    def test_getName(self):
        self.da.setName('123-45-67', 'Maria')
        self.da.setName('049-12-12', 'Helen')
        self.da.setName('575-75-75', 'Sarah')

        self.assertEqual('Maria', self.da.getName('123-45-67'))
        self.assertEqual('Helen', self.da.getName('049-12-12'))
        self.assertEqual('Sarah', self.da.getName('575-75-75'))
        self.assertIsNone(self.da.getName('475-75-75'))

if __name__ == '__main__':
    unittest.main()
