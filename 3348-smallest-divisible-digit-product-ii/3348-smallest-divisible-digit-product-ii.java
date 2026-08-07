class Solution {

    // Factor contribution of each digit:
    // digit: {count of 2, count of 3, count of 5, count of 7}
    private static final int[][] FACTOR = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    public String smallestNumber(String num, long t) {

        // Required prime factor counts of t
        int[] target = new int[4];

        int[] primes = {2, 3, 5, 7};

        for (int i = 0; i < 4; i++) {
            while (t % primes[i] == 0) {
                target[i]++;
                t /= primes[i];
            }
        }

        // If t has any prime factor other than 2,3,5,7
        if (t != 1) {
            return "-1";
        }

        /*
         * Find the minimum number of digits needed to create
         * a product divisible by target.
         */
        int requiredDigits = minimumDigits(target);

        // If we need more digits than num has,
        // construct the smallest number with requiredDigits.
        if (requiredDigits > num.length()) {
            return construct(target, requiredDigits);
        }

        int n = num.length();

        // Prefix factor counts of num
        int[][] prefix = new int[n + 1][4];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                prefix[i + 1][j] =
                    prefix[i][j] + FACTOR[num.charAt(i) - '0'][j];
            }
        }

        // Check whether num itself works
        if (num.indexOf('0') == -1 &&
            covers(prefix[n], target)) {
            return num;
        }

        int firstZero = num.indexOf('0');
        if (firstZero == -1) {
            firstZero = n;
        }

        /*
         * Find the rightmost position where we can increase
         * the digit and make the suffix as small as possible.
         */
        for (int i = n - 1; i >= 0; i--) {

            // We cannot keep a prefix containing a zero.
            if (i > firstZero) {
                continue;
            }

            int current = num.charAt(i) - '0';

            // Factors contributed by prefix [0, i)
            int[] used = prefix[i].clone();

            for (int d = current + 1; d <= 9; d++) {

                int[] remaining = new int[4];

                for (int p = 0; p < 4; p++) {
                    remaining[p] = Math.max(
                        0,
                        target[p]
                        - used[p]
                        - FACTOR[d][p]
                    );
                }

                int suffixLength = n - i - 1;

                if (minimumDigits(remaining) <= suffixLength) {

                    StringBuilder ans = new StringBuilder();

                    // Keep prefix
                    ans.append(num, 0, i);

                    // Make current digit larger
                    ans.append(d);

                    // Build smallest possible suffix
                    ans.append(construct(remaining, suffixLength));

                    return ans.toString();
                }
            }
        }

        /*
         * No answer with the same number of digits.
         * Therefore use n + 1 digits.
         */
        return construct(target, n + 1);
    }

    /*
     * Minimum number of digits required to provide
     * all required factors.
     *
     * We greedily group:
     * 2^3 -> 8
     * 3^2 -> 9
     * 2*3 -> 6
     * 2^2 -> 4
     */
    private int minimumDigits(int[] a) {

        int c2 = a[0];
        int c3 = a[1];
        int c5 = a[2];
        int c7 = a[3];

        int digits = 0;

        // Use 8 = 2^3
        digits += c2 / 3;
        c2 %= 3;

        // Use 9 = 3^2
        digits += c3 / 2;
        c3 %= 2;

        // Use 6 = 2 * 3
        if (c2 > 0 && c3 > 0) {
            digits++;
            c2--;
            c3--;
        }

        // Remaining 2^2 -> 4
        digits += c2 / 2;
        c2 %= 2;

        // Remaining single 2 -> 2
        digits += c2;

        // Remaining 3 -> 3
        digits += c3;

        // 5 and 7 can only be represented by themselves
        digits += c5;
        digits += c7;

        return digits;
    }

    /*
     * Construct the lexicographically smallest string
     * that satisfies the required factors using exactly
     * 'length' digits.
     */
    private String construct(int[] required, int length) {

        int c2 = required[0];
        int c3 = required[1];
        int c5 = required[2];
        int c7 = required[3];

        StringBuilder special = new StringBuilder();

        // 8 = 2^3
        while (c2 >= 3) {
            special.append('8');
            c2 -= 3;
        }

        // 9 = 3^2
        while (c3 >= 2) {
            special.append('9');
            c3 -= 2;
        }

        // 6 = 2 * 3
        if (c2 > 0 && c3 > 0) {
            special.append('6');
            c2--;
            c3--;
        }

        // 4 = 2^2
        while (c2 >= 2) {
            special.append('4');
            c2 -= 2;
        }

        // 2
        while (c2 > 0) {
            special.append('2');
            c2--;
        }

        // 3
        while (c3 > 0) {
            special.append('3');
            c3--;
        }

        // 5
        while (c5 > 0) {
            special.append('5');
            c5--;
        }

        // 7
        while (c7 > 0) {
            special.append('7');
            c7--;
        }

        /*
         * Sort digits because we want the smallest
         * possible suffix.
         */
        char[] arr = special.toString().toCharArray();
        java.util.Arrays.sort(arr);

        StringBuilder result = new StringBuilder();

        // Extra positions can be filled with 1.
        for (int i = 0; i < length - arr.length; i++) {
            result.append('1');
        }

        result.append(arr);

        return result.toString();
    }

    private boolean covers(int[] have, int[] need) {
        for (int i = 0; i < 4; i++) {
            if (have[i] < need[i]) {
                return false;
            }
        }
        return true;
    }
}