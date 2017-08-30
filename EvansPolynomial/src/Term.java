
public class Term implements Comparable{
	private int coefficient;
	private int degree;
	public Term (int co, int deg) {
		coefficient = co;
		degree = deg;
	}
	public int getDegree() {
		return degree;
	}
	public int getCoefficient() {
		return coefficient;
	}
	public void setDegree(int newDegree) {
		degree = newDegree;
	}
	public void setCoefficient(int newCoefficient) {
		coefficient = newCoefficient;
	}
	public int compareTo(Object t1) {
		if (this.degree == ((Term) t1).getDegree()) {
			return 0;
		}
		if (this.degree > ((Term) t1).getDegree())
			return 1;
		return -1;
	}
	public boolean equals(Object t1) {
		if (this.degree == ((Term) t1).getDegree()) {
			return true;
		}
		return false;
	}
	public String toString() {
		if (degree > 0)
			return coefficient + "X" + "^" + degree;
		return coefficient + "";
	}
}
