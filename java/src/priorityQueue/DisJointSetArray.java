package priorityQueue;

import java.util.Arrays;

public class DisJointSetArray {
    private int[] smallest = new int[9];

    private int size = -1;

    private int maxSize = 9;

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= this.size; i++) {
            sb.append(this.smallest[i]);
            sb.append(",");
        }

        return sb.toString();
    }

    public void makeSet(int p) {
        if (this.size == this.maxSize - 1)
            throw new IndexOutOfBoundsException();

        this.smallest[++this.size] = p;
    }

    public int find(int i) {
        if (i < 0 || i > this.size)
            throw new IndexOutOfBoundsException();

        return this.smallest[i];
    }

    public void union(int i, int j) {
        int i_id = this.find(i);
        int j_id = this.find(j);

        if (i_id == j_id)
            return;

        int m = Math.min(i_id, j_id);

        for (int idx = 0; idx <= this.size; idx++) {
            if (this.smallest[idx] == i_id || this.smallest[idx] == j_id) {
                this.smallest[idx] = m;
            }
        }
    }
}
