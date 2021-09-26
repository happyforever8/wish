package wish;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//VO2 - BFS robot
//W W W W W
//W . W . W
//W W S . W
//W W W W W
//S是起点 问去所有.的距离，到不了是-1 这个example输出结果应该是[-1,2,1]


public class Robot {
	public static void main(String[] args){
		
	char[][] grid = {{'W', 'W', 'W', 'W', 'W'},
			         {'W', '.', 'W', '.', 'W'},
			         {'W', 'W', 'S', '.', 'W'},
			         {'W', 'W', 'W', 'W', 'W'}};
	
	int[][] arr = helper(grid);
	List<Integer> list = new ArrayList<>();
	
	for (int i = 0; i < grid.length; i++){
		for (int j = 0; j < grid[0].length; j++){
			if (grid[i][j] == '.' && arr[i][j] == 0){
				list.add(-1);
			}
			if (grid[i][j] == '#' && arr[i][j] != 0){
				list.add(arr[i][j]);
			}
		}
	}
	System.out.println(list);
		
	}

	public static int[][] helper(char[][] rooms) {
		if (rooms == null || rooms.length == 0) {
			return new int[0][];
		}
		int[][] arr = new int[rooms.length][rooms[0].length];

		Queue<int[]> queue = new LinkedList<>();

		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[0].length; j++) {
				if (rooms[i][j] == 'S') {
					queue.offer(new int[] { i, j });
				}
			}
		}
		int[] dirX = { 1, -1, 0, 0 };
		int[] dirY = { 0, 0, 1, -1 };

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				int[] curr = queue.poll();

				for (int j = 0; j < 4; j++) {
					int dx = curr[0] + dirX[j];
					int dy = curr[1] + dirY[j];
					if (dx >= 0 && dx < rooms.length && dy >= 0 && dy < rooms[0].length && rooms[dx][dy] == '.' ) {
						rooms[dx][dy] = '#';
						arr[dx][dy] = arr[curr[0]][curr[1]] + 1;
						queue.offer(new int[] { dx, dy });
					}
				}
			}
		}
		return arr;
	}
}
