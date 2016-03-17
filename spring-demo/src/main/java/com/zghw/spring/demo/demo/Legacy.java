package com.zghw.spring.demo.demo;

public class Legacy {
	/**
	 * 假设这是遗留的代码不能修改，但是我门需要返回User是与这个对象不同的那个对象
	 * @return
	 */
		public User createUser(){
			User user =new User();
			user.setAge(5555);
			user.setCardID("1000");
			return user;
		}

		public void temp(String aa){
			System.out.println("我是临时的，来替换我把"+aa);
		}
	@Override
	public String toString() {
		return "Legacy []"+createUser();
	}
		
}
