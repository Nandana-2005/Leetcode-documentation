/*
==========================================================
                DECODE STRING
                (LeetCode 394)
==========================================================

Problem:
Given an encoded string, return its decoded version.

Encoding Rule:

k[encoded_string]

means the encoded_string inside the brackets is
repeated exactly k times.

Examples:

Input : "3[a]2[bc]"
Output: "aaabcbc"

Input : "3[a2[c]]"
Output: "accaccacc"

----------------------------------------------------------
Intuition

Whenever we encounter '[',

we need to remember:

1. The string built so far.
2. The repetition count.

When we encounter ']',

we finish the current substring,
repeat it 'count' times,
and append it to the previous string.

Stacks naturally handle nested brackets.

----------------------------------------------------------
Approaches

1. Recursive (DFS)
2. Two Stacks (Optimal)

----------------------------------------------------------
Time Complexity

O(N)

Space Complexity

O(N)

==========================================================
*/

import java.util.Stack;

public class DecodeString {

    public static void main(String[] args) {

        String s = "3[a2[c]]";

        System.out.println("Recursive : " + decodeStringRecursive(s));
        System.out.println("Two Stacks: " + decodeString(s));
    }

    // ==========================================================
    // 1. RECURSIVE
    // ==========================================================

    private static int index;

    public static String decodeStringRecursive(String s) {

        index = 0;
        return dfs(s);
    }

    private static String dfs(String s) {

        StringBuilder current = new StringBuilder();
        int number = 0;

        while (index < s.length()) {

            char ch = s.charAt(index);

            if (Character.isDigit(ch)) {

                number = number * 10 + (ch - '0');
                index++;

            } else if (ch == '[') {

                index++;

                String decoded = dfs(s);

                while (number-- > 0)
                    current.append(decoded);

                number = 0;

            } else if (ch == ']') {

                index++;
                return current.toString();

            } else {

                current.append(ch);
                index++;
            }
        }

        return current.toString();
    }

    // ==========================================================
    // 2. TWO STACKS (OPTIMAL)
    // ==========================================================

    public static String decodeString(String s) {

        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();

        StringBuilder current = new StringBuilder();

        int number = 0;

        for (char ch : s.toCharArray()) {

            if (Character.isDigit(ch)) {

                number = number * 10 + (ch - '0');

            } else if (ch == '[') {

                countStack.push(number);
                stringStack.push(current);

                number = 0;
                current = new StringBuilder();

            } else if (ch == ']') {

                int repeat = countStack.pop();

                StringBuilder previous = stringStack.pop();

                while (repeat-- > 0)
                    previous.append(current);

                current = previous;

            } else {

                current.append(ch);
            }
        }

        return current.toString();
    }
}
