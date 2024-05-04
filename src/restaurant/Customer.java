package restaurant;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Comparable<Customer> , Serializable{
	private String name;
	private int phone;
	private String memId;
	
	public Customer() {
	}

	public Customer(String name, int phone) {
		this.name = name;
		this.phone = phone;
	}

	public Customer(String name, int phone, String memId) {
		super();
		this.name = name;
		this.phone = phone;
		this.memId = memId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(memId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(memId, other.memId);
	}

	@Override
	public int compareTo(Customer o) {
		return this.phone - o.phone;
	}
	
	
	
}
