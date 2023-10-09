package JavaLearing.leetcode.hot100;

import java.util.HashMap;
import java.util.Map;

public class Number3 {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int left = 0, right = 0;
//        Set<Character> set = new HashSet<>();
        HashMap<Character, Integer> map = new HashMap<>();
        while (right < s.length()) {
            char cur = s.charAt(right);
            if (map.containsKey(cur)) {
                left = Math.max(left, map.get(cur) + 1);
            }
            map.put(cur, right);
            maxLength = Math.max(maxLength, right - left + 1);
            ++right;
        }

        return maxLength;
    }
}
