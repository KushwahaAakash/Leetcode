/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }
        
        int index = 1;
        ListNode prev = head;
        ListNode curr = head.next;
        ListNode next = head.next.next;
        
        int firstCP = -1, lastCP = -1, minDistance = Integer.MAX_VALUE;
        List<Integer> criticalPoints = new ArrayList<>();
        
        while (next != null) {
            if ((curr.val > prev.val && curr.val > next.val) || (curr.val < prev.val && curr.val < next.val)) {
                if (firstCP == -1) {
                    firstCP = index;
                } else {
                    minDistance = Math.min(minDistance, index - lastCP);
                }
                lastCP = index;
                criticalPoints.add(index);
            }
            prev = curr;
            curr = next;
            next = next.next;
            index++;
        }
        
        if (criticalPoints.size() < 2) {
            return new int[]{-1, -1};
        }
        
        int maxDistance = criticalPoints.get(criticalPoints.size() - 1) - criticalPoints.get(0);
        return new int[]{minDistance, maxDistance};
    }
}
