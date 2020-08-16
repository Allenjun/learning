package com.allen.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author JUN
 * @Description TODO
 * @createTime 11:23
 */
public class StackQuestion {
    
    public static void main(String[] args) {
        StackQuestion stackQuestion = new StackQuestion();
        System.out.println(stackQuestion.simplifyPath("/a//b////c/d//././/.."));
    }
    
    public String reverseParentheses(String s) {
        return null;
    }
    
    public String removeDuplicates(String s, int k) {
        Stack<int[]> stack = new Stack<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            int ch = s.charAt(i) - 'a';
            if (!stack.isEmpty() && stack.peek()[0] == ch) {
                stack.peek()[1] = stack.peek()[1] + 1;
                if (stack.peek()[1] == k) {
                    stack.pop();
                }
            } else {
                stack.push(new int[]{ ch, 1 });
            }
        }
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) {
            int[] pop = stack.pop();
            for (int i = 0; i < pop[1]; i++) {
                sb.append((char) (pop[0] + 'a'));
            }
        }
        return sb.toString();
    }
    
    public int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % nums.length]) {
                stack.pop();
            }
            result[i % nums.length] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % nums.length]);
        }
        return result;
    }
    
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }
            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            stack.push(nums2[i]);
        }
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }
        return result;
    }
    
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
                Integer num2 = stack.pop();
                Integer num1 = stack.pop();
                if ("+".equals(token)) {
                    stack.push(num1 + num2);
                }
                if ("-".equals(token)) {
                    stack.push(num1 - num2);
                }
                if ("*".equals(token)) {
                    stack.push(num1 * num2);
                }
                if ("/".equals(token)) {
                    stack.push(num1 / num2);
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
    
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int x : pushed) {
            stack.push(x);
            while (!stack.isEmpty() && j < pushed.length && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return pushed.length == j;
    }
    
    public int minAddToMakeValid(String s) {
        int num = 0;
        Stack<String> stack = new Stack<>();
        String[] strArr = s.split("");
        for (String chr : strArr) {
            if ("(".equals(chr) || ")".equals(chr)) {
                if ("(".equals(chr)) {
                    stack.push(chr);
                } else if (")".equals(chr) && !stack.isEmpty()) {
                    stack.pop();
                } else {
                    num++;
                }
            }
        }
        return stack.size() + num;
    }
    
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                list.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop().right;
            }
        }
        return list;
    }
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        return zigzagLevelOrder(list, 0, root);
    }
    
    private List<List<Integer>> zigzagLevelOrder(List<List<Integer>> list, int zoom, TreeNode node) {
        if (node != null) {
            for (int i = list.size() - 1; i < zoom; i++) {
                list.add(new LinkedList<>());
            }
            if (zoom % 2 == 0) {
                list.get(zoom).add(node.val);
            } else {
                list.get(zoom).add(0, node.val);
            }
            zigzagLevelOrder(list, zoom + 1, node.left);
            zigzagLevelOrder(list, zoom + 1, node.right);
        }
        return list;
    }
    
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        return inorderTraversal(list, root);
    }
    
    private List<Integer> inorderTraversal(List<Integer> list, TreeNode node) {
        if (node != null) {
            inorderTraversal(list, node.left);
            list.add(node.val);
            inorderTraversal(list, node.right);
        }
        return list;
    }
    
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] strArr = path.split("/");
        for (String chr : strArr) {
            if ("..".equals(chr) && !stack.isEmpty()) {
                stack.pop();
            } else if (!".".equals(chr) && !"".equals(chr) && !"..".equals(chr)) {
                stack.push(chr);
            }
        }
        return "/" + String.join("/", stack);
    }
    
    class TreeNode {
        
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int x) {
            val = x;
        }
    }
}
