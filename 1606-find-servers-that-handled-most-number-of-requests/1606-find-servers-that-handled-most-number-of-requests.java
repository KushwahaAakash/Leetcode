
import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        int n = arrival.length;

        int[] count = new int[k];

        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // [finishTime, serverIndex]
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        int max = 0;

        for (int i = 0; i < n; i++) {

            int time = arrival[i];

            // Release completed servers
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] server = busy.poll();
                available.add((int) server[1]);
            }

            if (available.isEmpty()) {
                continue;
            }

            int preferred = i % k;

            // Find next available server
            Integer server = available.ceiling(preferred);

            // Wrap around
            if (server == null) {
                server = available.first();
            }

            available.remove(server);

            count[server]++;

            max = Math.max(max, count[server]);

            busy.offer(new long[]{
                (long) arrival[i] + load[i],
                server
            });
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                ans.add(i);
            }
        }

        return ans;
    }
}
