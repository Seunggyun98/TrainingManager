package GUITest;

import java.util.ArrayList;

class Member {
	private String name;
	private String sex;
	private int age;
	private int id;
	private int type;

	
	public Member(int id, String name, String sex, int age,int type ) {
		this.name=name;
		this.sex=sex;
		this.age=age;
		this.id=id;
		this.type=type;
	}


	public String getName() {
		return name;
	}

	public String getSex() {
		return sex;
	}

	public int getAge() {
		return age;
	}

	public int getId() {
		return id;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type=type;
	}
}
