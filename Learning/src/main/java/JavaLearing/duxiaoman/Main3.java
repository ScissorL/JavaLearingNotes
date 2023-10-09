package JavaLearing.duxiaoman;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] nums = new int[n];
        int[] cnts = new int[m];
//        Node[] nodes = new Node[m];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        for (int i = 0; i < m; i++) {
            cnts[i] = in.nextInt();
        }
        int[] cur = new int[m];
        for (int i = 0; i < n; i++) {
            if (nums[i] <= m) {
                cur[nums[i] - 1]++;
            }
        }
        int left = 0, right = n - 1;
        while (left < right) {
            if (cnts[nums[left] - 1] < cur[nums[left] - 1]) {
                ++left;
                --cur[nums[left] - 1];
            } else if (cnts[nums[right] - 1] < cur[nums[right] - 1]) {
                --right;
                --cur[nums[right] - 1];
            } else {
                break;
            }
        }
        System.out.println();
    }
//    public static class Node {
//        int[] nums;
//        Node(int[] nums) {
//            this.nums = nums;
//        }
//        @Override
//        public boolean equals(Object object) {
//            if (object.getClass() != this.getClass()) {
//                return false;
//            }
//            Node node = (Node) object;
//            for (int i = 0; i < nums.length; i++) {
//                if (nums[i] != node.nums[i]) return false;
//            }
//            return true;
//        }
//    }
}
