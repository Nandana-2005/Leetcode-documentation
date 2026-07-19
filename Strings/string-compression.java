/*
==========================================================
                STRING COMPRESSION
                (LeetCode 443)
==========================================================

Problem:
Given a character array chars, compress it in-place.

Compression Rule:

• Consecutive repeating characters are replaced by:
      character + frequency

• If the frequency is 1,
      only write the character.

Return the new length of the compressed array.

Examples

Input:
['a','a','b','b','c','c','c']

Output:
['a','2','b','2','c','3']

Length = 6

----------------------------------------------------------
Intuition

Each group of identical characters contributes:

1. The character itself.
2. Its count (only if count > 1).

Maintain:

read  -> scans the array
write -> writes the compressed result

----------------------------------------------------------
Approaches

1. Brute Force (Build a new string)
2. Two Pointers (Optimal)

----------------------------------------------------------
Time Complexity

Brute Force:
O(N)

Optimal:
O(N)

Space Complexity

Brute Force:
O(N)

Optimal:
O(1)

==========================================================
*/

public class StringCompression {

    public static void main(String[] args) {

        char[] chars = {'a','a','b','b','c','c','c'};

        int length = compress(chars);

        System.out.println("Compressed Length = " + length);

        for (int i = 0; i < length; i++)
            System.out.print(chars[i] + " ");
    }

    // ==========================================================
    // 1. BRUTE FORCE
    // ==========================================================

    public static int compressBrute(char[] chars) {

        StringBuilder result = new StringBuilder();

        int i = 0;

        while (i < chars.length) {

            char current = chars[i];
            int count = 0;

            while (i < chars.length && chars[i] == current) {
                count++;
                i++;
            }

            result.append(current);

            if (count > 1)
                result.append(count);
        }

        for (int j = 0; j < result.length(); j++)
            chars[j] = result.charAt(j);

        return result.length();
    }

    // ==========================================================
    // 2. TWO POINTERS (OPTIMAL)
    // ==========================================================

    public static int compress(char[] chars) {

        int write = 0;
        int read = 0;

        while (read < chars.length) {

            char current = chars[read];

            int count = 0;

            while (read < chars.length &&
                    chars[read] == current) {

                count++;
                read++;
            }

            chars[write++] = current;
            //adding value only if more than 1 character
            if (count > 1) {
                //converting integer to string 
                String frequency = String.valueOf(count);

                for (char digit : frequency.toCharArray()) {

                    chars[write++] = digit;
                }
            }
        }

        return write;
    }
}
