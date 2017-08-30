
public class Driver {
	public static void main(String [] args) {
		Polynomial poly = new Polynomial();
		poly.addTerm(3,4);
		poly.addTerm(4, 4);
		poly.addTerm(-4, 4);
		System.out.println(poly.get(0).toString());
	}
}
