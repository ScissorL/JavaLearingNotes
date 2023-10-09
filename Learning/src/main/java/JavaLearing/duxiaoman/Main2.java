//package JavaLearing.duxiaoman;
//
//import javax.swing.text.StyledEditorKit;
//import java.util.Scanner;
//
//public class Main2 {
//    static int n;
//    static int m;
//    static int[] nums;
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int t = in.nextInt();
//        for (int i = 0; i < t; i++) {
//            n = in.nextInt();
//            m = in.nextInt();
//            nums = new int[n];
//            for (int j = 0; j < n; j++) {
//                nums[j] = in.nextInt();
//            }
//            for (int j = 0; j < m; j++) {
//                if (check()) {
//                    System.out.println(j);
//                    break;
//                }
//                int
//            }
//        }
//    }
//    public static boolean check() {
//        for (int i = 1; i < n; i++) {
//            if (nums[i] < nums[i-1]) {
//                return false;
//            }
//        }
//        return true;
//    }
//}
