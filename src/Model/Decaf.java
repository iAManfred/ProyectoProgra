package Model;

public class Decaf extends Beverage1 {
	public Decaf() {
		description = "Decaf Coffee";
	}
 
        @Override
	public double cost() {
		return 1.05;
	}
}
