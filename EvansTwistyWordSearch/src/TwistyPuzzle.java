import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class TwistyPuzzle {
	//Stores the characters of the puzzle
	private  char[][] puzzleArray;
	
	//Stores the words that need to be found
	private String[] wordsToFind;
	
	//Stores the number of words that need to be found.
	private int numberOfWords;
	
	//The number of rows in the puzzle.
	private int row;
	
	//The number of columns in the puzzle.
	private int column;

	
	/** This constructs an object of the Puzzle class.
	 * 
	 * @param file This is the name of the file that is scanned in.                
	 * @throws FileNotFoundException I don't deal with nonexistent files in the program, so I just throw the exception.
	 *
	 */
	public TwistyPuzzle(String file) throws FileNotFoundException {

		File puzzleFile = new File(file);
		puzzleArray = readPuzzle(puzzleFile);
	}


	/** This scans in a puzzle from a file provided by the user.
	 * 
	 * @param The file being scanned.
	 * @return a 2D array of characters representing the puzzle.
	 * @throws FileNotFoundException I don't deal with nonexistent files.
	 */
	private char[][] readPuzzle(File file) throws FileNotFoundException {
		Scanner fileIn = new Scanner(file);
		if (!fileIn.hasNext()) {
			System.out.println("The file specified is empty.");
			System.exit(0);
		}
		 row = fileIn.nextInt();
		 column = fileIn.nextInt();
		String[] puzzleArray = new String[row];
		char[][] charArray = new char[row][column];
			for(int i = 0;i < row; i++) {
				puzzleArray[i] = fileIn.next();
			}
			for(int i = 0; i < row; i++) {
				for(int j = 0; j < column; j++) {
					charArray[i][j] = puzzleArray[i].toCharArray()[j];
				}
			}
			while(fileIn.hasNext()) {
				fileIn.next();
				numberOfWords++;
			}
			fileIn = new Scanner(file);
			for(int i = 0;i < row + 1; i++) {
				fileIn.nextLine();
			}
			wordsToFind = new String[numberOfWords];
			for (int i = 0; i < numberOfWords; i++) {
				wordsToFind[i] = fileIn.next();
			}
		return charArray;
	}

	/**
	 * This method solves the puzzle.
	 * Precondition: There is a puzzle.
	 */
	public void solvePuzzle() {
		
		//Prints out the original puzzle.
		System.out.println("Original Puzzle:");
		System.out.println();
		printPuzzle();
		
		//Prints out the puzzles solved for the words specified.
		for (int i = 0; i < numberOfWords; i++) {
			if(findWord(wordsToFind[i])) {
				System.out.println(wordsToFind[i] + ":");
				System.out.println();
				printPuzzle();
				everythingToLowerCase();
			}
			else {
				System.out.println(wordsToFind[i] + ":");
				System.out.println();
				System.out.println("The word could not be found");
			}
		}
	}
	private  boolean findWord(String word) {
		for (int i = 0; i < puzzleArray.length; i++) {
			for(int j = 0; j < column; j++) {
				if (puzzleArray[i][j] == word.charAt(0)) {
					if(solve(0, i,j, word))
						return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Prints out the puzzle.
	 */
	public void printPuzzle() {
		for (int i=0; i < puzzleArray.length; i++) {
			System.out.println(puzzleArray[i]);
		}
		System.out.println();
	}
	private void everythingToLowerCase() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				puzzleArray[i][j] = Character.toString(puzzleArray[i][j]).toLowerCase().toCharArray()[0];
				}
			}
	}
	
	
	private boolean solve(int move, int y, int x, String wordToFind) {
		
			if (move == wordToFind.length()) {
				return true;
			}
		
			if (x < 0 || x >= column || y < 0 || y >= row) {
				return false;
			}

			if ( puzzleArray[y][x] != wordToFind.charAt(move)) {
				return false;
			}
			move++;
			puzzleArray[y][x] = Character.toUpperCase(puzzleArray[y][x]);
			if (solve(move,x+1, y+1,wordToFind))
				return true;
			if (solve(move,x+1, y-1,wordToFind))
				return true;
			if (solve(move,x-1, y+1,wordToFind))
				return true;
			if (solve(move,x-1, y-1,wordToFind))
				return true;
			if (solve(move,x+1, y,wordToFind))
				return true;
			if (solve(move,x - 1, y,wordToFind))
				return true;
			if (solve(move,x, y-1, wordToFind))
				return true;
			if (solve(move,x, y+1, wordToFind))
				return true;

			puzzleArray[y][x] = Character.toLowerCase(puzzleArray[y][x]);
			return false;
	}
	
	
	/**
	 * This changes a character to uppercase.
	 * @param charRow This is the row of the character.
	 * @param charColumn This is the column of the character.
	 */
	private void toUpperCase(int charRow, int charColumn) {
		puzzleArray[charRow][charColumn] = Character.toString(puzzleArray[charRow][charColumn]).toUpperCase().toCharArray()[0];
	}
	
	
}
