package priorityQueue;

public class nAryHeap {
    private int n;

    private int[] H = new int[1];

    private int size = -1;

    private int maxSize = 1;


    public nAryHeap() {}

    public nAryHeap(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0 ; i <= this.size; i++) {
            sb.append(this.H[i]);
            sb.append(",");
        }

        return sb.toString();
    }


    private int parent(int i) {
        return (int) Math.floor((i - 1) / this.n);
    }

    private int leftChild(int i) {
        return i * this.n + 1;
    }

    private int rightChild(int i) {
        return i * this.n + this.n;
    }

    private void swap(int i, int j) {
        int tmp = this.H[i];
        this.H[i] = this.H[j];
        this.H[j] = tmp;
    }

    private void shiftUp(int i) {
        int p = 0;

        while(i > 0 && this.H[i] > this.H[this.parent(i)]) {
            p = this.parent(i);
            this.swap(i, p);
            i = p;
        }
    }

    private void shiftDown(int i) {
        int maxIdx = i;

        for (int cIdx = this.leftChild(i); cIdx <= this.rightChild(i); cIdx++)
            if (cIdx <= this.size && this.H[maxIdx] < this.H[cIdx])
                maxIdx = cIdx;

        if (i != maxIdx) {
            this.swap(i, maxIdx);
            this.shiftDown(maxIdx);
        }
    }

    public void insert(int p) {
        if (this.size == this.maxSize - 1) {
            int[] newH = new int[this.maxSize * this.n];

            for (int i = 0; i <= this.size; i++)
                newH[i] = this.H[i];

            this.H = newH;
            this.maxSize = this.maxSize * this.n;
        }

        this.H[++this.size] = p;
        this.shiftUp(this.size);
    }

    public int extractMax() {
        if (this.size == -1)
            throw new IndexOutOfBoundsException();

        int result = this.H[0];
        this.H[0] = this.H[this.size];
        this.size--;
        this.shiftDown(0);

        return result;
    }

    public void remove(int i) {
        if (i < 0 || i > this.size)
            throw new IndexOutOfBoundsException();

        this.H[i] = this.H[0] + 1;
        this.shiftUp(i);
        this.extractMax();
    }

    public void changePriority(int p, int i) {
        if (i < 0 || i > this.size)
            throw new IndexOutOfBoundsException();

        int oldp = this.H[i];
        this.H[i] = p;
        if (oldp < p)
            this.shiftUp(i);
        else
            this.shiftDown(i);
    }


}
