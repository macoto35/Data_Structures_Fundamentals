package hashing;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

public class AssignmentHashTable {

    private long p = 1000000007L;

    private int x = 263;

    private int m;

    private LinkedList<String>[] chains;

    public AssignmentHashTable(int m) {
        this.m = m;
        this.chains = new LinkedList[this.m];
    }

    private int hash(String string) {
        long hash = 0;
        for (int i = string.length() - 1 ; i >= 0; i--)
            hash = (hash * this.x + (int)string.charAt(i)) % this.p;

        return (int)(hash % this.m);
    }

    public void add(String string) {
        int hash = this.hash(string);
        LinkedList chain = this.chains[hash];

        if (chain == null) {
            chain = new LinkedList<String>();
            this.chains[hash] = chain;
        }

        if (!chain.contains(string)) {
            chain.addFirst(string);
        }
    }

    public void del(String string) {
        LinkedList chain = this.chains[this.hash(string)];

        if (chain != null)
            chain.remove(string);
    }

    public String find(String string) {
        LinkedList<String> chain = this.chains[this.hash(string)];
        return chain != null && chain.contains(string) ? "yes" : "no";
    }

    public String check(int i) {
        LinkedList<String> chain = this.chains[i];

        if (chain == null)
            return "";

        return chain.stream().collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int N = scanner.nextInt();

        AssignmentHashTable table = new AssignmentHashTable(m);
        List<String> result = new ArrayList<String>();
        try {
            for (int i = 0; i < N; i++) {
                switch(scanner.next()) {
                    case "add":
                        table.add(scanner.next());
                        break;
                    case "del":
                        table.del(scanner.next());
                        break;
                    case "find":
                        result.add(table.find(scanner.next()));
                        break;
                    case "check":
                        result.add(table.check(scanner.nextInt()));
                        break;
                }
            }

            result.stream().forEach(x -> System.out.println(x));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
