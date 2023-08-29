package JavaLearing.leetcode;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/*
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
每个右括号都有一个对应的相同类型的左括号。
 */
public class LC20 {
    public boolean isValid(String s) {
        Stack<Character> characterStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                characterStack.push(ch);
            } else if (!characterStack.isEmpty()) {
                char top = characterStack.peek();
                if (((ch == ']' && top == '[') || (ch == ')' && top == '(') || (ch == '}' && top == '{'))) {
                    characterStack.pop();
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return characterStack.isEmpty();
    }
}
