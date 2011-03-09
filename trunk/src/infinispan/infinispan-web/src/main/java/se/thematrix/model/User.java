package se.thematrix.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 3426477320353647086L;

	private String name;
	private String address;
//	private int age;
//	private String phone;

//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}

	public User() {}
	
	public User(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

//	public User(String name, String address, int age) {
//		super();
//		this.name = name;
//		this.address = address;
//		this.age = age;
//		this.phone = "0313226526";
//	}

//	public User(String name, String address, int age, String phone) {
//		super();
//		this.name = name;
//		this.address = address;
//		this.age = age;
//		this.phone = phone;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", address=" + address + "]";
	}

//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}
//
//	@Override
//	public String toString() {
//		return "User [name=" + name + ", address=" + address + ", age=" + age
//				+ ", phone=" + phone + "]";
//	}
	
}
