package JavaLearing.leetcode.hot100;


public class Number5 {
    public int max = 0;
    public int[] pos = new int[2];
    public String longestPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            findMaxLen(s, i - 1, i + 1);
            findMaxLen(s, i, i + 1);
        }
        return s.substring(pos[0], pos[1]);
    }
    public void findMaxLen(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            --i;
            ++j;
        }
        if (j - i - 1 > max) {
            pos[0] = i + 1;
            pos[1] = j;
            max = j - i - 1;
        }
    }
}
