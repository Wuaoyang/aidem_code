package Test;

import java.util.ArrayList;
import java.util.List;

/**
 * xxxxxxxxxxxx
 *
 * @author aidem
 * @date 2020/11/24 0024 18:08
 */
public class test {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("^"));

    }

    /**
     * 第一版本
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {
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
        int tempSize, sLength = s.length();
        for (int i = 0; i < sLength; i++) {
            // 遍历每个值
            String c = String.valueOf(s.charAt(i));
            if (!temp.contains(c)) {
                temp.add(c);
            } else {
                tempSize = temp.size();
                for (int j = 0; j < tempSize; j++) {
                    if (temp.get(j).equals(c)) {
                        temp = temp.subList(j + 1, tempSize);
                        temp.add(c);
                        break;
                    }
                }
            }
            maxLength = Math.max(maxLength,temp.size());
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring(String s) {
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
        int  sLength = s.length();
        for (int i = 0; i < sLength; i++) {
            // 遍历每个值
            String c = String.valueOf(s.charAt(i));
            if (!temp.contains(c)) {
                temp.add(c);
            } else {
                while (temp.contains(c)){
                    temp.remove(0);
                }
                temp.add(c);
            }
            maxLength = Math.max(maxLength,temp.size());
        }
        return maxLength;
    }
}
