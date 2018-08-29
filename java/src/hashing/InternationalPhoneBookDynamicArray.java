package hashing;

public class InternationalPhoneBookDynamicArray {
    private Pair[] arr = new Pair[1];

    private int size = 0;

    private int maxSize = 1;

    private class Pair {
        private String number;

        private String name;

        public Pair(String number, String name) {
            this.number = number;
            this.name = name;
        }
    }

    private int binarySearch(long number, int st, int ed) {
        if (st == ed) {
            if (this.getLong(this.arr[st].number) < number)
                return st + 1;
            else
                return st;
        }

        int mid = st + Math.floorDiv(ed - st, 2);
        long midVal = this.getLong(this.arr[mid].number);

        if (midVal == number)
            return mid;
        else if (midVal < number)
            return this.binarySearch(number, mid + 1, ed);
        else
            return this.binarySearch(number, st, mid - 1);
    }

    private long getLong(String number) {
        return Long.parseLong(number.replaceAll("\\D", ""));
    }

    public void setName(String number, String name) {
        if (this.size == this.maxSize) {
            int newMaxSize = this.maxSize * 2;
            Pair[] newArr = new Pair[newMaxSize];

            for (int i = 0 ; i < this.size ; i++)
                newArr[i] = this.arr[i];

            this.arr = newArr;
            this.maxSize = newMaxSize;
        }

        Pair pair = new Pair(number, name);
        if (this.size == 0) {
            this.arr[this.size] = pair;
        } else {
            int idx = this.binarySearch(this.getLong(number), 0, this.size - 1);
            for (int i = this.size - 1 ; i > idx - 1 ; i--)
                this.arr[i + 1] = this.arr[i];
            this.arr[idx] = pair;
        }
        this.size += 1;
    }

    public String getName(String number) {
        Pair pair = this.arr[this.binarySearch(this.getLong(number), 0, this.size - 1)];

        if (pair != null && pair.number == number)
            return pair.name;
        else
            return null;
    }
}
