import java.io.FileNotFoundException;


public class Driver {
	public static void main(String[] args) throws FileNotFoundException {
		TwistyPuzzle puzzle = new TwistyPuzzle("puzzle1.txt");
		puzzle.solvePuzzle();
	}
}
