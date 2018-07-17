package array;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TreeHeight {
    private String treeHeight(int[] parents, int n) {
        // put into tree
        Node[] nodes = new Node[n];
        int rootIdx = -1;

        for (int i = 0; i < n; i++) {
            int child = parents[i];
            if (child == -1) {
                rootIdx = i;
            } else {
                if (nodes[child] == null)
                    nodes[child] = new Node();
                nodes[child].children.add(i);
            }
        }

        // tree height
        StringBuffer sb = new StringBuffer();
        sb.append(treeHeightWithQueue(rootIdx, nodes));
        sb.append("/");
        //sb.append(treeHeightWithRecursive(rootIdx, nodes));

        return sb.toString();
    }

    private int treeHeightWithQueue(int rootIdx, Node[] nodes) {
        Queue<Integer> queue = new Queue<Integer>();
        queue.enqueue(rootIdx);
        int lastElement = rootIdx;
        int count = 0;

        while (!queue.empty()) {
            int idx = queue.dequeue();

            if (nodes[idx] != null)
                for (int i : nodes[idx].children)
                    queue.enqueue(i);

            if (idx == lastElement) {
                count += 1;
                lastElement = queue.topBack() == null ? -1 : queue.topBack();
            }
        }

        return count;
    }

    private int treeHeightWithRecursive(int idx, Node[] nodes) {
        Node node = nodes[idx];

        if (node == null || !node.hasChildren()) {
            return 1;
        }

        ArrayList<Integer> result = new ArrayList<Integer>();
        for (Integer n: node.children) {
            int count = treeHeightWithRecursive(n, nodes) + 1;
            result.add(count);
        }

        return Collections.max(result);
    }

    private class Node {
        private ArrayList<Integer> children = new ArrayList<Integer>();

        public boolean hasChildren() {
            return this.children.size() > 0;
        }
    }

    private class Queue<E> {
        private QueueNode head;

        private QueueNode tail;

        private class QueueNode {
            private QueueNode next;

            private E data;

            public QueueNode(E data) {
                this.data = data;
            }
        }

        public void enqueue(E input) {
            QueueNode node = new QueueNode(input);

            if (this.head == null)
                this.head = node;
            if (this.tail != null)
                this.tail.next = node;

            this.tail = node;
        }

        public E dequeue() {
            if (this.empty())
                return null;

            QueueNode delete = this.head;
            E result = delete.data;

            this.head = delete.next;
            if (this.head == null)
                this.tail = null;

            delete = null;

            return result;
        }

        public boolean empty() {
            return this.head == null;
        }

        public E topBack() {
            if (this.tail == null)
                return null;
            return this.tail.data;
        }
    }

    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] parents = new int[n];
        for (int i = 0 ; i < n ; i++)
            parents[i] = scanner.nextInt();

        TreeHeight th = new TreeHeight();
        System.out.println(th.treeHeight(parents, n));*/

        try {
            Scanner scanner = new Scanner(Files.newInputStream(Paths.get("././sample/tree_height_test.in")));

            while(scanner.hasNextInt()) {
                int n = scanner.nextInt();
                int[] parents = new int[n];
                for (int i = 0 ; i < n ; i++)
                    parents[i] = scanner.nextInt();

                TreeHeight th = new TreeHeight();
                System.out.println(th.treeHeight(parents, n));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
