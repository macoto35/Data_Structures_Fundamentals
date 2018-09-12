package hashing;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class AssignmentPhoneBook {
    String[] book = new String[10000000];

    public void add(String number, String name) {
        this.book[Integer.parseInt(number)] = name;
    }

    public void del(String number) {
        this.book[Integer.parseInt(number)] = null;
    }

    public String find(String number) {
        String result = this.book[Integer.parseInt(number)];
        if (result == null)
            return "not found";
        return result;
    }

    public static void main(String[] args) {
        try {
            FastScanner scanner = new FastScanner(System.in);
            int N = scanner.nextInt();

            AssignmentPhoneBook book = new AssignmentPhoneBook();
            List<String> result = new ArrayList<String>();
            for (int i = 0 ; i < N ; i++) {
                Map<String, String> map = scanner.nextData();

                switch(map.get("func")) {
                    case "add":
                        book.add(map.get("number"), map.get("name"));
                        break;
                    case "del":
                        book.del(map.get("number"));
                        break;
                    case "find":
                        result.add(book.find(map.get("number")));
                        break;
                }
            }
            scanner.close();

            for(String s : result) {
                System.out.print(s + " ");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static class FastScanner {
        BufferedReader br;

        String[] names = new String[]{"func", "number", "name"};

        public FastScanner(InputStream is) {
            this.br = new BufferedReader(new InputStreamReader(is));
        }

        private String next() {
           try {
                return br.readLine();
            } catch (Exception ex) {
               ex.printStackTrace();
           }

           return null;
        }

        public int nextInt() {
            return Integer.parseInt(this.next());
        }

        public Map<String, String> nextData() {
            String[] args = this.next().split("\\s+");
            Map<String, String> result = new HashMap<String, String>();

            for(int i = 0 ; i < args.length; i++) {
                result.put(this.names[i], args[i]);
            }

            return result;
        }

        public void close() {
            try {
                br.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
