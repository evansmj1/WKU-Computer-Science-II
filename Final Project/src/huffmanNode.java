import support.BSTNode;


//questions about this go to valerie
//questions about the BSTNode component go to linnie

//I extended the BSTNode to make the huffman nodes actual nodes -L
public class huffmanNode extends BSTNode implements Comparable<huffmanNode> {

	public int frequency; //frequency of the character or of the sum of the child characters

	
	public huffmanNode(int freq) {
		//calling the constructor for the node -L
		super(freq);
		frequency=freq;
	}
	
	public huffmanNode() {
		//calling the constructor for the node
		super(0);
		frequency=0;
	}
	
	//getter and setter
	public int getFreq(){
		return frequency;
	}
	public void setFreq(int freq){
		frequency=freq;
	}
	

	@Override
	public int compareTo(huffmanNode node) {
		return (((huffmanNode) node).getFreq()-this.frequency); //returns a positive if freq of passed argument is smaller than that of calling node 
	}
	public int compareTo(huffmanLeaf node) {
		return (((huffmanNode) node).getFreq()-this.frequency); //returns a positive if freq of passed argument is smaller than that of calling node 
	}
	
	public String toString() {
		return "Node with freq: "+frequency;
	}

}
