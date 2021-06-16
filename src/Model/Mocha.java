package Model;



public class Mocha extends CondimentDecorator {
	Beverage1 beverage;
 
	public Mocha(Beverage1 beverage) {
		this.beverage = beverage;
	}
 
        @Override
	public String getDescription() {
		return beverage.getDescription() + ", Mocha";
	}
 
        @Override
	public double cost() {
		return .20 + beverage.cost();
	}

  

}
