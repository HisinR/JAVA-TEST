package cn.hisin.arithmetic.leetcode;

import java.util.*;

/**
 * @author 胡海鑫
 * @date 2019/3/6 13:09
 * <p>
 * 关于数组的一些算法
 */
public class ArraysMath {
    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 示例 1:
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     * 示例 2:
     * 输入: [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int k = 0;
        int maxProfit = 0;
        for (int i = 1; i <= prices.length - 1; i++) {
            if (k >= prices.length) {
                break;
            }
            int k2 = prices[i] - prices[k];
            if (k2 > 0) {
                maxProfit += k2;
            }
            k++;
        }
        return maxProfit;
    }

    /**
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     *
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        //求数组长度
        int n = nums.length;
        //当右移次数大于数组长度，取模后的只为新的要移动的次数
        if (k > n) {
            k = k % n;
        }
        //新建一个数组,将nums幅值进去（此时空间复杂度不满足O(1)）
        int[] rot = new int[n];
        //目的是最后结果在nums数组上操作，这样LeetCode系统可以检测
        rot = Arrays.copyOf(nums, n);
        //每个数都循环移动k个数
        for (int i = 0; i < n; i++) {
            int p = (i + k) % n;
            nums[p] = rot[i];
        }

        //输出可不写，仅为自己查看结果
        for (int x : nums) {
            System.out.println(x);
        }
    }

    public static void main(String[] args) {


        int arr[] = {1, 2, 3, 4, 5};
        rotate(arr, 2);
        System.out.println();
    }
}
