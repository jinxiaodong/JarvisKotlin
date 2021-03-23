package com.jarvis.kotlingrammar.arithmetic

import java.util.*
import kotlin.collections.HashMap

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 3/1/21
 */


/**
 *
 * 349. 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * 说明：
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 * */

fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
    var nums = nums1
    var maxNums = nums2
    if (nums1.size > nums2.size) {
        nums = nums2
        maxNums = nums1
    }
    var result = mutableListOf<Int>()
    for (num in nums) {
        if (maxNums.contains(num) && !result.contains(num)) {
            result.add(num)
        }
    }
    return result.toIntArray()
}

fun intersection2(nums1: IntArray, nums2: IntArray): IntArray {

    Arrays.sort(nums1)
    Arrays.sort(nums2)
    val array = IntArray(nums1.size + nums2.size)
    var index = 0
    var index1 = 0
    var index2 = 0
    while (index1 < nums1.size && index2 < nums2.size) {
        var num1 = nums1[index1]
        var num2 = nums2[index2]
        if (num1 == num2) {
            if (index == 0 || array[index - 1] != num1) {
                array[index] = num1
                index++
            }
            index1++
            index2++
        } else if (num1 > num2) {
            index2++
        } else {
            index1++
        }

    }

    return array.copyOf(index)
}


/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4:
 * 输入: s = ""
 * 输出: 0
 * 提示：
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 *
 * 滑动窗口
 *
 * */
fun lengthOfLongestSubstring(s: String): Int {

    val hashMap = HashMap<Char, Int>()
    var start = 0
    var end = 0
    var size = 0
    for (char in s) {
        if (hashMap.containsKey(char)) {
            start = maxOf(start, hashMap[char] ?: start)
        }
        size = maxOf(end - start + 1, size)
        hashMap.put(char, end + 1)
        end++
    }
    return size
}

fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
    var queue = LinkedList<Int>()
    var array = mutableListOf<Int>()

    for (i in 0..k) {
        if (queue.isNotEmpty() && nums[i] >= nums[queue.peekLast()]) {
            queue.pollLast()
        }
        queue.offerLast(i)
    }
    array.add(nums[queue.peekFirst()])
    for (i in k..nums.size) {
        if (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
            queue.pollLast()
        }
        queue.offerLast(i)

        while (queue.peekFirst() <= i - k) {
            queue.pollFirst()
        }
        array.add(nums[queue.peekFirst()])
    }

    return array.toIntArray()
}