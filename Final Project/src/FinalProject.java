import java.io.*;
import java.util.*;

import ch08.trees.*;
import ch09.priorityQueues.Heap;

import support.BSTNode;  

public class FinalProject {
	static ArrayList<String> list = new ArrayList<String>();
	static ArrayList<String> decodedList = new ArrayList<String>();
	static String finalEncodedString = "";
	static String fileString = "";
	public static void main(String[] args) throws FileNotFoundException  {
		//Variables for Michael and Noah's Code:
		list.add("");
		decodedList.add("");

		//get file
		@SuppressWarnings("resource")
		Scanner conIn = new Scanner(System.in);

	    System.out.print("Name of file? ");
	    String name = conIn.next();
		Scanner fileIn = new Scanner(new File(name));
		if (fileIn.hasNext())
			fileString = fileIn.next();
		while (fileIn.hasNext()) {
			fileString = fileString + " " + fileIn.next();
		}
	    System.out.println(fileString);
	    // Set up file reading 
	    FileReader fin=null;
	    try {
	    	fin = new FileReader(name);
	    } catch(Exception e){
	    	System.out.print("*File not found* Please rerun the program");
	    	return;
	    }
	    
	    //get the initial heap of huffmanNodes
		Heap<huffmanNode> theHeap = getHeap(fin);
		
		
		//Linnie's edits start
		//this is the huffman node
		//contains BST nodes from the book files that have been extended into huffmanNode and huffmanLeaf
		huffmanNode finalHuffNode = getHuffTree(theHeap);
		
		//the class HuffmanBST is a 
		//binary search tree created with the huffman tree
		//extends binary search tree if you want to use that.
		//I think it would be easier to just use the the finalHuffNode above with recursion
		//but I though I would give you options
		HuffmanBST huffTree = new HuffmanBST(finalHuffNode);
		
		//things to keep in mind (from Linnie):
		//unless you want it changed, the character field can only be accessed by the huffmanLeaf class
		//so use instanceof or when you get to a leaf, only then can you call getChar()
		//let me know if there is anything else 
		
		
	//	System.out.print(theHeap.toString()); //to check the heap--remove before final
		//Linnie here- this should be empty after my part of the code executes, but will it still be needed?
		//let me know, I can see what I can do to clone or not empty the heap.
	//	System.out.println("Tree:");
	//	printTree(finalHuffNode);
		encodeHuffTree(finalHuffNode, "");
		String finalEncode = "";
		for (int i = 0; i < list.size(); i++) {
			finalEncode = finalEncode + list.get(i);
		}
		finalEncodedString = finalEncode;
		decodeHuffTree(finalHuffNode);
		finalEncode = "";
		for (int i = 0; i < fileString.length(); i++) {
			finalEncode = finalEncode + list.get(decodedList.indexOf(fileString.charAt(i) + ""));
		}
		System.out.println("Final Encoded Value: ");
		System.out.println(finalEncode);
		finalEncodedString = finalEncode;
		decodedList.clear();
		decodeHuffTree(finalHuffNode);
		String finalDecode = "";
		for (int i = 0; i < decodedList.size(); i++) {
			finalDecode = finalDecode + decodedList.get(i);
		}
		System.out.println("Final Decoded Value: ");
		System.out.println(finalDecode);
	}

	
	
	//returns the heap of frequency huffmanLeaves
	//questions about this go to valerie
	public static Heap<huffmanNode> getHeap(FileReader fin) {
			
		    String word; //Each set of characters
		    WordFreq charToTry; 
		    WordFreq charInTree; 
		    WordFreq charFromTree;
		    int minFreq=1;
		    int treeSize;
		    
		    BinarySearchTree<WordFreq> tree = new BinarySearchTree<WordFreq>();
		    
		    @SuppressWarnings("resource")
			Scanner wordsIn = new Scanner(fin); 
		
		    while (wordsIn.hasNext())      // while more words to process
		    {
		    	word = wordsIn.nextLine(); 
		    	//word = word.toLowerCase(); 
		    	
		    	for (int i=0; i<word.length();i++){ //for every character in the word
		               
				      charToTry = new WordFreq(word.charAt(i)); //get next character
				      charInTree = tree.get(charToTry); 
				      if (charInTree == null) //not in tree yet
				      {
				        // insert new char into tree
				        charToTry.inc();               // set frequency to 1
				        tree.add(charToTry);
				      }
				      else
				      {
				        // char already in tree, just increment frequency
				        charInTree.inc();
				      }
				}
				    
		    }
		    
		    //adds information to heap as huffmanLeaves
		    Heap<huffmanNode> theHeap = new Heap<huffmanNode>(400);
		    treeSize = tree.reset(BinarySearchTree.INORDER);
		   
		    for (int count = 1; count <= treeSize; count++) //for every character
		    {
		      charFromTree = tree.getNext(BinarySearchTree.INORDER); //gets the next character from the tree
		      
		      
		      if (charFromTree.freqIs() >= minFreq) //if the freq of the char is not 0
		      {
		    	 //add the huffmanLeaf to the heap
		    	huffmanNode temp=new huffmanLeaf(charFromTree.wordIs(), charFromTree.freqIs());
		    	theHeap.enqueue(temp);
		      }
		      
		      
		    }
			return theHeap;
		}
	
    
	
	//returns the huffman node that contains the huffman tree.	
	//Questions about this go to linnie
	private static huffmanNode getHuffTree(Heap<huffmanNode> theHeap){
		
		huffmanNode finalNode = new huffmanNode();
		huffmanNode tempNode = new huffmanNode();
		
		//create the huffman tree
		while(!theHeap.isEmpty()){
			
			//get a node
			finalNode= theHeap.dequeue();
			System.out.println("the node with the lowest frequency is "+finalNode.getFreq());
			//if the previous node was the last node, then the heap should be empty
			if (theHeap.isEmpty()){
				break;
			}
			//second smallest frequency
			tempNode=theHeap.dequeue();
			
			//make new node with combined frequencies 
			huffmanNode newNode = new huffmanNode(finalNode.getFreq() + tempNode.getFreq());
			
			//set the smallest frequency on the left
			newNode.setLeft(finalNode);
			System.out.println("setting the smallest as left: "+finalNode.getFreq());
			//second smallest on the right
			newNode.setRight(tempNode);
			System.out.println("setting the second smallest as right "+tempNode.getFreq());
			
			//add back to the heap to be sorted
			theHeap.enqueue(newNode);
			
			System.out.println("   ");
		}
		
		return finalNode;
		
	}
	public static void encodeHuffTree(huffmanNode tree, String encodedString) {
		if (tree == null)
			return;
		if (tree instanceof huffmanLeaf) {
			list.add(encodedString);
			System.out.println("coded value for " + ((huffmanLeaf) tree).getChar() + ": ");
			System.out.println(encodedString);
		}

		encodeHuffTree((huffmanNode) tree.getLeft(), encodedString + "0");
		encodeHuffTree((huffmanNode) tree.getRight(), encodedString + "1");
			
	}
	public static void printTree(huffmanNode tree) {
		if (tree == null)
			return;
		if (tree instanceof huffmanLeaf)
			System.out.println(((huffmanLeaf) tree).getChar());
		System.out.println(tree.getInfo());
		printTree((huffmanNode) tree.getLeft());
		printTree((huffmanNode) tree.getRight());
	}
	public static void decodeHuffTree(huffmanNode tree) {
		String decodedString = "";
		while (!finalEncodedString.equals("")) {
//			System.out.println(finalEncodedString);
			rNavigateHuffTree(tree, finalEncodedString);
		}
	}
	public static void rNavigateHuffTree(huffmanNode tree, String bitString) {
		if (tree == null)
			return;
		if (tree instanceof huffmanLeaf) {
			finalEncodedString = bitString;
			decodedList.add(((huffmanLeaf) tree).getChar() + "");
		}
		if (finalEncodedString.equals(""))
			return;
		if (bitString.charAt(0) == '0')
			rNavigateHuffTree((huffmanNode) tree.getLeft(), bitString.substring(1));
		if (bitString.charAt(0) == '1') {
			rNavigateHuffTree((huffmanNode) tree.getRight(), bitString.substring(1));
		}
	}
   
}