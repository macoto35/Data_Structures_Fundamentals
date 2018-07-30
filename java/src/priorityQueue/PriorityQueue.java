package priorityQueue;

public class PriorityQueue {
    private int[] H = new int[15];

    private int size = -1;

    private int maxSize = 14;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= this.size; i++) {
            sb.append(this.H[i]);
            sb.append(",");
        }

        return sb.toString();
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
        int tmp = this.H[i];
        this.H[i] = this.H[j];
        this.H[j] = tmp;
    }

    private void shiftUp(int i) {
        while (i > 0 && this.H[i] > this.H[this.parent(i)] ) {
            int p = this.parent(i);
            this.swap(i, p);
            i = p;
        }
    }

    private void shiftDown(int i) {
        int maxIdx = i;

        if (i <= this.size) {
            int l = this.leftChild(i);
            if (l <= this.size && this.H[maxIdx] < this.H[l])
                maxIdx = l;

            int r = this.rightChild(i);
            if (r <= this.size && this.H[maxIdx] < this.H[r])
                maxIdx = r;

            if (i != maxIdx) {
                this.swap(i, maxIdx);
                this.shiftDown(maxIdx);
            }
        }
    }

    public void insert(int data) {
        if (this.size == this.maxSize)
            throw new IndexOutOfBoundsException();
        this.size += 1;
        this.H[this.size] = data;
        this.shiftUp(this.size);
    }

    public int extractMax() {
        if (this.size == -1)
            throw new NullPointerException();

        int result = this.H[0];
        this.H[0] = this.H[this.size];
        this.size -= 1;
        this.shiftDown(0);

        return result;
    }

    public void remove(int i) {
        if (i > this.size || i < 0)
            throw new NullPointerException();

        this.H[i] = this.H[0] + 1;
        this.shiftUp(i);
        this.extractMax();
    }

    public void changePrority(int i, int data) {
        if (i > this.size || i < 0)
            throw new NullPointerException();

        int old = this.H[i];
        this.H[i] = data;
        if (data > old)
            this.shiftUp(i);
        else
            this.shiftDown(i);
    }


}
