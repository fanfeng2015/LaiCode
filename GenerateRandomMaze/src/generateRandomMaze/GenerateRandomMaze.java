package generateRandomMaze;

import java.util.Arrays;

// Randomly generate a maze of size N * N (where N = 2K + 1) whose corridor
// and wall’s width are both 1 cell. 

// For each pair of cells on the corridor, there must exist one and only one
// path between them. (Randomly means that the solution is generated randomly, 
// and whenever the program is executed, the solution can be different.). 

// The wall is denoted by 1 in the matrix and corridor is denoted by 0.

// Assumptions:
// 1. N = 2K + 1 and K >= 0.
// 2. the top left corner must be corridor.
// 3. there should be as many corridor cells as possible.
// 4. for each pair of cells on the corridor, there must exist one and only one path between them.

public class GenerateRandomMaze {

	private enum Direction {
		NORTH(-1, 0), SOUTH(1, 0), EAST(0, -1), WEST(0, 1);

		int deltaX;
		int deltaY;

		Direction(int deltaX, int deltaY) {
			this.deltaX = deltaX;
			this.deltaY = deltaY;
		}

		public int moveX(int x, int times) {
			return x + deltaX * times;
		}

		public int moveY(int y, int times) {
			return y + deltaY * times;
		}
	}

	public int[][] maze(int n) {
		// initialize the maze to have corridor (0) only on the top left corner
		int[][] maze = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(maze[i], 1);
		}
		maze[0][0] = 0;
		generate(maze, 0, 0);
		return maze;
	}

	// DFS
	private void generate(int[][] maze, int x, int y) {
		Direction[] directions = Direction.values();
		shuffle(directions);
		// follow the shuffled order to do DFS
		for (Direction direction : directions) {
			// advance by 2 steps
			int nextX = direction.moveX(x, 2);
			int nextY = direction.moveY(y, 2);
			if (isValidWall(maze, nextX, nextY)) {
				maze[direction.moveX(x, 1)][direction.moveY(y, 1)] = 0;
				maze[nextX][nextY] = 0;
				generate(maze, nextX, nextY);
			}
		}
	}

	// generate a random order of directions
	private void shuffle(Direction[] directions) {
		for (int i = 0; i < directions.length; i++) {
			int offset = (int) (Math.random() * (directions.length - i)); // offset length from i
			Direction temp = directions[i];
			directions[i] = directions[i + offset];
			directions[i + offset] = temp;
		}
	}

	private boolean isValidWall(int[][] maze, int x, int y) {
		int n = maze.length; // maze is n * n
		return x >= 0 && x < n && y >= 0 && y < n && maze[x][y] == 1;
	}

	// Time complexity is O(n^2).
	// Space complexity is O(n^2), because of call-stack.
}
