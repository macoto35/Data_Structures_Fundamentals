package array;

import java.util.*;

public class CheckBracketsInTheCode {

    private String checkBracket(String str) {

        Stack s = new Stack();

        char cin;
        Element cout;
        for (int i = 0 ; i < str.length(); i++) {
            cin = str.charAt(i);

            if (cin == '[' || cin == '{' || cin == '(') {
                s.push(cin, i);
            } else if (cin == ']' || cin == '}' || cin == ')') {
                cout = s.pop();

                if ((cin == ']' && cout.c != '[') || (cin == '}' && cout.c != '{') || (cin == ')' && cout.c != '('))
                    return Integer.toString(i + 1);
            }
        }

        if (s.empty()) {
            return "success";
        } else {
            return Integer.toString(s.pop().idx + 1);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CheckBracketsInTheCode check = new CheckBracketsInTheCode();
        System.out.println(check.checkBracket(scanner.next()));
    }

    public String run(String str) {
        return checkBracket(str);
    }


    public class Element {
        public char c;

        public int idx;

        public Element(char c, int idx) {
            this.c = c;
            this.idx = idx;
        }
    }

    private class Stack {
        private Node head;

        private class Node {
            private Element key;

            private Node next;

            public Node(char c, int idx) {
                this.key = new Element(c, idx);
            }
        }

        public void push(char c, int idx) {
            Node node = new Node(c, idx);

            node.next = head;
            head = node;
        }

        public Element pop() {
            if (head == null)
                return null;

            Node del = head;
            head = del.next;
            Element returnValue = del.key;
            del = null;

            return returnValue;
        }

        public boolean empty() {
            return head == null;
        }
    }
}
