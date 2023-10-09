package JavaLearing.leetcode.hot100;

import java.util.*;

public class LiangShuZhiHe {
    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, List<Integer>> numToPos = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                numToPos.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
            }
            int[] results = new int[2];
//        ArrayList<int> arrayList = new ArrayList<int>();
//        Arrays.sort(nums, (a, b) -> Integer.compare(b ,a));
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                int diff = target - nums[i];
                for (int j = i + 1 ; j < nums.length; j++) {
                    if (diff == nums[j]) {
                        List<Integer> posLists = numToPos.get(nums[i]);
                        results[0] = posLists.get(0);
                        if (posLists.size() == 2) {
                            results[0] = posLists.get(1);
                        } else {
                            results[1] = numToPos.get(nums[j]).get(0);
                        }
                        return results;
                    }
                }
            }
            return results;
        }
    }
}
