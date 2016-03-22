package com.zghw.spring.demo.demo.annontaion.app;

public class MyProfile {
	private String myLocationSQL;

	public MyProfile(){}
	
	public MyProfile(String myLocationSQL) {
		super();
		this.myLocationSQL = myLocationSQL;
	}

	public String getMyLocationSQL() {
		return myLocationSQL;
	}

	public void setMyLocationSQL(String myLocationSQL) {
		this.myLocationSQL = myLocationSQL;
	}
	
}
