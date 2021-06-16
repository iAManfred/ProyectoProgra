package Model;

public class HouseBlend extends Beverage1 {
	public HouseBlend() {
		description = "House Blend Coffee";
	}
 
        @Override
	public double cost() {
		return .89;
	}
}
