package array;

import java.util.*;

public class CheckBracketsInTheCode {

    private String checkBracket(String str) {

        Stack<Bracket> s = new Stack<Bracket>();

        char next;
        Bracket cout;
        for (int i = 0 ; i < str.length(); i++) {
            next = str.charAt(i);

            if (next == '[' || next == '{' || next == '(') {
                s.push(new Bracket(next, i));
            } else if (next == ']' || next == '}' || next == ')') {

                if (s.top() == null || !s.top().match(next))
                    return Integer.toString(i + 1);
                else
                    s.pop();
            }
        }

        if (s.empty()) {
            return "success";
        } else {
            return Integer.toString(s.pop().position + 1);
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


    public class Bracket {
        public char type;

        public int position;

        public Bracket(char type, int position) {
            this.type = type;
            this.position = position;
        }

        public boolean match(char c) {
            if (this.type == '[' && c == ']')
                return true;
            else if (this.type == '{' && c == '}')
                return true;
            else if (this.type == '(' && c == ')')
                return true;

            return false;
        }
    }

    private class Stack<E> {
        private Node head;

        private class Node {
            private E key;

            private Node next;

            public Node(E data) {
                this.key = data;
            }
        }

        public void push(E input) {
            Node node = new Node(input);

            node.next = head;
            head = node;
        }

        public E pop() {
            if (head == null)
                return null;

            Node del = head;
            head = del.next;
            E returnValue = del.key;
            del = null;

            return returnValue;
        }

        public E top() {
            if (head == null)
                return null;

            return head.key;
        }

        public boolean empty() {
            return head == null;
        }
    }
}
