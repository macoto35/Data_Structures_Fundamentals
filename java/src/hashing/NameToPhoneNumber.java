package hashing;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;


public class NameToPhoneNumber {
    private class Pair {
        public String key;

        public String value;

        public Pair(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private int L = 15;

    private long p = (long) Math.pow(10, 15) + 100011;

    private int m  = 1000;

    private BigInteger a;

    private BigInteger b;

    private BigInteger x;

    private LinkedList<Pair>[] chains = new LinkedList[this.m];

    public NameToPhoneNumber() {
        this.randomKeys();
    }

    private void randomKeys() {
        this.x = BigInteger.valueOf(ThreadLocalRandom.current().nextLong(1, p - 1));
        this.a = BigInteger.valueOf(ThreadLocalRandom.current().nextLong(1, p - 1));
        this.b = BigInteger.valueOf(ThreadLocalRandom.current().nextLong(0, p - 1));
    }

    private BigInteger polyHash(String S) {
        BigInteger hash = BigInteger.ZERO;

        for(int i = S.length() - 1; i >= 0 ; i--)
            hash = hash.multiply(this.x).add(BigInteger.valueOf((int)S.charAt(i))).mod(BigInteger.valueOf(this.p));

        return hash;
    }

    private int universalFamilyHash(BigInteger x) {
        return this.a.multiply(this.x).add(this.b).mod(BigInteger.valueOf(this.p)).mod(BigInteger.valueOf(this.m)).intValue();
    }

    private int hash(String S) {
        return this.universalFamilyHash(this.polyHash(S));
    }

    public void add(String key, String value) {
        LinkedList<Pair> chain = this.chains[this.hash(key)];

        if (chain == null)
            chain = new LinkedList<Pair>();

        Pair pair = chain.stream().filter(x -> key.equals(x.key)).findAny().orElse(null);
        if (pair != null) {
            pair.value = value;
        } else {
            chain.add(new Pair(key, value));
            this.chains[this.hash(key)] = chain;
        }
    }

    public String get(String key) {
        LinkedList<Pair> chain = this.chains[this.hash(key)];

        if (chain == null)
            return null;

        return chain.stream().filter(x -> key.equals(x.key)).map(x -> x.value).findAny().orElse(null);
    }
}
