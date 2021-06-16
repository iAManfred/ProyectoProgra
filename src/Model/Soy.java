package Model;

public class Soy extends CondimentDecorator {
	Beverage1 beverage;

	public Soy(Beverage1 beverage) {
		this.beverage = beverage;
	}

        @Override
	public String getDescription() {
		return beverage.getDescription() + ", Soy";
	}

        @Override
	public double cost() {
		return .15 + beverage.cost();
	}
         
}
