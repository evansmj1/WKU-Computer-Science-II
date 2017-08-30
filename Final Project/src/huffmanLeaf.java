
//questions about this go to valerie
//questions about the BSTNode component go to linnie
public class huffmanLeaf extends huffmanNode {

	public char character; //ASCII character represented by node
	
	public huffmanLeaf(char newChar, int freq){
		super(freq);//to make sure that the node is set up properly -L
		character=newChar;
	}
	
	//getter and setter
	public void setChar(char newChar) {
		character=newChar;
	}
	public char getChar(){
		return character;
	}
	
	public String toString() {
		return "Node with freq: "+frequency+ " and char "+ character;
	}
	
	@Override
	public int compareTo(huffmanLeaf node) {
		return (((huffmanNode) node).getFreq()-this.frequency); //returns a positive if freq of passed argument is smaller than that of calling node 
	}
	
}
