package restaurant;

public abstract class OrderInfo {
	String FoodId;
	String FoodName;
	int FoodPrice;
	
	public OrderInfo() {
	}

	public OrderInfo(String foodId, String foodName, int foodPrice) {
		super();
		FoodId = foodId;
		FoodName = foodName;
		FoodPrice = foodPrice;
	}

	public abstract String getFoodId();

	public abstract String getFoodName();

	public abstract int getFoodPrice();

	public abstract void setFoodId(String foodId);

	public abstract void setFoodName(String foodname);

	public abstract void setFoodPrice(int foodPrice);
	
	
	
}
