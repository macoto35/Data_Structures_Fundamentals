import unittest
from dynamicHashTable import DynamicHashTable
from random import *

class DynamicHashTableTest(unittest.TestCase):
    def setUp(self):
        self.dh = DynamicHashTable()

    def test_rehash(self):
        # add
        for i in range(30):
            name = chr(randint(65, 90)) 
            for i in range(randint(5,10)):
                name += chr(randint(97, 122))
            self.dh.add(name, randint(0, 999999999999999))

        self.dh.display()

if __name__ == '__main__':
    unittest.main()
