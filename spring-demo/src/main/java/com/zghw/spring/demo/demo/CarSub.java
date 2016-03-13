package com.zghw.spring.demo.demo;

import org.springframework.beans.factory.annotation.Autowired;

public class CarSub {
	private String carName;
	private int cout;

	public int getCout() {
		return cout;
	}
	@Autowired
	public void setCout(int cout) {
		this.cout = cout;
	}

	public CarSub(String carName, int count) {
		this.cout = count;
		this.carName =carName;
	}

	public CarSub(String carName) {
		this.carName =carName;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	@Override
	public String toString() {
		return "CarSub [carName=" + carName + ", cout=" + cout + "]";
	}
}
