import java.util.Random;

/**
 * TicTacToe class implements the interface
 * 
 * @author relkharboutly
 * @date 1/5/2017
 */
public class TicTacToe implements ITicTacToe {

	// The game board and the game status
	private static final int ROWS = 3, COLS = 3; // number of rows and columns
	private int[][] board = new int[ROWS][COLS]; // game board in 2D array

	/**
	 * clear board and set current player
	 */
	public TicTacToe() {
		clearBoard();
	}
	@Override
	public void clearBoard() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				board[i][j] = EMPTY;
			}
		}

	}
	@Override
	public void setMove(int player, int location) {
		if(location>=0) {
			// find row and column index based on location
			int row = location / ROWS;
			int col = location % COLS;
			if(board[row][col]==EMPTY) {
				if (player == HUMAN_PLAYER) {
					board[row][col] = CROSS;
				} else if (player == COMPUTER_PLAYER) {
					board[row][col] = NOUGHT;
				}
			}else {
				System.out.println("Location is not Empty.");
			}
		}
	}

	@Override
	public int getComputerMove() {
		int bestMove=-1;
		// check wining strategy 
		bestMove=getBestMove(NOUGHT);
		if(bestMove>=0) 
			return bestMove;
		
		//blocking player 
		bestMove=getBestMove(CROSS);
		if(bestMove>=0) 
			return bestMove;
		
		//fill empty 
		for (int i = 0; i < ROWS*COLS; i++) {
			if(board[i/ROWS][i%COLS]==EMPTY) {
				bestMove=i;
				break;
			}
		}
		
		return bestMove;
	}

	private int getBestMove(int sign) {
		// check wining strategy horizontally and vertically
		for (int i = 0; i < ROWS; i++) {

			if ((board[i][0] == sign && board[i][1] == sign) || (board[i][1] == sign && board[i][2] == sign)
					|| (board[i][0] == sign && board[i][2] == sign)) {
				for (int j = 0; j < ROWS; j++) {
					if (board[i][j] == EMPTY)
						return (i * ROWS) + j;
				}
			}

			if ((board[0][i] == sign && board[1][i] == sign) || (board[1][i] == sign && board[2][i] == sign)
					|| (board[0][i] == sign && board[2][i] == sign)) {
				for (int j = 0; j < COLS; j++) {
					if (board[j][i] == EMPTY)
						return (j * COLS) + i;
				}
			}
		}
		// check diagonally

		if (((board[0][0] == sign && board[1][1] == sign) || (board[2][2] == sign && board[1][1] == sign)
				|| (board[0][0] == sign && board[2][2] == sign))) {
			for (int i = 0; i < ROWS; i++)
				if (board[i][i] == EMPTY)
					return (i * ROWS) + i;
		}
		if((board[2][0] == sign && board[1][1] == sign) || (board[0][2] == sign && board[1][1] == sign)
						|| (board[2][0] == sign && board[0][2] == sign)) {
			for (int i = 0; i < ROWS; i++)
				if (board[ROWS - i - 1][i] == EMPTY)
					return ((ROWS - i - 1) * ROWS) + i;
		
		}
		return -1;
	}

	@Override
	public int checkForWinner() {
		// check horizontally and vertically
		for (int i = 0; i < ROWS; i++) {
			// check horizontally
			if (board[i][0] != EMPTY && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return board[i][0] == CROSS ? CROSS_WON : NOUGHT_WON;
			}
			// check vertically
			if (board[0][i] != EMPTY && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
				return board[0][i] == CROSS ? CROSS_WON : NOUGHT_WON;
			}
		}
		// check diagonally
		if (board[1][1] != EMPTY) {
			if ((board[0][0] == board[1][1] && board[1][1] == board[2][2])
					|| (board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
				return board[1][1] == CROSS ? CROSS_WON : NOUGHT_WON;
			}
		}
		// check for playing
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (board[i][j] == EMPTY) {
					return PLAYING;
				}
			}
		}
		return TIE;
	}

	/**
	 * Print the game board
	 */
	public void printBoard() {
		for (int row = 0; row < ROWS; ++row) {
			for (int col = 0; col < COLS; ++col) {
				printCell(board[row][col]); // print each of the cells
				if (col != COLS - 1) {
					System.out.print("|"); // print vertical partition
				}
			}
			System.out.println();
			if (row != ROWS - 1) {
				System.out.println("-----------"); // print horizontal partition
			}
		}
		System.out.println();
	}

	/**
	 * Print a cell with the specified "content"
	 * 
	 * @param content
	 *            either CROSS, NOUGHT or EMPTY
	 */
	public void printCell(int content) {
		switch (content) {
		case EMPTY:
			System.out.print("   ");
			break;
		case NOUGHT:
			System.out.print(" O ");
			break;
		case CROSS:
			System.out.print(" X ");
			break;
		}
	}
}
