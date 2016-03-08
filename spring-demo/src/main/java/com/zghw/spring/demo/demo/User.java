package com.zghw.spring.demo.demo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class User {
	private String cardID;
	private String name;
	private int age;
	private double money;
	private boolean isMarried;
	private WebSite[] arrayWebSite;
	private List<Car> listCar;
	private Map<String,WebSite> registedWebSite;
	private Fruit fruit;
	private Properties systemProp;
	private Properties mapProp;
	private Date birthday;
	private Class clazz;
	private User userChild;
	
	public static Properties getProperties(){
		return System.getProperties();
	}
	
	public String getCardID() {
		return cardID;
	}
	public void setCardID(String cardID) {
		this.cardID = cardID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public boolean isMarried() {
		return isMarried;
	}
	public void setIsMarried(boolean isMarried) {
		this.isMarried = isMarried;
	}
	public WebSite[] getArrayWebSite() {
		return arrayWebSite;
	}
	public void setArrayWebSite(WebSite[] arrayWebSite) {
		this.arrayWebSite = arrayWebSite;
	}
	public List<Car> getListCar() {
		return listCar;
	}
	public void setListCar(List<Car> listCar) {
		this.listCar = listCar;
	}
	public Map<String, WebSite> getRegistedWebSite() {
		return registedWebSite;
	}
	public void setRegistedWebSite(Map<String, WebSite> registedWebSite) {
		this.registedWebSite = registedWebSite;
	}
	public Fruit getFruit() {
		return fruit;
	}
	public void setFruit(Fruit fruit) {
		this.fruit = fruit;
	}
	public Properties getSystemProp() {
		return systemProp;
	}
	public void setSystemProp(Properties systemProp) {
		this.systemProp = systemProp;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Class getClazz() {
		return clazz;
	}
	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}
	public User getUserChild() {
		return userChild;
	}
	public void setUserChild(User userChild) {
		this.userChild = userChild;
	}
	
	public Properties getMapProp() {
		return mapProp;
	}

	public void setMapProp(Properties mapProp) {
		this.mapProp = mapProp;
	}

	@Override
	public String toString() {
		return "User [cardID=" + cardID + ", name=" + name + ", age=" + age
				+ ", money=" + money + ", isMarried=" + isMarried
				+ ", arrayWebSite=" + Arrays.toString(arrayWebSite)
				+ ", listCar=" + listCar + ", registedWebSite="
				+ registedWebSite + ", fruit=" + fruit + ", systemProp="
				+ systemProp + ", mapProp=" + mapProp + ", birthday="
				+ birthday + ", clazz=" + clazz + ", userChild=" + userChild
				+ "]";
	}

}
