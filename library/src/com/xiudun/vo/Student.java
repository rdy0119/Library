/**
 * 
 */
package com.xiudun.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 *	学生
 */
public class Student {

	private String id;
	private String username;
	private String sex;
	private String age;
	//只保存图书的Key：bag=[a, b]
	private List<String> bag = new ArrayList<>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public List<String> getBag() {
		return bag;
	}
	public void setBag(List<String> bag) {
		this.bag = bag;
	}
	
}






























