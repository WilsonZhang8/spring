package com.zghw.spring.demo.demo;

public class CarFactory {
	private CarFactory(){
	}
	public CarSub getCar(String namefactory,int countfactory){
		return new CarSub(namefactory,countfactory);
	}
	public CarSub getCar(String namefactory){
		return new CarSub(namefactory);
	}
	public static CarFactory getInstance(String factoryName){
		System.out.println(factoryName+"静态工厂方法执行");
		return new CarFactory();
	}
}
