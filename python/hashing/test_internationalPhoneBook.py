import unittest
from internationalPhoneBookLinkedList import InternationalPhoneBookLinkedList
from internationalPhoneBookDynamicArray import InternationalPhoneBookDynamicArray


class InternationalPhoneBookTest(unittest.TestCase):
    def setUp(self):
        self.list = InternationalPhoneBookLinkedList()
        self.array = InternationalPhoneBookDynamicArray()

    def test_linkedList(self):
        self.list.setName('017-0774-1234-5678', 'Maria')
        self.list.setName('112-4567-1122-9740', 'Sasha')
        self.list.setName('150-2570-7171-7575', 'Helen')

        self.assertEqual('Maria', self.list.getName('017-0774-1234-5678'))
        self.assertEqual('Sasha', self.list.getName('112-4567-1122-9740'))
        self.assertEqual('Helen', self.list.getName('150-2570-7171-7575'))
        self.assertIsNone(self.list.getName('250-2570-7171-7575'))

    def test_dynamicArray(self):
        self.array.setName('017-0774-1234-5678', 'Maria')
        self.array.setName('112-4567-1122-9740', 'Sasha')
        self.array.setName('150-2570-7171-7575', 'Helen')

        self.assertEqual('Maria', self.array.getName('017-0774-1234-5678'))
        self.assertEqual('Sasha', self.array.getName('112-4567-1122-9740'))
        self.assertEqual('Helen', self.array.getName('150-2570-7171-7575'))
        self.assertIsNone(self.array.getName('250-2570-7171-7575'))

if __name__ == '__main__':
    unittest.main()
