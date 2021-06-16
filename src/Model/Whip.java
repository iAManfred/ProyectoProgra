package Model;

public class Whip extends CondimentDecorator {
	Beverage1 beverage;
 
	public Whip(Beverage1 beverage) {
		this.beverage = beverage;
	}
 
	public String getDescription() {
		return beverage.getDescription() + ", Whip";
	}
 
	public double cost() {
		return .10 + beverage.cost();
	}
        
         
        
}
