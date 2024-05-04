package restaurant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Restaurant {
	
	static final int NUM_FOOD = 6;
	static final int NUM_INFO = 3;
	static Order order = new Order();
	static User user;
	
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		ArrayList<Food> foodInfoList;
		int totalFoodCount = 0;
		
		System.out.println("Restaurant 고객 정보 입력");
		System.out.print("손님의 이름을 입력해주세요 : ");
		String userName = sc.nextLine();
		
		System.out.print("연락처를 입력해주세요(-제외하고 숫자만 입력해주세요) : ");
		int userPhone = sc.nextInt();
		sc.nextLine();
		
		System.out.print("멤버쉽 ID를 입력해주세요 : ");
		String userMemId = sc.nextLine();
		System.out.println();
		
		user = new User(userName, userPhone, userMemId);
		
		boolean quit = false;
		
		while(!quit) {
			menuIntroduction();
			
			try {
				System.out.print("메뉴 번호를 선택해주세요 : ");
				int numberSelection = sc.nextInt();
				
				if(numberSelection < 1 || numberSelection > 9) {
					System.out.println("1 부터 9 까지의 숫자를 입력하세요.");
				} else {
					switch(numberSelection) {
					case 1:
						menuGuestInfo(userName, userPhone, userMemId);
						break;
					case 2:
						menuOrderList();
						break;
					case 3:
						menuOrderClear();
						break;
					case 4:
						totalFoodCount = totalFileToFoodList();
						foodInfoList = new ArrayList<Food>();
						menuOrderAddFood(foodInfoList);
						break;
					case 5:
						menuOrderRemoveFoodCount();
						break;
					case 6:
						menuOrderRemoveFood();
						break;
					case 7:
						menuOrderBill();
						break;
					case 8:
						menuExit();
						quit = true;
						break;
					case 9:
						menuAdminLogin();
						break;
					}
				}
				
			} catch(OrderException e) {
				System.out.println(e);
				quit = true;
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("잘못된 메뉴 입력으로 종료합니다. ");
				quit = true;
			}
		}
		
	}
	

	public static void menuIntroduction() {
		System.out.println("╭──────────────────────────────────────╮");
		System.out.println("│                                      │");
		System.out.println("│       Welcome to the Restaurant      │");
		System.out.println("│                                      │");
		System.out.println("╰──────────────────────────────────────╯");
		System.out.println("────────────────────────────────────────");
		System.out.println("1.멤버쉽 정보확인 \t4.주문리스트에 음식 추가하기");
		System.out.println("2.주문리스트 보기  \t5.주문리스트 항목 수량줄이기");
		System.out.println("3.주문리스트 비우기 \t6.주문리스트 항목 삭제하기");
		System.out.println("7.영수증 표시  \t8.시스템 종료");
		System.out.println("9.관리자 로그인");
		System.out.println("────────────────────────────────────────");
	}
	//1. 고객 정보확인
	public static void menuGuestInfo(String userName, int userPhone, String userMemId) {
		System.out.println("현재 고객 정보 : ");
		System.out.println("이름 : "+user.getName()+", 연락처 : "+user.getPhone()+", 멤버쉽 ID : "+user.getMemId());
	}
	//2. 주문리스트 보기
	public static void menuOrderList() {
		if(order.orderCount >= 0) {
			order.printOrder();
		}
	}
	//3. 주문리스트 비우기
	public static void menuOrderClear() throws OrderException {
		
		if(order.orderCount == 0) {
			throw new OrderException("주문리스트에 항목이 없습니다.");
		} else {
			System.out.println("주문리스트에 모든 항목을 삭제하겠습니까? Y | N ");
			sc.nextLine();
			String str = sc.nextLine();
			
			if(str.toUpperCase().equals("Y") || str.toUpperCase().equals("y")) {
				System.out.println("주문리스트에 모든 음식을 삭제하였습니다. ");
				order.deleteOrder();
			}
			
		}
	}
	//4. 주문리스트에 음식 추가하기
	public static void menuOrderAddFood(ArrayList<Food> food) {
		sc.nextLine();
		foodList(food);
		
		order.printFoodList(food);
		boolean quit = false;
		
		while(!quit) {
			System.out.print("주문리스트에 추가할 음식의 번호를 입력하세요 : ");
			String str = sc.nextLine();
			
			boolean flag = false;
			int numId = -1;
			
			for(int i = 0 ;i<food.size();i++) {
				if(str.equals(food.get(i).getFoodId())) {
					numId = i;
					flag = true;
					break;
				}
			}
			
			if(flag) {
				System.out.println("주문리스트에 추가하겠습니까?  Y | N ");
				str = sc.nextLine();
				
				if(str.toUpperCase().equals("Y") || str.toUpperCase().equals("y")) {
					System.out.println(food.get(numId).getFoodName() + " 음식이 주문리스트에 추가되었습니다.");
					if(!isOrderInFood(food.get(numId).getFoodId())) {
						order.insertFood(food.get(numId));
					}
				}	
				quit = true;
			} else {
				System.out.println("다시 입력해주세요");
			}
		}
		
	}
	//5. 주문리스트 항목 수량줄이기
	public static void menuOrderRemoveFoodCount() throws OrderException {
		sc.nextLine();
		if(order.orderCount == 0) {
			throw new OrderException("주문리스트에 수량이 없습니다.");
		} else {
			menuOrderList();
			boolean quit = false;
			while(!quit) {
				String str = sc.nextLine();
				boolean flag = false;
				int numId = -1;
				
				for(int i=0;i<order.orderCount;i++) {
					if(str.equals(order.orderList.get(i).getFoodId())) {
						numId = i;
						flag = true;
						break;
					}
				}
				if(flag) {
					int quantity = order.orderList.get(numId).getQuantity();
					if(quantity >= 1) {
						System.out.println("현재수량" + quantity + " 줄이시겠습니까 Y | N ");
						str = sc.nextLine();
						if(str.toUpperCase().equals("Y") || str.toUpperCase().equals("y")) {
							order.orderList.get(numId).setQuantity(quantity -1);
							System.out.println(order.orderList.get(numId).getFoodId()+ "의 수량이 1 감소했습니다.");
						}
					}else {
						System.out.println("수량을 줄이시겠습니까? Y | N ");
						str = sc.nextLine();
						if(str.toUpperCase().equals("Y") || str.toUpperCase().equals("y")) {
							System.out.println(order.orderList.get(numId).getFoodId()+"의 수량이 0이되어 주문리스트에서 삭제.");
						}
					}
					quit = true;
				}else {
					System.out.println("다시 입력해주세요");
				}
			}
		}
	}
	//6. 주문리스트 항목 삭제하기
	public static void menuOrderRemoveFood() throws OrderException {
		sc.nextLine();
		if(order.orderCount == 0) {
			
			throw new OrderException("주문리스트에 항목이 없습니다.");
		} else {
			menuOrderList();
			boolean quit = false;
			
			while(!quit) {
				System.out.println("주문리스트에서 삭제할 음식의 번호를 입력하세요 : ");
				String str = sc.nextLine();
				boolean flag = false;
				int numId = -1;
				
				for(int i=0;i<order.orderCount;i++) {
					if(str.equals(order.orderList.get(i).getFoodId())) {
						numId = i;
						flag = true;
						break;
					}
				}
				if(flag) {
					System.out.println("주문리스트의 항목을 삭제하겠습니까? Y | N ");
					str = sc.nextLine();
					if(str.toUpperCase().equals("Y") || str.toUpperCase().equals("y")) {
						System.out.println(order.orderList.get(numId).getFoodId()+"음식이 주문리스트에서 삭제되었습니다.");
						order.removeOrder(numId);
					}
					quit = true;
				} else {
					System.out.println("올바르게 입력해주세요.");
				}
			}
		}
	}

	//7. 영수증
	public static void menuOrderBill() throws OrderException {
		sc.nextLine();
		if(order.orderCount == 0) {
			throw new OrderException("주문리스트에 항목이 없습니다.");
		} else {
			System.out.println("배달받을 분은 고객정보와 같습니까? Y | N");
			String str = sc.nextLine();
			
			if(str.toUpperCase().equals("Y") || str.toUpperCase().equals("y")) {
				System.out.println("주소를 입력해주세요 : ");
				String address = sc.nextLine();
				printBill(user.getName(), String.valueOf(user.getPhone()), address);
			}else {
				System.out.print("배달받을 고객 이름을 입력 : ");
				String name = sc.nextLine();
				System.out.print("배달받을 고객의 연락처를 입력 : ");
				String phone = sc.nextLine();
				System.out.print("배달받을 고객의 집주소를 입력 : ");
				String address = sc.nextLine();
				printBill(name, phone, address);
						
			}
		}
	}
	//8. 종료
	public static void menuExit() {
		System.out.println("8. 종료");
	}

	public static void foodList(ArrayList<Food> food) {
		setFileToFoodList(food);
	}

	public static boolean isOrderInFood(String FoodId) {
		return order.isOrderInFood(FoodId);
	}

	public static int totalFileToFoodList() {
		try {
			FileReader fr = new FileReader("Food.txt");
			BufferedReader br = new BufferedReader(fr);
			
			String str;
			
			int num = 0;
			while((str = br.readLine()) != null) {
				if (str.isEmpty()) {
					System.out.println("string");
				} else {
					if(str.contains("Food")) {
						++num;
					}
				}
			}
			br.close();
			fr.close();
			return num;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return 0;
	}
	
	public static void setFileToFoodList(ArrayList<Food> foodList) {
		try {
			FileReader fr = new FileReader("Food.txt");
			BufferedReader br = new BufferedReader(fr);
			
			String FoodId;
			String[] readFood = new String[3];
			
			while((FoodId = br.readLine()) != null) {
				if(FoodId.contains("Food")) {
					readFood[0] = FoodId;
					readFood[1] = br.readLine();
					readFood[2] = br.readLine();
				}
				Food food = new Food(readFood[0], readFood[1], Integer.parseInt(readFood[2]));
				foodList.add(food);
			
			}
			br.close();
			fr.close();
 		} catch (Exception e) {
 			e.printStackTrace();
 			System.out.println(e);
 		}
	}
	
	public static void printBill(String name, String valueOf, String address) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		String strDate = formatter.format(date);
		System.out.println();
		System.out.println("─────────────배달 받을 고객 정보─────────────");
		System.out.println("고객명 : "+name+"\t\t연락처 : " + user.getPhone());
		System.out.println("배송지 : "+address+"\t\t배송시간: "+ strDate);
		order.printOrder();
		
		int sum = 0;
		for(int i=0; i<order.orderCount; i++) {
			sum += order.orderList.get(i).getTotalPrice();
		}
		System.out.println("\t\t주문 총금액 : "+ sum +"원\n");
		System.out.println("────────────────────────────────────────");
		System.out.println();
	}
	
	//9. 관리자
	public static void menuAdminLogin() {
		System.out.println("관리자 정보를 입력하세요");
		System.out.print("아이디 : ");
		String adminId = sc.next();
		
		System.out.print("비밀번호 : ");
		String adminPW = sc.next();
		
		Admin admin = new Admin(user.getName(), user.getPhone());
		if(adminId.equals(admin.getId()) && adminPW.equals(admin.getPassword())) {
			String[] writeFood = new String[3];
			System.out.println("새로운 음식 메뉴를 추가하시겠습니까? Y | N");
			String str = sc.next();
			
			if(str.toUpperCase().equals("Y")) {
				System.out.print("새로운 음식 메뉴의 번호 : ");
				String str1 = sc.nextLine();
				
				writeFood[0] = sc.nextLine();			
				System.out.print("메뉴 이름 : ");
				writeFood[1] = sc.nextLine();
				
				System.out.print("음식 가격 : ");
				writeFood[2] = sc.nextLine();
				
				try {
					FileWriter fw = new FileWriter("Food.txt", true);
				
					for(int i=0;i<3;i++) {
						fw.write(writeFood[i] + "\n");
					}
					fw.close();
					System.out.println("새로운 음식이 메뉴에 추가되었습니다.");
				} catch (Exception e) {
					System.out.println(e);
				}
			} else {
				System.out.println("이름 : "+admin.getName()+", 연락처 : "+admin.getPhone());
				System.out.println("아이디 : "+admin.getId()+", 비밀번호 : "+admin.getPassword());
				}
			} else {
				System.out.println("관리자 정보가 일치하지 않습니다.");
		}
	}

	
}
