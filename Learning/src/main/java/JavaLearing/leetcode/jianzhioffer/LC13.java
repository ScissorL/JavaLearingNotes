package JavaLearing.leetcode.jianzhioffer;


import javafx.util.Pair;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 * 也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 *
 *
 * 示例 1：
 *
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 2：
 *
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 */
public class LC13 {
    private int K;
    private int M;
    private int N;
    private int cnt = 1;
    public int movingCount(int m, int n, int k) {
        M = m;
        N = n;
        K = k;
        boolean num[][] = new boolean[m][n];
        return dfs(num, 0, 0);
    }
    public int getNum(int val) {
        int sum = 0;
        while (val > 0) {
            sum += val % 10;
            val /= 10;
        }
        return sum;
    }
    public int dfs(boolean[][] num, int m, int n) {
        if (m < 0 || m >= M || n < 0 || n >= N || num[m][n] || getNum(m) + getNum(n) > K) {
//                System.out.println("m2 = " + m2);
//                System.out.println("n2 = " + n2);
            return 0;
        }
        num[m][n] = true;
        System.out.println(m + " " + n);
        return 1 + dfs(num, m + 1, n) + dfs(num, m, n + 1);

    }

    public static void main(String[] args) {
        LC13 lc13 = new LC13();
        System.out.println(lc13.movingCount(3, 1 ,  0));
        System.out.println(lc13.movingCount(2, 3 ,  1));
    }
}
