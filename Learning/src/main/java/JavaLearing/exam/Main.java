package JavaLearing.exam;
//
//import java.awt.print.PrinterAbortException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Main {
//    private static int N;
//    private static List<Integer> sOrderTime;
//    private static List<Integer> tCostTime;
//    private static List<Integer> aProfile;
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        N = in.nextInt();
//        sOrderTime = new ArrayList<>(N);
//        tCostTime = new ArrayList<>(N);
//        aProfile = new ArrayList<>(N);
//        for (int i = 0; i < N; i++) {
//            sOrderTime.set(i, in.nextInt());
//        }
//        for (int i = 0; i < N; i++) {
//            tCostTime.set(i, in.nextInt());
//        }
//        for (int i = 0; i < N; i++) {
//            aProfile.set(i, in.nextInt());
//        }
//
//    }
//}
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] s = new int[n];
        int[] t = new int[n];
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            s[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            t[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int[] dp = new int[n];
        dp[0] = a[0];

        for (int i = 1; i < n; i++) {
            dp[i] = a[i];
            for (int j = i - 1; j >= 0; j--) {
                if (s[i] - t[i] >= s[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + a[i]);
                }
            }
        }

        int maxReward = 0;
        for (int i = 0; i < n; i++) {
            maxReward = Math.max(maxReward, dp[i]);
        }

        System.out.println(maxReward);
    }
}
