import java.util.Scanner;

/**
 * Tic-Tac-Toe: Two-player console, non-graphics
 * 
 * @author relkharboutly
 * @date 1/5/2017
 */
public class TTTConsole {

	public static Scanner in = new Scanner(System.in); // the input Scanner

	public static TicTacToe TTTboard = new TicTacToe();

	/** The entry main method (the program starts here) */
	public static void main(String[] args) {

		int currentState = TicTacToe.PLAYING;
		String userInput;
		// game loop
		do {
			TTTboard.printBoard();
			// Print message if game-over
			currentState = TTTboard.checkForWinner();

			/**
			 * get player input here and call setMove(). user should input a number between
			 * 0-8
			 */
			if(currentState==ITicTacToe.PLAYING) {
				System.out.print("Enter Location(0-8): ");
				int location=in.nextInt();
				if(location<0||location>8) {
					System.out.println("Please Enter location between 0 to 8");
				}else {
					TTTboard.setMove(ITicTacToe.HUMAN_PLAYER, location);
					TTTboard.setMove(ITicTacToe.COMPUTER_PLAYER, TTTboard.getComputerMove());
				}
			}

			if (currentState == ITicTacToe.CROSS_WON) {
				System.out.println("'X' won! Bye!");
			} else if (currentState == ITicTacToe.NOUGHT_WON) {
				System.out.println("'O' won! Bye!");
			} else if (currentState == ITicTacToe.TIE) {
				System.out.println("It's a TIE! Bye!");
			}
			

		} while ((currentState == ITicTacToe.PLAYING) ); // repeat if not game-over
		
	}

}