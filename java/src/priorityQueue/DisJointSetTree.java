package priorityQueue;

public class DisJointSetTree {
    private int[] parent = new int[6];

    private int[] rank = new int[6];

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for(int p : parent) {
            sb.append(p);
            sb.append(",");
        }

        return sb.toString();
    }

    public void makeSet(int i) {
        if (i < 0 || i >= this.parent.length)
            throw new IndexOutOfBoundsException();
        this.parent[i] = i;
    }

    public int find(int i) {
        if (i < 0 || i >= this.parent.length)
            throw new IndexOutOfBoundsException();

        while (i != this.parent[i])
            i = this.parent[i];

        return this.parent[i];
    }

    public void union(int i , int j) {
        int i_id = this.find(i);
        int j_id = this.find(j);

        if (this.rank[i_id] > this.rank[j_id]) {
            this.parent[j_id] = i_id;
        } else {
            this.parent[i_id] = j_id;
            if (this.rank[i_id] == this.rank[j_id])
                this.rank[j_id] += 1;
        }
    }
}
