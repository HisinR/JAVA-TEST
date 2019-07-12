package cn.hisin.demo.leetcode;


/**
 * @author hisin
 */
public class TreeMath {

    private static int sum=0;

    private static int sumRootToLeaf(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    private static void dfs(TreeNode root, int currVal) {
        int temp = currVal * 2 + root.val;
        if (root.left == null && root.right == null) {
            sum  = (sum + temp % 1000000007) % 1000000007;
            return;
        }
        if (root.left != null){
            dfs(root.left, temp);
        }
        if (root.right != null){
            dfs(root.right, temp);
        }
    }

    public static void main(String[] args) {
        int a=2,b=3;
        int sum =a+b;
        System.out.println(sum);
//        TreeNode treeNode = new TreeNode(22);
//        int i = sumRootToLeaf(treeNode);
//        System.out.println(i);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}