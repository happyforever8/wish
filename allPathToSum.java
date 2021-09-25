package wish;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SumPaths2 {
	
	// time is O(n!) - factorial time
	// 空间复杂度：O(N)
	public static void findPaths(int[][] mat, List<Integer> path, int i, int j, int sum) {
		// base case
		if (mat == null || mat.length == 0) {
			return;
		}
		int M = mat.length;
		int N = mat[0].length;

		// if the last cell is reached, print the route
		if (i == M - 1 && j == N - 1 && sum == mat[i][j]) {
			path.add(mat[i][j]);
			System.out.println(path);
			path.remove(path.size() - 1);
			return;
		}
		// include the current cell in the path
		path.add(mat[i][j]);

		// move right
		if ((i >= 0 && i < M && j + 1 >= 0 && j + 1 < N)) {
			findPaths(mat, path, i, j + 1, sum - mat[i][j]);
		}

		// move down
		if ((i + 1 >= 0 && i + 1 < M && j >= 0 && j < N)) {
			findPaths(mat, path, i + 1, j, sum - mat[i][j]);
		}

		// backtrack: remove the current cell from the path
		path.remove(path.size() - 1);
	}

	public static void main(String[] args) {
		int[][] mat = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

		List<Integer> path = new ArrayList<>();

		// start from `(0, 0)` cell
		int x = 0, y = 0;

		findPaths(mat, path, x, y, 21);
	}
}
