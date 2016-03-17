package com.zghw.spring.demo.demo;

public class Computer {
	private CarSub cs;
	private CarSub cs1;
	private String test;
	@Override
	public String toString() {
		return "Computer [cs=" + cs + ", cs1=" + cs1 + "]";
	}
	public CarSub getCs() {
		return cs;
	}
	public void setCs(CarSub cs) {
		this.cs = cs;
	}
	public CarSub getCs1() {
		return cs1;
	}
	public void setCs1(CarSub cs1) {
		this.cs1 = cs1;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
}
