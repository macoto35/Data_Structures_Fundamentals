package hashing;

import java.util.List;
import java.util.ArrayList;
import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

public class SearchSubstring {
    private boolean areEqual(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        for(int i = 0; i < s1.length(); i++)
            if (s1.charAt(i) != s2.charAt(i))
                return false;

        return true;
    }

    public List<Integer> findSubstringNaive(String T, String P) {
        List<Integer> positions = new ArrayList<Integer>();

        for(int i = T.length() - P.length() ; i >= 0; i--)
            if (areEqual(T.substring(i, i + P.length()), P))
                positions.add(i);

        return positions;
    }

    private BigInteger polyHash(String s, long p, long x) {
        BigInteger hash = BigInteger.ZERO;

        for (int i = s.length() - 1 ; i >= 0 ; i--)
            hash = hash.multiply(BigInteger.valueOf(x)).add(BigInteger.valueOf((long) s.charAt(i))).mod(BigInteger.valueOf(p));

        return hash;
    }

    public List<Integer> rabinKarp(String T, String P) {
        long p = (long) Math.pow(10, 15) + 100011;
        long x = ThreadLocalRandom.current().nextLong(1, p - 1);
        BigInteger pHash = polyHash(P, p, x);
        List<Integer> positions = new ArrayList<Integer>();

        for (int i = T.length() - P.length(); i >= 0 ; i--) {
            String t = T.substring(i, i + P.length());
            BigInteger tHash = polyHash(t, p, x);

            if (!pHash.equals(tHash))
                continue;

            if (areEqual(t, P))
                positions.add(i);
        }

        return positions;
    }

    private BigInteger[] precomputeHashes(String T, int lenP, long p, long x) {
        int idx = T.length() - lenP;
        BigInteger[] H = new BigInteger[idx + 1];
        H[idx] = polyHash(T.substring(idx, idx + lenP), p, x);

        BigInteger y = BigInteger.ONE;
        for (int i = 0 ; i < lenP; i++)
            y = y.multiply(BigInteger.valueOf(x)).mod(BigInteger.valueOf(p));

        for (int i = idx - 1; i >= 0; i--) {
            // ( x * H[i+1] + ord(T[i]) - y * ord(T[i + lenP]) ) % p
            BigInteger result = BigInteger.valueOf(x).multiply(H[i + 1]).add(BigInteger.valueOf((long)T.charAt(i))).subtract(y.multiply(BigInteger.valueOf((long)T.charAt(i + lenP)))).mod(BigInteger.valueOf(p));
            H[i] = result;
        }

        return H;
    }

    public List<Integer> optimizedRabinKarp(String T, String P) {
        long p = (long) Math.pow(10, 15) + 100011;
        long x = ThreadLocalRandom.current().nextLong(1, p - 1);
        BigInteger pHash = polyHash(P, p, x);
        BigInteger[] H = precomputeHashes(T, P.length(), p, x);
        List<Integer> positions = new ArrayList<Integer>();

        for (int i = T.length() - P.length(); i >= 0 ; i--) {
            if (!pHash.equals(H[i]))
                continue;
            if(areEqual(T.substring(i, i + P.length()), P))
                positions.add(i);
        }

        return positions;
    }

    public static void main(String[] args) {
        SearchSubstring search = new SearchSubstring();

        // Naive
        search.findSubstringNaive("Hello, World! Hello, User!", "Hello").stream().forEach(x -> System.out.print(x + " "));
        System.out.println();
        search.findSubstringNaive("hello, World! hello, User!", "Hello").stream().forEach(x -> System.out.print(x + " "));
        System.out.println();
        search.findSubstringNaive("python has a great built-in list type named list.", "Hello").stream().forEach(x -> System.out.print(x + " "));
        System.out.println();

        // Rabin-Karp
        search.rabinKarp("Hello, World! Hello, User!", "Hello").stream().forEach(x -> System.out.print(x + " "));
        System.out.println();
        search.rabinKarp("hello, World! hello, User!", "Hello").stream().forEach(x -> System.out.print(x + " "));
        System.out.println();
        search.rabinKarp("python has a great built-in list type named list.", "Hello").stream().forEach(x -> System.out.print(x + " "));
        System.out.println();

        // optimized Rabin-Karp
        search.optimizedRabinKarp("Hello, World! Hello, User!", "Hello").stream().forEach(x -> System.out.print(x + " "));
        System.out.println();
        search.optimizedRabinKarp("hello, World! hello, User!", "Hello").stream().forEach(x -> System.out.print(x + " "));
        System.out.println();
        search.optimizedRabinKarp("python has a great built-in list type named list.", "Hello").stream().forEach(x -> System.out.print(x + " "));
    }
}
