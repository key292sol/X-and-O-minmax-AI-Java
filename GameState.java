public class GameState {
	public static final char BLANK_SPACE = ' ';
	public static final char DRAW = 'D';
	public static final char LOSE = 'L';
	public static final char WIN = 'W';
	public static final char CONTINUE = 'C'; 
	public static final char NOT_OVER = CONTINUE;

	public char[][] grid;
	public char turn;

	public GameState() {
		grid = new char[3][3];
		reset();
	}

	public void reset() {
		turn = 'X';

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = BLANK_SPACE;
			}
		}
	}

	public void setMove(int r, int c) {
		grid[r][c] = this.turn;
		changeTurn();
	}

	private char changeTurn() {
		turn = (turn == 'X') ? 'O' : 'X';
		return turn;
	}

	public static char isGameOver(char[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			char c1 = grid[i][0],
				 c2 = grid[i][1],
				 c3 = grid[i][2];
			if (c1 == c2 && c1 == c3 && c1 != BLANK_SPACE) return WIN;

			c1 = grid[0][i];
			c2 = grid[1][i];
			c3 = grid[2][i];
			if (c1 == c2 && c1 == c3 && c1 != BLANK_SPACE) return WIN;
		}

		if (grid[1][1] != BLANK_SPACE) {
			if (grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2]) return WIN;
			if (grid[0][2] == grid[1][1] && grid[0][2] == grid[2][0]) return WIN;
		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == BLANK_SPACE) return NOT_OVER;
			}
		}

		return DRAW;
	}
}
