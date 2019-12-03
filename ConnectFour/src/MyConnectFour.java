import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

/*
 * A 2 players game with one human player and a computer player.
 * As player 1, you can type in the position to drop your own chess
 * A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally
 */
public class MyConnectFour {
	
	public static void main(String[] args){
		new MyConnectFour();
	}

	// get user input from terminal
	private BufferedReader input;
	// array for chess board
	private char[][] board;
	
	public MyConnectFour(){
		board = new char[6][7];
		input = new BufferedReader(new InputStreamReader(System.in));
		playGame();
	}
	
	private void playGame(){
		System.out.println("Welcome to Connect 4");
		System.out.println("There are 2 players red and yellow");
		System.out.println("Player 1 is Red, Player 2 is Yellow");
		System.out.println("To play the game type in the number of the column you want to drop you counter in");
		System.out.println("A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally");
		System.out.println();
		System.out.println("*********You are the Player 1*********");
		System.out.println();
		// draw the original empty chess board
		printBoard();
		// whether one of the player wins
		boolean win = false;
		// total amount of the chess, in order to judge whether the game ends in a draw
		int chessAmount = 0;

		// main loop
		while(!win){
			// player 1
			String userInput = getUserInput();
			// get a return "error" from getUserInput(), means userd typed in wrong symbols. Keep receiving the input from terminal till user type in the right number of position
			if (userInput.equals("error")){
				continue;
			}
			int move = Integer.parseInt(userInput);
			// if user types in a position number out of range
			if (move>7 || move<1){
				continue;
			}
			// if hit the top of the board
			if (board[0][move-1] == 'r' || board[0][move-1] == 'y'){
				System.out.println("Hit the top! Please re-enter other position.");
				System.out.println();
				continue;
			}
			// eventually user type in a right number of position
			placeCounter('r',move);
			// if one of the player has won by connecting 4 chess
			boolean hasWon = false;
			// for counting how many same chesses have been connected
			int count = 0;

			// checking the win condition from 3 dimensions: horizontal, diagonal and vertical
			// player 1 check horizontal
			for(int i=0; i<board.length; i++){
				for(int j=0; j<board[i].length; j++){
					if(board[i][j] == 'r'){
						count = count + 1;
						if(count >= 4){
							hasWon = true;
						}
					}
					else{
						count = 0;
					}
				}
				count = 0;
            }
			if(hasWon){
				win = true;
			}

			// player 1 check diagonal
			for(int m=0;m<3;m++){
				for(int n=0;n<4;n++){
					if(board[m][n] == board[m+1][n+1] && board[m+1][n+1] == board[m+2][n+2]
							&& board[m+2][n+2] == board[m+3][n+3] && board[m+3][n+3] == 'r'){
						hasWon = true;
					}
					else if (board[m][6-n] == board[m+1][5-n] && board[m+1][5-n] == board[m+2][4-n]
							&& board[m+2][4-n] == board[m+3][3-n]&&board[m+3][3-n]=='r'){
						hasWon = true;
					}
				}
			}
			if (hasWon){
				win = true;
			}

			// player 1 check vertical
			count = 0;
			for(int i=0; i<board[0].length; i++){
				for(int j=0; j<board.length; j++){
					if(board[j][i] == 'r'){
						count = count + 1;
						if(count >= 4){
							hasWon = true;
						}
					}
					else{
						count = 0;
					}
				}
				count = 0;
            }
//			printBoard();
			if(hasWon){
				win = true;
			}
			if (win){
				System.out.println("Player 1 Have Won!!!");
				break;
			}
			// total amount of chess +1
			chessAmount++;
			if (chessAmount == 42){
				System.out.println("End in a draw!");
				break;
			}
			else{
				//player 2
				// player 2 is computer player, so it generates position randomly
				Random random = new Random();
				move = random.nextInt(7)+1;
				// if hit the top, generate new position until it meets the conditions
				if (board[0][move-1] == 'r' || board[0][move-1] == 'y'){
					boolean rightMovePosition = false;
					while(!rightMovePosition){
						move = random.nextInt(7)+1;
						if (board[0][move-1] != 'r' || board[0][move-1] != 'y'){
							rightMovePosition = true;
						}
					}
				}
				// player 2 put a right position number in
				placeCounter('y',move);
				hasWon = false;
				count = 0;
				// player 2 check horizontal
				for(int i=0; i<board.length; i++){
					for(int j=0; j<board[i].length; j++){
						if(board[i][j] == 'y'){
							count = count + 1;
							if(count >= 4){
								hasWon = true;
							}
						}
						else{
                            count = 0;
                        }
					}
					count = 0;
				}
				if (hasWon){
					win = true;
				}

				// player 2 check diagonal
				for(int m=0;m<3;m++){
					for(int n=0;n<4;n++){
						if(board[m][n] == board[m+1][n+1] && board[m+1][n+1] == board[m+2][n+2]
								&& board[m+2][n+2] == board[m+3][n+3] && board[m+3][n+3] == 'y'){
							hasWon = true;
						}
						else if (board[m][6-n] == board[m+1][5-n] && board[m+1][5-n] == board[m+2][4-n]
								&& board[m+2][4-n] == board[m+3][3-n]&&board[m+3][3-n]=='y'){
							hasWon = true;
						}

					}
				}
				if (hasWon){
					win = true;
				}

				// player 2 check vertical
				count = 0;
				for(int i=0; i<board[0].length; i++){
					for(int j=0; j<board.length; j++){
						if(board[j][i] == 'y'){
							count = count + 1;
							if(count >= 4){
								hasWon = true;
							}
						}
						else{
                            count = 0;
                        }
					}
					count = 0;
				}
				// draw the chess board which contains a move from player 1 and a move from player 2
				printBoard();
				if(hasWon){
					win = true;
				}
				if (win){
					System.out.println("Player 2 Have Won!!!");
					break;
				}
				chessAmount++;
				if (chessAmount == 42){
					System.out.println("End in a draw!");
					break;
				}
			}
		}
	}

	private String getUserInput(){
		/**
		 * get user input from terminal
		 * it can check whether the user type in the right position number, then return different Strings
		 * toReturn - user input String
		 * toNumber - an int number transformed from toReturn
		 */
		String toReturn;
		System.out.print("Please type in your position(1~7): ");
		try{			
			toReturn = input.readLine();
			int toNumber = Integer.parseInt(toReturn);
			if (toNumber>=1 && toNumber<=7){
				return toReturn;
			}
		}
		// like when type in "a", "b", "c" etc instead of number
		catch(Exception e){
			System.out.println("Fail to get user input!");
			System.out.println();
			return "error";
		}
		// like when type in the number out of 1~7
		System.out.println("It is out of range!");
		System.out.println();
		return "error";
	}
	
	private void printBoard(){
		/**
		 * draw the chess board based on the situation at the moment
		 */
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[i].length; j++){
				if(board[i][j] == 'r'){
					System.out.print("| r ");
				}
				else if(board[i][j] == 'y'){
					System.out.print("| y ");
				}
				else{
					System.out.print("|   ");
				}
			}
			System.out.println("|");
		}
		// the index of columns
		System.out.println("  1   2   3   4   5   6   7");
	}
	
	private void placeCounter(char player, int position){
		/**
		 * players place their own chess
		 * player - different players label, player 1 is r and player 2 is y
		 */
		boolean placed = false;
		// player 1
		if(player == 'r'){
			for(int i=board.length-1; i>=0; i--){
				if(!placed){
					// position occupied
					if(board[i][position-1] == 'y' || board[i][position-1] == 'r'){
						continue;
					}
					// position vacant
					else if(board[i][position-1] != 'r'){
						board[i][position-1] = 'r';
						placed = true;
					}
				}
			}
		}
		else{
			// player 2
			for(int i=board.length-1; i>=0; i--){
				if(!placed){
					if(board[i][position-1] == 'r' || board[i][position-1] == 'y'){
						continue;
					}
					else if(board[i][position-1] != 'y'){
						board[i][position-1] = 'y';
						placed = true;
					} 
				}
			}
		}
	}
}
