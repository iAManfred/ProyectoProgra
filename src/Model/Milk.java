package Model;

public class Milk extends CondimentDecorator {
	Beverage1 beverage;

	public Milk(Beverage1 beverage) {
		this.beverage = beverage;
	}

    

        @Override
	public String getDescription() {
		return beverage.getDescription() + ", Milk";
	}


        @Override
	public double cost() {
		return .10 + beverage.cost();
	}
        
}
