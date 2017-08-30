import support.BSTNode;


public class DecisionNode extends BSTNode<String>{
	private boolean question;
	
	public DecisionNode(String info, boolean questionOrAnswer) {
		super(info);
		question = questionOrAnswer;
		
	}
	public boolean isQuestion() {
		if (question)
			return true;
		return false;
	}
	public void setQuestion(boolean state) {
		question = state;
		
	}

}
