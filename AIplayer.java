
public class AIplayer {
	private char myTurnChar, theirTurnChar;

	public int[] nextMove(char[][] position, char _turn) {
		myTurnChar = _turn;
		theirTurnChar = (myTurnChar == 'X') ? 'O': 'X';

		PositionStats bestPos = minmax(position, true);
		return bestPos.getPosAsArr();
	}

	private PositionStats minmax(char[][] position, boolean myMove) {
		char gameEnd = GameState.isGameOver(position);
		if (gameEnd == GameState.WIN) {
			// return (myMove) ? -1 : 1;
			int eval = (myMove) ? -1 : 1;
			return new PositionStats(eval);
		} else if (gameEnd == GameState.DRAW) {
			return new PositionStats(0);
		}
		
		int eval = (myMove) ? -10 : 10;
		PositionStats bestPosition = new PositionStats();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (position[i][j] == GameState.BLANK_SPACE) {
					char[][] newPosition = this.copyPosition(position);
					newPosition[i][j] = (myMove) ? myTurnChar : theirTurnChar;

					PositionStats curPos = minmax(newPosition, !myMove);
					curPos.setRowCol(i, j);
					int curEval = curPos.getEval();

					if (myMove) {
						if (eval < curEval) {
							eval = curEval;
							bestPosition = curPos;
						}
					} else {
						if (eval > curEval) {
							eval = curEval;
							bestPosition = curPos;
						}
					}
				}
			}
		}

		return bestPosition;
	}

	private char[][] copyPosition(char[][] position) {
		char[][] newPos = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				newPos[i][j] = position[i][j];
			}
		}
		return newPos;
	}

	private class PositionStats {
		int row, col;
		int myEval;

		public PositionStats(){}

		public PositionStats(int e) { this.setEval(e); }

		public void setStats(int r, int c, int e) {
			this.setRowCol(r, c);
			this.setEval(e);
		}

		public void setRowCol(int r, int c) {
			row = r;
			col = c;
		}

		public void setEval(int eval) {
			myEval = eval;
		}

		public int getEval() {
			return this.myEval;
		}

		public int[] getPosAsArr() {
			return new int[]{row, col};
		}
	}
}
