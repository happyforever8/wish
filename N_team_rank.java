 也可以和其他人组成团体,
* 比如n = 2, 两个人 A,B,
* 可能的结果有3种
* A 第一, B 第二
* B 第一, A 第二
* A, B 团体第一
*
* n = 3, 有 13 种可能
第一种情况，1 < i < j。j个人有i个名次可以看作是j-1个人新加入1个人，j-1个人的名次有两种情况：
 (1)i个排名，则新加入的人只能是i个名次中的一个，有i种可能；
 (2)i-1个名次，则新加入的人只能是新的名次，其可以插入在i-1个名次的任意位置，有i种可能；所以可以得到f(i,j) = i×f(i-1,j-1) + i×f(i,j-1)。
第二种情况，i = 1，所有人名次一样，f(1, j) = 1。
第三种情况，i = j，此时进行全排列即可，f(i, i) = i !。
————————————————
package wish;

public class TeamRank {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(13);
	}

	// 以下只为函数体部分，语言为Java
	public int numSort(int n) {
		if (n <= 0){
			return 0;
		}
		if (n == 1){
			return 1;
		}
		int result = 0;
		int[][] dp = new int[n][n]; // dp[i][j]表示j+1个人有i+1种排名的个数
		// 行大于列的地方不考虑
		// 初始化第一行，第二种情况
		for (int i = 0; i < n; i++) {
			dp[0][i] = 1;
		}
		// 初始化对角线，全排列，第三种情况
		for (int i = 1; i < n; i++) {
			dp[i][i] = nFac(i + 1);
		}
		// 第一种情况
		for (int i = 1; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				dp[i][j] = (i + 1) * dp[i][j - 1] + (i + 1) * dp[i - 1][j - 1];
			}
		}
		for (int i = 0; i < n; i++) {
			result += dp[i][n - 1];
		}
		return result;
	}

	// 求n的阶乘
	public int nFac(int n) {
		if (n == 0)
			return 1;
		int result = 1;
		while (n > 0) {
			result *= n;
			n--;
		}
		return result;
	}

}
