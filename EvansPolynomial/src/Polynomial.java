import ch06.lists.ArrayIndexedList;


public class Polynomial extends ArrayIndexedList {
	public Polynomial() {
		super();
	}
	public void addTerm(int coefficient, int degree) {
		Term newTerm = new Term(coefficient, degree);
		if (coefficient == 0)
			return;
		find(newTerm);
		if (found) {
			Term newTerm2 = new Term(coefficient + ((Term) get(location)).getCoefficient(), degree);
			set(location, newTerm2);
		}
		add(new Term(coefficient, degree));
	}

}
