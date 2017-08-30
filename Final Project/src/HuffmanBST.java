import ch08.trees.BinarySearchTree;

//questions about this go to linnie

//this class enables the huffman tree to have the operations of a binsarySearchTree preformed on it
//if you chose to just use the finalHuffNode in the main driver, this has no use
public class HuffmanBST extends BinarySearchTree{
	
	HuffmanBST(){
		super();
	}
	
	HuffmanBST(huffmanNode finalNode){
		super();
		super.root=finalNode; 
		
	}
	
	

}
