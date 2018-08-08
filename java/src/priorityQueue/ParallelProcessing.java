package priorityQueue;

import java.util.*;

public class ParallelProcessing {

    private class Data {
        long time;
        int thread;

        public Data(long time, int thread) {
            this.time = time;
            this.thread = thread;
        }
    }

    private class MinHeap {
        private Data[] H;

        private int size = -1;

        private int maxSize;

        public MinHeap(int maxSize) {
            this.H = new Data[maxSize];
            this.maxSize = maxSize;
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
            Data tmp = this.H[i];
            this.H[i] = this.H[j];
            this.H[j] = tmp;
        }

        private void shiftUp(int i) {
            while (i > 0) {
                Data c = this.H[i];
                int pIdx = this.parent(i);
                Data p = this.H[pIdx];
                if (c.time < p.time || (c.time == p.time && c.thread < p.thread))
                    this.swap(i, pIdx);

                i = pIdx;
            }
        }

        private void shiftDown(int i) {
            if (i <= this.size) {
                int minIdx = i;
                Data min = this.H[i];

                int lIdx = this.leftChild(i);
                if (lIdx <= this.size) {
                    Data l = this.H[lIdx];
                    if (l.time < min.time || (l.time == min.time && l.thread < min.thread)) {
                        minIdx = lIdx;
                        min = l;
                    }
                }

                int rIdx = this.rightChild(i);
                if (rIdx <= this.size) {
                    Data r = this.H[rIdx];
                    if (r.time < min.time || (r.time == min.time && r.thread < min.thread)) {
                        minIdx = rIdx;
                        min = r;
                    }
                }

                if (minIdx != i) {
                    this.swap(i, minIdx);
                    this.shiftDown(minIdx);
                }
            }
        }

        public void insert(long time, int thread) {
            Data d =  new Data(time, thread);
            this.H[++this.size] = d;
            this.shiftUp(this.size);
        }

        public Data extractMin() {
            Data result = this.H[0];
            this.H[0] = this.H[this.size--];
            this.shiftDown(0);

            return result;
        }
    }

    public void run(int n, int m, int[] t) {
        MinHeap heap = new MinHeap(n);

        for (int i = 0 ; i < n; i++)
            heap.insert(0, i);

        for (int i = 0 ; i < m; i++) {
            Data result = heap.extractMin();
            heap.insert(t[i] + result.time, result.thread);
            System.out.println(result.thread + " " + result.time);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] t = new int[m];
        for (int i = 0; i < m ; i++)
            t[i] = scanner.nextInt();

        ParallelProcessing parallel = new ParallelProcessing();
        parallel.run(n, m, t);
    }
}
