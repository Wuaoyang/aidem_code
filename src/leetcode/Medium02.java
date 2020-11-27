package leetcode;

/**
 * addTwoNumbers
 * <p>
 * //给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * //
 * // 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * //
 * // 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * //
 * // 示例：
 * //
 * // 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * //输出：7 -> 0 -> 8
 * //原因：342 + 465 = 807
 * //
 * // Related Topics 链表 数学
 * // 👍 5242 👎 0
 * <p>
 * <p>
 * //leetcode submit region begin(Prohibit modification and deletion)
 * <p>
 * /**
 * * Definition for singly-linked list.
 * * public class ListNode {
 * * int val;
 * * ListNode next;
 * * ListNode() {}
 * * ListNode(int val) { this.val = val; }
 * * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * * }
 * *
 *
 * @author aidem
 * @date 2020/11/16 0016 10:50
 */
public class Medium02 {

}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1);
        ListNode cursor = result;
        int lv1, lv2, sum, inc = 0;
        while (l1 != null || l2 != null) {
            lv1 = l1 == null ? 0 : l1.val;
            lv2 = l2 == null ? 0 : l2.val;
            sum = lv1 + lv2 + inc;
            cursor.next = new ListNode(sum % 10);
            inc = sum > 9 ? 1 : 0;
            cursor = cursor.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (inc == 1) {
            cursor.next = new ListNode(1);
        }
        return result.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
