package priorityQueue;

import java.util.Arrays;
import java.util.Scanner;

public class MergingTables {
    int n;
    int m;
    int[] P;
    int[] R;
    int[][] O;
    int maxSize;

    public MergingTables(int n, int m, int[] P, int[] R, int[][] O, int maxSize) {
        this.n = n;
        this.m = m;
        this.P = P;
        this.R = R;
        this.O = O;
        this.maxSize = maxSize;
    }

    private int find(int i) {
        if (i != this.P[i])
            this.P[i] = find(this.P[i]);
        return this.P[i];
    }

    private int union(int d, int s) {
        int d_id = find(d);
        int s_id = find(s);

        if (d_id != s_id) {
            this.R[d_id] += this.R[s_id];
            this.R[s_id] = 0;
            P[s_id] = d_id;
        }

        return R[d_id];
    }

    public void run() {
        int size = 0;

        for (int[] item : this.O) {
            size = union(item[0] - 1, item[1] - 1);

            if (size > this.maxSize)
                this.maxSize = size;

            System.out.println(this.maxSize);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] P = new int[n];
        int[] R = new int[n];
        int maxSize = 0;
        for (int i = 0;  i < n; i++) {
            P[i] = i;
            R[i] = scanner.nextInt();
            if (R[i] > maxSize)
                maxSize = R[i];
        }

        int[][] O = new int[m][2];
        for (int i = 0; i < m; i++) {
            O[i][0] = scanner.nextInt();
            O[i][1] = scanner.nextInt();
        }

        MergingTables merge = new MergingTables(n, m, P, R, O, maxSize);
        merge.run();
    }
}
