import unittest
from nameToPhoneNumber import NameToPhoneNumber

class NameToPhoneNumberTest(unittest.TestCase):
    def setUp(self):
        self.hash = NameToPhoneNumber()

    def test_polyHash(self):
        self.hash.add('Emma', '999-9999-9999-9999')
        self.hash.add('Harry', '000-0000-0000-0000')
        self.hash.add('Merry', '123-4567-8901-2345')
        
        self.assertEqual('999-9999-9999-9999', self.hash.get('Emma'))
        self.assertEqual('000-0000-0000-0000', self.hash.get('Harry'))
        self.assertEqual('123-4567-8901-2345', self.hash.get('Merry'))

if __name__ == '__main__':
    unittest.main()
