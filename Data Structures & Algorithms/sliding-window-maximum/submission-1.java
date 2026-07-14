class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        List<Integer> list = new ArrayList<>();
        Deque<Integer> dq = new LinkedList<>();

        // First window
        for (int i = 0; i < k; i++) {

            while (!dq.isEmpty() &&
                   nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }

            dq.offerLast(i);
        }

        // Remaining windows
        for (int i = k; i < nums.length; i++) {

            list.add(nums[dq.peekFirst()]);

            // Remove out-of-window indices
            while (!dq.isEmpty() &&
                   dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // Remove smaller elements
            while (!dq.isEmpty() &&
                   nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }

            dq.offerLast(i);
        }

        // Last window max
        list.add(nums[dq.peekFirst()]);

        int[] result = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}