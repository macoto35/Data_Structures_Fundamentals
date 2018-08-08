package priorityQueue;

import java.util.LinkedList;
import java.util.Scanner;

public class convertArrayIntoHeap {
    private int n;

    private int[] A;

    private LinkedList result = new LinkedList();

    public convertArrayIntoHeap(int n, int[] A) {
        this.n = n;
        this.A = A;
    }

    private int parent(int i) {
        return (int) Math.floor((i - 1) / 2);
    }

    private int leftChild(int i) {
        return i * 2 + 1;
    }

    private int rightChild(int i) {
        return i * 2 + 2;
    }

    private void swap(int i, int j) {
        int tmp = this.A[i];
        this.A[i] = this.A[j];
        this.A[j] = tmp;
        this.result.add(i + " " + j);
    }

    private void shiftUp(int i) {
        while (i > 0) {
            if (this.A[i] < this.A[this.parent(i)])
                this.swap(this.parent(i), i);
            i = this.parent(i);
        }
    }

    public void buildHeap() {
        for (int i = n - 1; i > 0; i--)
            this.shiftUp(i);

        System.out.println(this.result.size());
        this.result.stream().forEach((data) -> System.out.println(data));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }

        convertArrayIntoHeap heap = new convertArrayIntoHeap(n, A);
        heap.buildHeap();
    }
}
