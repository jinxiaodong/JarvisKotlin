package com.jarvis.kotlingrammar.arithmetic

import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * @author jinxiaodong
 * @description：语法第一周
 * @date 2020/11/24
 */
fun main() {

}


/**
 * 第一题：
 * 在你的面前从左到右摆放着 nn 根长短不一的木棍，你每次可以折断一根木棍，
 * 并将折断后得到的两根木棍一左一右放在原来的位置（即若原木棍有左邻居，
 * 则两根新木棍必须放在左邻居的右边，若原木棍有右邻居，新木棍必须放在右邻居的左边，所有木棍保持左右排列）。
 * 折断后的两根木棍的长度必须为整数，且它们之和等于折断前的木棍长度。你希望最终从左到右的木棍长度单调不减，
 * 那么你需要折断多少次呢？
 *
 * 输入描述：
 * 第一行是一个数 nn，表示开始时有多少根木棍 (1<=n<=3000)(1<=n<=3000) 第二行有 nn 个数，
 * 从第 11 个到第 nn 个分别表示从左到右的木棍长度。对任意木棍的长度 ll，有 1<=l<=30001<=l<=3000。
 *
 * 输出一行，一个数，你最少所需的折断木棍的次数
 *
 * 算法：
 * 从后往前遍历，使用单调递减栈
 * 在原本应该出栈的时机，将那根木棒折断成若干小于等于栈顶的小木棒
 * 并让他们尽量保持平均大小，将略小一的入栈
 *
 * 从后往前遍历，当当前位置木棍长度比后面的大时，就需要将其折成n份，策略是折成的n份中最小值尽量大，而最大值不超过后面的数。时间复杂度O(n) 空间复杂度O(1)
 * 举个例子:3 13 60 7从后往前遍历:记maxlength为右侧最大长度
 * 1.遍历到7，将maxlength初始化为7
 * 2.遍历到60，需要将60拆解为n份，最大值不超过7，最小值尽量大。因此拆解为60/7=8.571428571，取上整，也就是9份(原因最下面解释)，9份木棍长度中最大值为7，最小值为6，将maxlength置为6。拆成9份需要折8次。
 * 3.遍历到13，13/6=2.166666667，取上整为3，将3份长度为6 6 6的木棍轮流减掉1，一共减去5，得到3份长度分别为4 4 5，将maxlength置为4。拆成3份折2次。
 * 4.遍历到3，3＜maxlegth，然后将maxlength置为3
 * 结束。因此至少需要8+2=10次。
 * 解释60/7=8.571428571，取上整9的原因，不管取8还是9，最后都是9份。两者的区别就是，取8的话余数为4，那么9份木棍长度分别为4 7 7 7 7 7 7 7 7；取9的话余数为-3，需要在9份木棍上选择3份减掉1，长度分别为6 6 6 7 7 7 7 7 7 。注意上面如果单独在一条木棍上减3，那结果就是取下整的结果，但是我们完全可以把减数均摊，因此取上整的木棍长度最小值一定＞取下整的木棍最小值。
 * */
private fun breakNum(nums: ArrayList<Int>): Int {
    var ans = 0
    for (index in nums.size - 2 downTo 0) {
        println(index)
        if (nums[index + 1] >= nums[index]) continue
        var t = (nums[index] - 1) / nums[index + 1]
        ans += t
        nums[index] /= (t + 1)
    }
    return ans
}


/**
 * 第二题 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 示例：
 *      给定 nums = [2, 7, 11, 15], target = 9
 *      因为 nums[0] + nums[1] = 2 + 7 = 9
 *      所以返回 [0, 1]
 * 算法：
 *      1.暴力枚举 寻找存在target-x 的值
 *      2.哈希表方法
 *
 * 下面代码只使用hash表法
 */
fun twoSum(nums: IntArray, target: Int): IntArray? {

    var hashMap = HashMap<Int, Int>()
    for ((index, num) in nums.withIndex()) {
        if (hashMap.containsKey(target - num)) {
            return intArrayOf(hashMap[target - num]!!, index)
        }
        hashMap[num] = index
    }
    return null

}


/**
 * 第三题： 两数相加
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *      输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 *      输出：7 -> 0 -> 8
 *      原因：342 + 465 = 807
 *
 * 遍历相加即可
 * 由于链表存储整数是逆序，所以可以位位相加 注意进位
 * */

class ListNode {
    var value: Int = 0
    var next: ListNode? = null

    constructor(value: Int) {
        this.value = value
    }

    constructor(value: Int, next: ListNode) {
        this.value = value
        this.next = next
    }


}

//遍历
fun addTwoNumbers(node1: ListNode, node2: ListNode): ListNode? {
    var carry = 0
    var l1: ListNode?
    var l2: ListNode?
    var head: ListNode? = null
    var current: ListNode? = null
    l1 = node1
    l2 = node2
    var v1: Int = 0
    var v2: Int = 0
    while (l1 != null || l2 != null) {
        if (l1 != null) {
            v1 = l1.value
        }
        if (l2 != null) {
            v2 = l2.value
        }
        var sum = carry + v1 + v2
        carry = sum / 10
        if (head == null) {
            head = ListNode(sum % 10)
            current = head
        } else {
            current?.next = ListNode(sum % 10)
            current = current?.next
        }

        l1 = l1?.next
        l2 = l2?.next

    }
    if (carry == 1) {
        current?.next = ListNode(1)
    }
    return head
}

fun addTwoNumbers2(node1: ListNode, node2: ListNode): ListNode? {
    return dfs(node1, node2, 0)
}

//递归
//根据题意可知链表数字位数是从小到大的
//因为两个数字相加会产生进位，所以使用i来保存进位。
//则当前位的值为(l1.val + l2.val + i) % 10
//则进位值为(l1.val + l2.val + i) / 10
//建立新node，然后将进位传入下一层。
//
fun dfs(l: ListNode?, r: ListNode?, i: Int): ListNode? {
    if (l == null && r == null && i == 0) return null
    val sum: Int = (l?.value ?: 0) + (r?.value ?: 0) + i
    val node: ListNode = ListNode(sum % 10)
    node.next = dfs(l?.next, r?.next, sum / 10)
    return node
}


