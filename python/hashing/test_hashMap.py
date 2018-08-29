import unittest
from hashMap import HashMap

class HashMapTest(unittest.TestCase):
    def setUp(self):
        self.map = HashMap()

    def test_hash(self):
        self.assertEqual(17, self.map.hash('017-0774-1234-5678'))
        self.assertEqual(112, self.map.hash('112-4567-1122-9740'))
        self.assertEqual(150, self.map.hash('150-2570-7171-7575'))

    def test_hashKey(self):
        self.map.set('017-0774-1234-5678', 'Maria')
        self.map.set('112-4567-1122-9740', 'Sasha')
        self.map.set('150-2570-7171-7575', 'Helen')

        self.assertTrue(self.map.hashKey('017-0774-1234-5678'))
        self.assertTrue(self.map.hashKey('112-4567-1122-9740'))
        self.assertTrue(self.map.hashKey('150-2570-7171-7575'))
        self.assertFalse(self.map.hashKey('250-2570-7171-7575'))

    def test_get(self):
        self.map.set('017-0774-1234-5678', 'Maria')
        self.map.set('112-4567-1122-9740', 'Sasha')
        self.map.set('150-2570-7171-7575', 'Helen')
        self.map.set('150-2570-7171-7575', 'Mery')

        self.assertEqual('Maria', self.map.get('017-0774-1234-5678'))
        self.assertEqual('Sasha', self.map.get('112-4567-1122-9740'))
        self.assertEqual('Mery', self.map.get('150-2570-7171-7575'))
        self.assertIsNone(self.map.get('250-2570-7171-7575'))


    def test_remove(self):
        self.map.set('017-0774-1234-5678', 'Maria')
        self.map.set('112-4567-1122-9740', 'Sasha')
        self.map.set('150-2570-7171-7575', 'Helen')

        self.assertEqual('Maria', self.map.remove('017-0774-1234-5678'))
        self.assertEqual('Sasha', self.map.remove('112-4567-1122-9740'))
        self.assertEqual('Helen', self.map.remove('150-2570-7171-7575'))
        self.assertIsNone(self.map.remove('250-2570-7171-7575'))

if __name__ == '__main__':
    unittest.main()
