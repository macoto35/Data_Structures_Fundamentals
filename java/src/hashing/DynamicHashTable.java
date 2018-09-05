package hashing;

import java.util.LinkedList;
import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class DynamicHashTable {
    private class Pair {
        public String key;

        public String value;

        public Pair(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private long p = (long) Math.pow(10, 15) + 100011;

    private BigInteger x;

    private BigInteger a;

    private BigInteger b;

    private int m = 2;

    private LinkedList<Pair>[] chains = new LinkedList[this.m];

    private int size = 0;

    public DynamicHashTable() {
        this.randomKey();
    }

    private void randomKey() {
        this.x = BigInteger.valueOf(ThreadLocalRandom.current().nextLong(1, p - 1));
        this.a = BigInteger.valueOf(ThreadLocalRandom.current().nextLong(1, p - 1));
        this.b = BigInteger.valueOf(ThreadLocalRandom.current().nextLong(0, p - 1));
    }

    private BigInteger polyHash(String key) {
        BigInteger hash = BigInteger.ZERO;

        for (int i = key.length() - 1; i >= 0; i--)
            hash = hash.multiply(this.x).add(BigInteger.valueOf((long) key.charAt(i))).mod(BigInteger.valueOf(this.p));

        return hash;
    }

    private int universalHash(BigInteger key) {
        return this.a.multiply(key).add(this.b).mod(BigInteger.valueOf(this.p)).mod(BigInteger.valueOf(this.m)).intValue();
    }

    private int hashcode(String key) {
        return this.universalHash(this.polyHash(key));
    }

    private void rehash() {
        if (this.size / this.m > 0.9) {
            this.randomKey();
            this.m = this.m * 2;
            LinkedList<Pair>[] newChains = new LinkedList[this.m];

            for(LinkedList<Pair> chain : this.chains) {
                if (chain != null) {
                    for (Pair pair : chain) {
                        LinkedList<Pair> newChain = newChains[this.hashcode(pair.key)];
                        if (newChain == null) {
                            newChain = new LinkedList<Pair>();
                            newChains[this.hashcode(pair.key)] = newChain;
                        }
                        newChain.add(pair);
                    }
                }
            }

            this.chains = newChains;
        }
    }

    public void add(String key, String value) {
        this.rehash();
        LinkedList<Pair> chair = this.chains[this.hashcode(key)];

        if (chair == null) {
            chair = new LinkedList<Pair>();
            this.chains[this.hashcode(key)] = chair;
        }

        Pair pair = chair.stream().filter(x -> key.equals(x.key)).findAny().orElse(null);
        if (pair != null) {
            pair.value = value;
        } else {
            chair.add(new Pair(key, value));
            this.size++;
        }
    }

    public int size() {
        int count = 0;
        for (LinkedList<Pair> chair : this.chains)
            if (chair != null)
                for (Pair pair : chair)
                    count++;
        return count;
    }
}
