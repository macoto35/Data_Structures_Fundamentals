import unittest
from phoneNumberToName import PhoneNumberToName

class PhoneNumberToNameTest(unittest.TestCase):
    def setUp(self):
        self.p = PhoneNumberToName()

    def test_getNameByPhoneNumber(self):
        self.p.add('999-9999-9999-9999', 'Emma');
        self.p.add('000-0000-0000-0000', 'Harry');
        self.p.add('123-4567-8901-2345', 'Mery');

        self.assertEqual('Emma', self.p.get('999-9999-9999-9999'));
        self.assertEqual('Harry', self.p.get('000-0000-0000-0000'));
        self.assertEqual('Mery', self.p.get('123-4567-8901-2345'));

if __name__ == '__main__':
    unittest.main()
