import unittest
from hashing import Hashing

class HashingTest(unittest.TestCase):
    def setUp(self):
        self.hash = Hashing()

    def test_number(self):
        self.assertIsNotNone(self.hash.number(999999999999999));
        self.assertIsNotNone(self.hash.number(0));
        self.assertIsNotNone(self.hash.number(-1));


if __name__ == '__main__':
    unittest.main()
