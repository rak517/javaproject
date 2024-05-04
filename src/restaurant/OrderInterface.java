package restaurant;

import java.util.ArrayList;

public interface OrderInterface {
	void printFoodList(ArrayList<Food> foodList);
	boolean isOrderInFood (String id);
	void insertFood(Food p);
	void removeOrder(int numId);
	void deleteOrder();
}
