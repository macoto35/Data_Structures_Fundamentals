package hashing;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class AssignmentFindPattern {
    private int p = 1000000007;

    private int x = 31;

    private long polyHash(String str) {
        long hash = 0;

        for (int i = str.length() - 1; i >= 0; i--)
            hash = (hash * this.x + ((int) str.charAt(i))) % this.p;

        return hash;
    }

    private Long[] preprocessHashes(String T, int pLen) {
        int idx = T.length() - pLen;
        Long[] H = new Long[idx + 1];
        H[idx] = this.polyHash(T.substring(idx, idx + pLen));

        long y = 1;
        for(int i = 0 ; i < pLen ; i++)
            y = (y * this.x) % this.p;

        for(int i = idx - 1; i >= 0; i--)
            H[i] = (this.x * H[i + 1] + ((int) T.charAt(i)) - y * ((int) T.charAt(i + pLen))) % p;

        return H;
    }

    public List<Integer> naive(String T, String P) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = T.length() - P.length() ; i >= 0 ; i--)
            if ( P.equals(T.substring(i, i + P.length())) )
                result.add(0, i);
        return result;
    }

    public List<Integer> sRabinKarp(String T, String P) {
        List<Integer> result = new ArrayList<Integer>();
        long pHash = this.polyHash(P);

        for (int i = T.length() - P.length() ; i >= 0 ; i--) {
            String t = T.substring(i, i + P.length());
            long tHash = this.polyHash(t);
            if (pHash == tHash && P.equals(t))
                result.add(0, i);
        }

        return result;
    }

    public List<Integer> fRabinKarp(String T, String P) {
        List<Integer> result = new ArrayList<Integer>();
        long pHash = this.polyHash(P);
        Long[] H = this.preprocessHashes(T, P.length());

        for (int i = T.length() - P.length(); i >= 0; i--) {
            if ( pHash == H[i] && P.equals(T.substring(i, i + P.length())) )
                result.add(0, i);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String P = scanner.next();
        String T = scanner.next();

        AssignmentFindPattern fp = new AssignmentFindPattern();
        //printList("naive", fp.naive(T, P));
        //printList("slow rabin-karp", fp.sRabinKarp(T, P));
        printList("fast rabin-karp", fp.fRabinKarp(T, P));
    }

    public static void printList(String type, List<Integer> list) {
        System.out.print(type + ": ");
        for (Integer i : list)
            System.out.print(i + " ");
        System.out.println();
    }
}
