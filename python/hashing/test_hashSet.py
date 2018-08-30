import unittest
from hashSet import HashSet

class Phone:
    def __init__(self, number, name):
        self.number = number
        self.name = name

    def hash(self):
        return int(self.number[:3])

    def equals(self, obj):
        return self.number == obj.number


class HashSetTest(unittest.TestCase):
    def setUp(self):
        self.set = HashSet()

    def test_find(self):
        phone1 = Phone('017-0774-1234-5678', 'Maria');
        phone2 = Phone('112-4567-1122-9740', 'Sasha');
        phone3 = Phone('150-2570-7171-7575', 'Helen');
        self.set.add(phone1);
        self.set.add(phone2);
        self.set.add(phone3);

        self.assertTrue(self.set.find(phone1));
        self.assertTrue(self.set.find(phone2));
        self.assertTrue(self.set.find(phone3));
        self.assertFalse(self.set.find(Phone('123', 'test')));

    def test_remove(self):
        phone1 = Phone('017-0774-1234-5678', 'Maria');
        phone2 = Phone('112-4567-1122-9740', 'Sasha');
        phone3 = Phone('150-2570-7171-7575', 'Helen');
        self.set.add(phone1);
        self.set.add(phone2);
        self.set.add(phone3);
        
        self.assertEqual('Maria', self.set.remove(phone1).name)
        self.assertEqual('Sasha', self.set.remove(phone2).name)
        self.assertEqual('Helen', self.set.remove(phone3).name)
        self.assertIsNone(self.set.remove(Phone('123', 'test')))


if __name__ == '__main__':
    unittest.main()
