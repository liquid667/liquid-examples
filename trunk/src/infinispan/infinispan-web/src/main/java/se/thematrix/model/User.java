package se.thematrix.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 3426477320353647086L;

	private String name;
	private String address;
	private int age;

	public User() {}
	
	public User(String name, String address, int age) {
		super();
		this.name = name;
		this.address = address;
		this.age = age;
	}

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", address=" + address + ", age=" + age
				+ "]";
	}
}
