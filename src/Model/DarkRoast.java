package Model;

public class DarkRoast extends Beverage1 {
	public DarkRoast() {
		description = "Dark Roast Coffee";
	}
 
        @Override
	public double cost() {
		return .99;
	}
}
