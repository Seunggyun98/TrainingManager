package app;

import java.util.ArrayList;

/**
 * 회원에 대한 정보를 담는 클래스
 * @author 승균
 *
 */
class Member {
	private String name;
	private String sex;
	private int age;
	private int id;
	private int type;

	public Member() {
		
	}
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
	public void setName(String name) {
		this.name=name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex=sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age=age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type=type;
	}
}
