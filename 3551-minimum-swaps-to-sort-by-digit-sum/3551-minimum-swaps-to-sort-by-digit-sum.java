import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;

        // One ticket per person: [digitSum | value | currentSeat]
        long[] tickets = new long[n];
        for (int seat = 0; seat < n; seat++) {
            long digitSum = digitSum(nums[seat]);
            tickets[seat] = (digitSum << 47) | ((long) nums[seat] << 17) | seat;
        }

        // Sorting decides who SHOULD sit where
        Arrays.sort(tickets);

        // Keep only the seat number: tickets[i] = seat where the right person for seat i sits now
        int seatMask = (1 << 17) - 1;
        for (int i = 0; i < n; i++) {
            tickets[i] &= seatMask;
        }

        // Count loops of wrongly seated people; -1 means "already visited"
        int loops = 0;
        for (int i = 0; i < n; i++) {
            if (tickets[i] == -1) continue;
            loops++;
            int cur = i;
            while (tickets[cur] != -1) {
                int next = (int) tickets[cur];
                tickets[cur] = -1;
                cur = next;
            }
        }

        // A loop of k people needs k - 1 swaps
        return n - loops;
    }

    private int digitSum(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }
}