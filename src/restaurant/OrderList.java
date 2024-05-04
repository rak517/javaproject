package restaurant;

import java.io.Serializable;

public class OrderList implements Serializable {
	private Food infoFood;
	private String FoodId;
	private int quantity;
	private int totalPrice;
	
	public OrderList() {
	}
	
	public OrderList(Food foodInfo) {
		this.infoFood = foodInfo;
		this.FoodId = infoFood.getFoodId();
		this.quantity = 1;
		updateTotalPrice();
	}
	

	

	public Food getInfoFood() {
		return infoFood;
	}



	public String getFoodId() {
		return FoodId;
	}



	public int getQuantity() {
		return quantity;
	}



	public int getTotalPrice() {
		return totalPrice;
	}



	public void setInfoFood(Food infoFood) {
		this.infoFood = infoFood;
	}



	public void setFoodId(String foodId) {
		FoodId = foodId;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.updateTotalPrice();
	}



	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}



	public void updateTotalPrice() {
		totalPrice = this.infoFood.getFoodPrice() * this.quantity;
	}
	
	
	
	
}
