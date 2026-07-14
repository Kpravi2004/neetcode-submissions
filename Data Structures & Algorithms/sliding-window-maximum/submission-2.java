class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {

        Deque<Integer> q = new ArrayDeque<>();

        int n = nums.length;

        int[] result = new int[n-k+1];

        // First window
        for(int i=0;i<k;i++)
        {
            while(!q.isEmpty() && nums[q.peekLast()] <= nums[i])
            {
                q.pollLast();
            }

            q.offerLast(i);
        }

        result[0] = nums[q.peekFirst()];

        // Remaining windows
        for(int i=k;i<n;i++)
        {
            // Remove indices outside the window
            if(q.peekFirst() <= i-k)
            {
                q.pollFirst();
            }

            // Remove smaller elements
            while(!q.isEmpty() && nums[q.peekLast()] <= nums[i])
            {
                q.pollLast();
            }

            // Add current index
            q.offerLast(i);

            // Store answer
            result[i-k+1] = nums[q.peekFirst()];
        }

        return result;
    }
}