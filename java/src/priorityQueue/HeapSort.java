package priorityQueue;

import java.util.Arrays;
import java.util.*;

public class HeapSort {
    public void heapSortNotInPlace(int[] A, int size) {
        PirorityQeuueIn pq = new PirorityQeuueIn();
        for (int i : A)
            pq.insert(i);
        for (int i = size - 1; i >= 0 ; i--) {
            A[i] = pq.extractMax();
        }
    }

    class PirorityQeuueIn {
        private int[] H = new int[10000];
        private int size = -1;
        private int maxSize = 9999;

        private int parent(int idx) {
            return (int) Math.floor((idx - 1) / 2);
        }

        private int leftChild(int idx) {
            return idx * 2 + 1;
        }

        private int rightChild(int idx) {
            return idx * 2 + 2;
        }

        private void swap(int i, int j) {
            int tmp = this.H[i];
            this.H[i] = this.H[j];
            this.H[j] = tmp;
        }

        private void shiftUp(int idx) {
            while (idx > 0 && this.H[idx] > this.H[this.parent(idx)]) {
                int p = this.parent(idx);
                this.swap(idx, p);
                idx = p;
            }
        }

        private void shiftDown(int idx) {
            int maxIdx = idx;

            if (idx <= this.size) {
                int l = this.leftChild(idx);
                if (l <= this.size && this.H[maxIdx] < this.H[l])
                    maxIdx = l;

                int r = this.rightChild(idx);
                if (r <= this.size && this.H[maxIdx] < this.H[r])
                    maxIdx = r;

                if (idx != maxIdx) {
                    this.swap(idx, maxIdx);
                    shiftDown(maxIdx);
                }
            }
        }

        public void insert(int p) {
            if (this.size == this.maxSize)
                throw new IndexOutOfBoundsException();
            this.H[++this.size] = p;
            this.shiftUp(this.size);
        }

        public int extractMax() {
            if (this.size == -1)
                throw new IndexOutOfBoundsException();
            int result = this.H[0];
            this.H[0] = this.H[this.size--];
            this.shiftDown(0);

            return result;
        }
    }

    public void heapSortInPlace(int[] A) {
        int size = A.length;
        this.buildHeap(A, size);
        while (size > 0) {
            this.swap(A,0, --size);
            this.shiftDown(A, 0, size);
        }
    }

    private void buildHeap(int[] A, int size) {
        int st = (int) Math.floor(size / 2);
        for (int i = st - 1; i >= 0; i--)
            this.shiftDown(A, i, size);
    }

    private void shiftDown(int[] A, int idx, int size) {
        int maxIdx = idx;

        if (idx < size) {
            int l = this.leftChild(idx);
            if (l < size && A[maxIdx] < A[l])
                maxIdx = l;

            int r = this.rightChild(idx);
            if (r < size && A[maxIdx] < A[r])
                maxIdx = r;

            if (idx != maxIdx) {
                swap(A, idx, maxIdx);
                shiftDown(A, maxIdx, size);
            }
        }
    }

    private int leftChild(int idx) {
        return idx * 2 + 1;
    }

    private int rightChild(int idx) {
        return idx * 2 + 2;
    }

    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void main(String[] args) {
        /*int[] A1 = new int[]{22, 40, 19, 3, 7, 12, 16, 19, 32, 8};
        int[] A2 = new int[]{22, 40, 19, 3, 7, 12, 16, 19, 32, 8};

        HeapSort hs = new HeapSort();
        hs.heapSortNotInPlace(A1, A1.length);
        System.out.print("Heap sort not in place: ");
        Arrays.stream(A1).forEach((a) -> System.out.print(a + " "));
        System.out.println();

        hs.heapSortInPlace(A2);
        System.out.print("Heap sort in place: ");
        Arrays.stream(A2).forEach((a) -> System.out.print(a + " "));*/

        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int range = scanner.nextInt();

        Random random = new Random();
        HeapSort hs = new HeapSort();

        while(true) {
            int[] A1 = new int[size];
            int[] A2 = new int[size];
            for (int i = 0; i < size; i++) {
                A1[i] = A2[i] = random.nextInt(range);
            }

            Arrays.stream(A1).forEach((a) -> System.out.print(a + " "));
            hs.heapSortNotInPlace(A1, A1.length);
            hs.heapSortInPlace(A2);

            if (Arrays.equals(A1, A2)) {
                System.out.println("- pass");
            } else {
                System.out.println("- fail");
                break;
            }
        }
    }
}
