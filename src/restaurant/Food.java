package restaurant;

public class Food extends OrderInfo {
	
	public Food() {
		
	}
	
	
	
	public Food(String FoodId, String FoodName, int FoodPrice) {
		super(FoodId, FoodName, FoodPrice);
	}

	

	@Override
	public String getFoodId() {
		return FoodId;
	}

	@Override
	public String getFoodName() {
		return FoodName;
	}

	@Override
	public int getFoodPrice() {
		return FoodPrice;
	}

	@Override
	public void setFoodId(String foodId) {
		this.FoodId = FoodId;
	}

	@Override
	public void setFoodName(String foodname) {
		this.FoodName = FoodName;
	}

	@Override
	public void setFoodPrice(int foodPrice) {
		this.FoodPrice = FoodPrice;
	}
	
}
