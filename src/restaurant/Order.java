package restaurant;

import java.util.ArrayList;

public class Order implements OrderInterface {
	public ArrayList<OrderList> orderList = new ArrayList<OrderList>();
	public static int orderCount = 0;
	
	
	@Override
	public void printFoodList(ArrayList<Food> foodList) {
		for (int i=0; i<foodList.size(); i++) {
			Food food = foodList.get(i);
			System.out.print(food.getFoodId() + " │ ");
			System.out.print(food.getFoodName() + " \t│ ");
			System.out.print(food.getFoodPrice() + "\t│ ");
			System.out.println("");
		}
	}

	@Override
	public boolean isOrderInFood(String FoodId) {
		boolean flag = false;
		
		for(int i=0;i<orderCount;i++) {
			if(FoodId.equals(orderList.get(i).getFoodId())) {
				orderList.get(i).setQuantity(orderList.get(i).getQuantity()+1);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public void insertFood(Food food) {
		OrderList foodInfo = new OrderList(food);
		orderList.add(foodInfo);
		orderCount = orderList.size();
		
	}

	@Override
	public void removeOrder(int numId) {
		orderList.remove(numId);
		orderCount = orderList.size();
	}

	@Override
	public void deleteOrder() {
		orderList.clear();
		orderCount = 0;
		
	}
	
	public void printOrder() {
		System.out.println("주문리스트 상품 목록 : ");
		System.out.println("────────────────────────────────────────");
		System.out.println("   음식번호 \t│  수량 \t│   합계");
		for(int i=0;i<orderList.size();i++) {
			System.out.print("   "+ orderList.get(i).getFoodId()+" \t│");
			System.out.print("   "+ orderList.get(i).getQuantity()+" \t│");
			System.out.print("   "+ orderList.get(i).getTotalPrice());
			System.out.println("  ");
		}
		System.out.println("────────────────────────────────────────");
	}
	
}
