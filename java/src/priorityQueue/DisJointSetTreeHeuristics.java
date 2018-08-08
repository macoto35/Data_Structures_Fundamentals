package priorityQueue;

public class DisJointSetTreeHeuristics {
    private int[] parent = new int[6];

    private int[] rank = new int[6];

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for(int i = 0 ; i < this.parent.length ; i++) {
            sb.append(this.parent[i]);
            sb.append("|");
            sb.append(this.rank[i]);
            sb.append(",");
        }

        return sb.toString();
    }

    public void makeSet(int i) {
        if (i < 0 || i >= this.parent.length)
            throw new IndexOutOfBoundsException();

        this.parent[i] = i;
    }

    /* path comparision heuristic */
    public int find(int i) {
        if (i < 0 || i >= this.parent.length)
            throw new IndexOutOfBoundsException();

        if (i != this.parent[i])
            this.parent[i] = this.find(this.parent[i]);

        return this.parent[i];
    }

    /* union by rank heuristic */
    public void union(int i, int j) {
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
