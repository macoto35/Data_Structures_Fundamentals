package priorityQueue;

/*
    Binary min heap + dynamic array
*/
public class BinaryMinHeap {
    private int[] H = new int[1];

    private int size = -1;

    private int maxSize = 1;

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= this.size; i++) {
            sb.append(this.H[i]);
            sb.append(",");
        }

        return sb.toString();
    }

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
        while(idx > 0 && this.H[idx] < this.H[this.parent(idx)]) {
            int p = this.parent(idx);
            this.swap(idx, p);
            idx = p;
        }
    }

    private void shiftDown(int idx) {
        int minIdx = idx;

        if (minIdx <= this.size) {
            int l = this.leftChild(idx);
            if (l <= this.size && this.H[minIdx] > this.H[l])
                minIdx = l;

            int r = this.rightChild(idx);
            if (r <= this.size && this.H[minIdx] > this.H[r])
                minIdx = r;

            if (idx != minIdx) {
                this.swap(idx, minIdx);
                this.shiftDown(minIdx);
            }
        }
    }

    public void insert(int p) {
        if (this.size + 1 == this.maxSize) {
            int[] newH = new int[this.maxSize * 2];

            for (int i = 0; i < this.maxSize; i++)
                newH[i] = this.H[i];

            this.H = newH;
            this.maxSize = this.maxSize * 2;
        }

        this.H[++this.size] = p;
        this.shiftUp(this.size);
    }

    public int extractMin() {
        if (this.size == -1)
            throw new IndexOutOfBoundsException();

        int result = this.H[0];
        this.H[0] = this.H[this.size--];
        this.shiftDown(0);

        return result;
    }

    public void remove(int idx) {
        if (idx > this.size || idx < 0)
            throw new IndexOutOfBoundsException();

        this.H[idx] = this.H[0] - 1;
        this.shiftUp(idx);
        this.extractMin();
    }

    public void changePriority(int idx, int p) {
        if (idx > this.size || idx < 0)
            throw new IndexOutOfBoundsException();
        int oldp = this.H[idx];
        this.H[idx] = p;
        if (oldp < p)
            this.shiftDown(idx);
        else
            this.shiftUp(idx);
    }
}
