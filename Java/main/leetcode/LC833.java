package leetcode;

import java.nio.channels.Pipe;

public class LC833 {
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int k = indices.length;
        Boolean exist = true;
        for (int i = 0; i < k; ++i) {
            String origin = s.substring(indices[i], indices[i] + sources[i].length());
            if (!origin.equals(sources[i])) {
                indices[i] = -1;
                System.out.println(-1);
            }
        }
        StringBuilder ans = new StringBuilder();
        int pos = 0;
        for (int i = 0; i < k; ++i) {
            if (indices[i] == -1) continue;
            ans.append(s, pos, indices[i]);
            System.out.println(ans);
            ans.append(targets[i]);
            System.out.println(ans);
            pos = indices[i] + sources[i].length();
        }
        ans.append(s, pos, s.length());
        return ans.toString();
    }
}
