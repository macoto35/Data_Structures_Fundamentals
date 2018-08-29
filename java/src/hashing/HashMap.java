package hashing;

public class HashMap {

    private Chain[] chains = new Chain[1000];

    private class Chain {
        private Pair head;
        private Pair tail;

        public Pair find(String key) {
            Pair pair = this.head;
            while (pair != null) {
                if (pair.key == key)
                    return pair;
                pair = pair.next;
            }
            return null;
        }

        public void push(String key, String value) {
            Pair pair = new Pair(key, value);

            if (this.head == null)
                this.head = pair;

            if (this.tail != null) {
                this.tail.next = pair;
                pair.prev = this.tail;
            }

            this.tail = pair;
        }

        public String pop(String key) {
            Pair pair = this.find(key);
            if (pair == null)
                return null;

            Pair prev = pair.prev;
            Pair next = pair.next;

            if (prev != null)
                prev.next = next;
            else
                this.head = next;

            if (next != null)
                next.prev = prev;
            else
                this.tail = prev;

            String result = pair.value;
            pair = null;

            return result;
        }
    }

    private class Pair {
        private String key;

        private String value;

        private Pair next;

        private Pair prev;

        public Pair(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public int hash(String key) {
        return Integer.parseInt(key.substring(0, 3));
    }

    public void set(String key, String value) {
        Chain chain = this.chains[this.hash(key)];
        if (chain == null) {
            chain = new Chain();
            this.chains[this.hash(key)] = chain;
        }

        Pair result = chain.find(key);
        if (result == null)
            chain.push(key, value);
        else
            result.value = value;
    }

    public boolean hashKey(String key) {
        Chain chain = this.chains[this.hash(key)];

        return chain != null && chain.find(key) != null;
    }

    public String get(String key) {
        Chain chain = this.chains[this.hash(key)];

        if (chain == null)
            return null;

        Pair pair = chain.find(key);
        return pair != null ? pair.value : null;
    }

    public String remove(String key) {
        Chain chain = this.chains[this.hash(key)];

        if (chain == null)
            return null;

        return chain.pop(key);
    }
}
