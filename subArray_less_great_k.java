********** 862. Shortest Subarray with Sum at Least K*********
ven an integer array nums and an integer k, return the length of the 
shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.

A subarray is a contiguous part of an array.

Input: nums = [2,-1,2], k = 3
Output: 3

// (1)  如果队首的值满足当前值-队首值>=K,记录长度并弹出队首
// (2) 如果当前值<队列尾，那么弹出队尾保持队列单调
    
    public int shortestSubarray(int[] nums, int k) {
        int[] sum = new int[nums.length + 1];
        
        for (int i = 0; i < nums.length; i++){
            sum[i + 1] = sum[i] + nums[i];
            if (nums[i] > k){
                return 1;
            }
        }
        int minLen = Integer.MAX_VALUE;
        
        Deque<Integer> queue = new ArrayDeque<>();
        
        for (int i = 0; i < sum.length; i++){
            // currSum is less than preSum means nums[i] is negative
            while (!queue.isEmpty() && sum[i] <= sum[queue.getLast()]){
                queue.removeLast();
            }
            while (!queue.isEmpty() && sum[i] - sum[queue.peek()] >= k){
                minLen = Math.min(minLen, i - queue.poll());
            }
            queue.add(i);
            
        }
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
        
 ***************** ******************  ****************** * 
   
   ind the longest subarray whose sum is less or equal to K

int[] arr = {1, 2, 1, 0, 1, -8, -9, 0};
int k = 4;
Output is 8

int arr[] = {1, 2, 1, 0, 1, 1, 0};
int k = 4;
output is 5
  
  
  public class HelloWorld{

     
     public static int atMostSum(int arr[], int n,
                                        int k)
    {
        int sum = 0;
        int cnt = 0, maxcnt = 0;
        
        int end = 0;
        
     
        for (int i = 0; i < n; i++) {
             
            // If adding current element doesn't
            // cross limit add it to current window
            if ((sum + arr[i]) <= k) {
                sum += arr[i];
                cnt++;
            } else if(sum != 0){
                // Else, remove first element of current
                 // window.
                sum = sum - arr[i - cnt] + arr[i];
           }
            // keep track of max length.
            if (maxcnt  < cnt){
                maxcnt = cnt;
                end = i;
                
            }
            //maxcnt = Math.max(cnt, maxcnt);
        }
       
        
       // this is for print the subarry, end to recort the end of that array
        for (int j = end - maxcnt + 1; j <= end ; j++){
             System.out.print(arr[j] + ",");
        }
       System.out.println();
        return maxcnt;
    }
     
    /* Driver program to test above function */
    public static void main(String[] args)
    {
        int arr[] = { 1,2,0,2,1,-1,1,0,0};
        int n = arr.length;
        int k = 3;
     
        System.out.println(atMostSum(arr, n, k));
             
    }

}
