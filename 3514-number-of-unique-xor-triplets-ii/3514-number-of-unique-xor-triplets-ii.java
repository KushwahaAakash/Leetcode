class Solution {
    public int uniqueXorTriplets(int[] nums) {

        final int MAXX = 2048;

        boolean[] pairXor = new boolean[MAXX];
        boolean[] ans = new boolean[MAXX];

        int n = nums.length;

        // XOR of two elements (indices may be equal)
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                pairXor[nums[i] ^ nums[j]] = true;
            }
        }

        // XOR with third element
        for (int x = 0; x < MAXX; x++) {
            if (!pairXor[x]) continue;

            for (int v : nums) {
                ans[x ^ v] = true;
            }
        }

        int cnt = 0;
        for (boolean b : ans)
            if (b) cnt++;

        return cnt;
    }
}