package Test;

import java.text.ParseException;
import java.util.Arrays;

/**
 * xxxxxxxxxxxx
 *
 * @author aidem
 * @date 2020/11/24 0024 18:08
 */
public class test {

    public static void main(String[] args) throws ParseException {

        int [] intt = new int[]{1,2,3,4,5};
        int[] s = Arrays.copyOfRange(intt, 1, 2);
        for (int i : s) {
            System.out.print(i);
        }


    }


    public static String longestPalindrome(String s) {
        // 暴力拆解
        String zero = "";
        // 排除特殊情况
        if (s == null) {
            return zero;
        }
        int length = s.length();
        if (length == 0 || length == 1) {
            return s;
        }
        if (length == 2) {
            return s.charAt(0) == (s.charAt(1)) ? String.valueOf(s.charAt(0)) + String.valueOf(s.charAt(0)) : zero;
        }
        // 开始真正的暴力
        int maxLength = 0, left, right;
        String temp, result = zero;
        for (int i = 0; i < length; i++) {
            temp = zero;
            left = i;
            right = i;
            while (left != -1 && right != length - 1) {
                char leftChar = s.charAt(left);
                if (leftChar == (s.charAt(right))) {
                    if (temp.equals(zero)) {
                        temp = String.valueOf(leftChar);
                    } else {
                        temp = leftChar + temp + leftChar;
                    }
                }
                left--;
                right++;
            }
            int tempLength = temp.length();
            if (tempLength > maxLength) {
                maxLength = tempLength;
                result = temp;
            }
        }
        return result;
    }
}
