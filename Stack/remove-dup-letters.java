/*
==========================================================
            REMOVE DUPLICATE LETTERS
            (LeetCode 316 / 1081)
==========================================================

Problem:
Given a string s, remove duplicate letters so that every
letter appears exactly once.

Among all possible answers, return the
lexicographically smallest one.

----------------------------------------------------------
Intuition

We want:

1. Every character only once.
2. Lexicographically smallest string.

When processing a character:

• If it is already present in the answer,
  ignore it.

• Otherwise,

  While

  1. Stack is not empty
  2. Current character is smaller than stack top
  3. Stack top appears again later

  Pop the stack.

Why?

Because we can safely use that larger character later,
allowing the current smaller character to appear earlier,
making the answer lexicographically smaller.

----------------------------------------------------------
Approaches

1. Brute Force (Generate all subsequences)
2. Monotonic Stack (Optimal)

----------------------------------------------------------
Time Complexity

Brute Force:
O(2^N)

Optimal:
O(N)

Space:
O(1)

(26 lowercase letters)

==========================================================
*/

import java.util.Stack;

public class RemoveDuplicateLetters {

    public static void main(String[] args) {

        String s = "cbacdcbc";

        System.out.println(removeDuplicateLetters(s));
    }

    // ==========================================================
    // OPTIMAL
    // ==========================================================

    public static String removeDuplicateLetters(String s) {

        int[] lastIndex = new int[26];

        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        //array to indicate if element is in stack or not
        boolean[] present = new boolean[26];

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (present[ch - 'a'])
                continue;
            //removing the elements in the stack in case they occur later than the current element and also to element is greater than current
            while (!stack.isEmpty()
                    && ch < stack.peek()
                    && lastIndex[stack.peek() - 'a'] > i) {

                present[stack.pop() - 'a'] = false;
            }

            stack.push(ch);
            present[ch - 'a'] = true;
        }

        StringBuilder answer = new StringBuilder();

        while (!stack.isEmpty()) {
            answer.append(stack.pop());
        }

        return answer.reverse().toString();
    }
}
