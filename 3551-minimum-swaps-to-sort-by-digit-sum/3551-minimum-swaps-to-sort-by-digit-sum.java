
import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int students = nums.length;
        int[][] line = new int[students][2];

        for (int i = 0; i < students; i++) {
            line[i][0] = nums[i];
            line[i][1] = digitSum(nums[i]);
        }

        Arrays.sort(line, (a, b) -> {
            if (a[1] != b[1])
                return a[1] - b[1];
            return a[0] - b[0];
        });

        Map<Integer, Integer> originalPlace = new HashMap<>();

        for (int i = 0; i < students; i++)
            originalPlace.put(nums[i], i);

        boolean[] settled = new boolean[students];
        int swaps = 0;

        for (int i = 0; i < students; i++) {
            if (settled[i] || originalPlace.get(line[i][0]) == i)
                continue;

            int student = i;
            int groupSize = 0;

            while (!settled[student]) {
                settled[student] = true;
                student = originalPlace.get(line[student][0]);
                groupSize++;
            }

            swaps += groupSize - 1;
        }

        return swaps;
    }

    private int digitSum(int rollNumber) {
        int total = 0;

        while (rollNumber > 0) {
            total += rollNumber % 10;
            rollNumber /= 10;
        }

        return total;
    }
}
