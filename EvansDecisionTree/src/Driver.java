import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class Driver {
	public static void main(String[] args) throws InterruptedException, FileNotFoundException, UnsupportedEncodingException {
		Scanner userIn = new Scanner(System.in);
		Scanner fileIn = new Scanner(new File("decisionTree.txt"));
		DecisionNode decisionTree = readFile(fileIn, null);
		System.out.println("would you like to play?");
		String userResponse = userIn.next();
		while (userResponse.equalsIgnoreCase("yes")) {
		//	preorderPrint(decisionTree);
			makeGuesses(decisionTree);
			System.out.println("Would you like to play again?");
			userResponse = userIn.next();
		}
		PrintWriter writer = new PrintWriter("decisionTree.txt", "UTF-8");
		preorderPrint(decisionTree);
		printToFile(decisionTree, writer);
		writer.close();
		
	}
	public static void makeGuesses(DecisionNode tree) throws InterruptedException {
		Scanner userIn = new Scanner(System.in);
		if (tree.isQuestion()) {
			System.out.println(tree.getInfo());
			String response = userIn.next();
			if (response.equalsIgnoreCase("yes") && tree.getRight()!=null) {
				makeGuesses((DecisionNode) tree.getRight());
			}
			else if (tree.getLeft()!=null) {
				makeGuesses((DecisionNode) tree.getLeft());
			}
		}
		else {
			System.out.println("Is it " + tree.getInfo());
			String response = userIn.next();
			if (response.equalsIgnoreCase("yes")) {
				return;
			}
			if (response.equalsIgnoreCase("no")) {
				System.out.println("What was the correct animal?");
				String correctAnimal = userIn.next();
				System.out.println("What is a question that would be correct for " + correctAnimal + " and incorrect for " + tree.getInfo());
				userIn.nextLine();
				String userQuestion = userIn.nextLine();
				DecisionNode newLeft = new DecisionNode(tree.getInfo(), false);
				DecisionNode newRight = new DecisionNode(correctAnimal, false);
				tree.setLeft(newLeft);
				tree.setInfo(userQuestion);
				tree.setQuestion(true);
				tree.setRight(newRight);
			}
		}
		
			
	}
	public static void preorderPrint(DecisionNode tree) {
		if (tree == null) {
			return;
		}
		if (tree.isQuestion())
			System.out.println("Q: " + tree.getInfo());
		if (!tree.isQuestion())
			System.out.println("A: " + tree.getInfo());
		preorderPrint((DecisionNode) tree.getLeft());
		preorderPrint((DecisionNode) tree.getRight());
	}
	public static void printToFile(DecisionNode tree, PrintWriter writer) throws FileNotFoundException, UnsupportedEncodingException {
		if (tree == null) {
			return;
		}
		if (tree.isQuestion())
			writer.println("Q: " + tree.getInfo());
		if (!tree.isQuestion())
			writer.println("A: " + tree.getInfo());
		printToFile((DecisionNode) tree.getLeft(), writer);
		printToFile((DecisionNode) tree.getRight(), writer);
	}
	public static DecisionNode readFile(Scanner in, DecisionNode tree) {
		if (!in.hasNextLine()) {
			return null;
		}
		else {
			String line = in.nextLine();
			if ((line.charAt(0) + "").equalsIgnoreCase("Q")) {
				tree = new DecisionNode(line.substring(2),true);
				tree.setLeft(readFile(in,tree));
				tree.setRight(readFile(in, (DecisionNode) tree));
			}
			else {
				tree = new DecisionNode(line.substring(2), false);
			}
		}
			return tree;
	}
}
