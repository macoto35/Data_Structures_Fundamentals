package hashing;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;
import java.math.BigInteger;

public class PhoneNumberToName {
    private class Pair {
        public String key;

        public String value;

        public Pair(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    private int L = 15;

    private long p = (long)(Math.pow(10, this.L) + 100011);

    private int m = 1000;

    private BigInteger a;

    private BigInteger b;

    private LinkedList<Pair>[] chairs = new LinkedList[this.m];

    public PhoneNumberToName() {
        this.randomKeys();
    }

    private void randomKeys() {
        this.a = BigInteger.valueOf(ThreadLocalRandom.current().nextLong(1, p - 1));
        this.b = BigInteger.valueOf(ThreadLocalRandom.current().nextLong(0, p - 1));
    }

    private int hash(String key) {
        BigInteger x = new BigInteger(key.replaceAll("\\D", ""));

        return this.a.multiply(x).add(this.b).mod(BigInteger.valueOf(this.p)).mod(BigInteger.valueOf(this.m)).intValue();
    }

    public void add(String key, String value) {
        LinkedList<Pair> chair = this.chairs[this.hash(key)];

        if (chair == null)
            chair = new LinkedList<Pair>();

        Pair pair = chair.stream().filter(x -> key.equals(x.key)).findAny().orElse(null);
        if (pair != null) {
            pair.value = value;
        } else {
            chair.add(new Pair(key, value));
            this.chairs[this.hash(key)] = chair;
        }
    }

    public String get(String key) {
        LinkedList<Pair> chair = this.chairs[this.hash(key)];

        if (chair == null)
            return null;

        return chair.stream().filter(x -> key.equals(x.key)).map(x -> x.value).findAny().orElse(null);
    }
}
