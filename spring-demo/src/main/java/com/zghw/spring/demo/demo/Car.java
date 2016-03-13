package com.zghw.spring.demo.demo;

public class Car {
	private String name;
	private String number;
	private double price;
	private String brand;
	public Car(String name, double price) {
		this.name = name;
		this.price = price;
	}
	public Car(String name, String number, String brand) {
		this.name = name;
		this.number = number;
		this.brand = brand;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	@Override
	public String toString() {
		return "Car [name=" + name + ", number=" + number + ", price=" + price
				+ ", brand=" + brand + "]";
	}
	
}
