给出一个array， 比如[红, 绿, 绿, 红, 红]. 翻转⼀个⼦区间(红变绿,绿变红),使得绿灯个数最⼤. 返回区间的起点和终点.
  
  public int[] maxGreenLights(String s) {
        int[] result = new int[2];
        int count = 0, maxSum = 0, minSum = 0;
        int left = -1;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'R') count++;
            else count--;
            if(count < minSum) {
                left = i;
                minSum = count;
            }
            if(count - minSum > maxSum) {
                result[0] = left + 1;
                result[1] = i ;
                maxSum = count - minSum;
            }
        }
        return result;
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
