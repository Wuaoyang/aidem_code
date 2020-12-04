package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aidem
 * @date 2020/12/4 0004
 * @description 94. 二叉树的中序遍历
 */
public class Medium94 {

    /**
     * Definition for a binary tree node.
     */
     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

    /**
     * 解法1 暴力递归
     *      可以发现，中序遍历就是一直往左遍历，到底后取值，再遍历取值的右树，按照左根右的顺序
     *      可以将模式抽取出来，先左，遇空不做事并取上个结点值，后右（继续做先左，遇空不做事并取上个结点值...）
     *      抽离出模型，如下方代码，天然递归代码块
     */
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            ArrayList<Integer> res = new ArrayList<>();
            doRecusion(res,root);
            return res;
        }

        // 天然递归
        private void doRecusion(ArrayList<Integer> res, TreeNode root){
            // 构造结构，遍历左树，再遍历右树，遇到空结点时回上结点取值
            if(root == null){
                return;
            }
            doRecusion(res,root.left);
            res.add(root.val);
            doRecusion(res,root.right);
        }
    }

}


