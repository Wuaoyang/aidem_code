package com.aidem.cn;

public class Person implements Cloneable {
	private int age;
	private String name;
	private Address address;

	public Person(int age, String name, Address address) {
		this.age = age;
		this.name = name;
		this.address = address;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String display() {
		return "Person [age=" + age + ", name=" + name + ", address=" + address + "]";
	}
}

