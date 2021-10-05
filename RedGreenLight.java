给出一个array， 比如[红, 绿, 绿, 红, 红]. 翻转⼀个⼦区间(红变绿,绿变红),使得绿灯个数最⼤. 返回区间的起点和终点.
	
  
public static void main(String[] args) {
	//in which红的多余绿的差最大
		// TODO Auto-generated method stub
		// O means Green
		// 1 means Red
		String s = "011011";  return [1,5]
		String s1 = "101000110"; return [6, 7]
		String s2 = "000";  return [0, 0]
		for (int i : maxGreenLights(s2)){
			System.out.println(i);
		}

	}
	
	 public static int[] maxGreenLights(String s) {
	         int[] result = new int[2];
	        Arrays.fill(result, -1);
	        // minSum 表示以前的红绿差值的最小值
	        // maxSum  表示当前的红绿差值的最大值
	        int sum = 0, maxSum = 0, minSum = 0;
	        
	        int left = -1;
	        for (int i = 0; i < s.length(); i++) {
	            if(s.charAt(i) == '1') {//红灯加一
	            	sum++;
	            } else{
	            	sum--; // 绿灯减一
	            }
	            // 表示绿灯比较多
	            if(sum < minSum) {
	                left = i;
	                minSum = sum;
	            }
	            
	            if(sum - minSum > maxSum) {
	                result[0] = left + 1;
	                result[1] = i ;
	                maxSum = sum - minSum;
	            }
	        }
	        return result[0] == -1 ? new int[0] : result;
	    }


双指针滑动窗口也行 说白了就是找一个区间 in which红的多余绿的差最大
public static int[] solve(int[] input) {
    int left = 0;
    int right = 0;
    int x = -1;
    int y = -1;
    int delta = Integer.MIN_VALUE;
    int count1 = 0;
    while (left < input.length && right < input.length) {
      if (input[right] == 0) {
        right++;
        continue;
      }
      count1++;
      while (left <= right && input[left] == 0) {
        ++left;
      }
      int tmpDelta = 2 * count1 - right + left - 1;
      
      if (tmpDelta > delta) {
        delta = tmpDelta;
        x = left;
        y = right;
      }
      ++right;
    }
    return new int[]{x,y};
  }
