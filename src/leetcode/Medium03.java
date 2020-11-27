package leetcode;

import java.util.ArrayList;
import java.util.List;

//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
// 示例 1:
//
// 输入: "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
// Related Topics 哈希表 双指针 字符串 Sliding Window
// 👍 4593 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * ✔[3]无重复字符的最长子串
 *
 * @author aidem
 * @date 2020/11/27 0027 09:39
 */
public class Medium03 {

    public int lengthOfLongestSubstring(String s) {
        /**
         * 使用list，当成栈结构，每次入栈判断是否已经存在有重复的
         * 有的话清空栈中该值之前包括该值的所有值，加入新的值
         * 如果没有则直接入栈，最后判断栈空间大小即可
         */
        int maxLength = 0;
        List<String> temp = new ArrayList();
        if (s == null || s.length() == 0) {
            return 0;
        }
        int sLength = s.length();
        for (int i = 0; i < sLength; i++) {
            // 遍历每个值
            String c = String.valueOf(s.charAt(i));
            if (!temp.contains(c)) {
                temp.add(c);
                maxLength = Math.max(maxLength,temp.size());
            } else {
                while (temp.contains(c)){
                    temp.remove(0);
                }
                temp.add(c);
            }
        }
        return maxLength;
    }
}
