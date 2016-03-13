package com.zghw.spring.demo.demo;

public class RelationBean {
	private User user; 
	public RelationBean(){
		System.out.println("我依赖其他人，我时不是最后创建");
	} 
	
	public void init(){
		System.out.println("invoke init()");
	}
	
	public void destroy(){
		System.out.println("invoke destroy()");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "RelationBean [user=" + user + "]";
	}
	
}
