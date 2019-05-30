package org.spring.springboot.domain;

import java.sql.Timestamp;

public class User {
	private String id;
	
	private String userName;
	
	private String passWord;
	
	private String sex;
	
	private String height;
	
	private String weight;
	
	private String yaowei;
	
	private String tunwei;
	
	private String xuetang;
	
	private Timestamp firstTime;
	
	private String firstDate;
	
	

	public User() {
		super();
	}

	public User(String id, String sex, String height, String weight, String yaowei, String tunwei, String xuetang) {
		this.id = id;
		this.sex = sex;
		this.height = height;
		this.weight = weight;
		this.yaowei = yaowei;
		this.tunwei = tunwei;
		this.xuetang = xuetang;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getYaowei() {
		return yaowei;
	}

	public void setYaowei(String yaowei) {
		this.yaowei = yaowei;
	}

	public String getTunwei() {
		return tunwei;
	}

	public void setTunwei(String tunwei) {
		this.tunwei = tunwei;
	}

	public Timestamp getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Timestamp firstTime) {
		this.firstTime = firstTime;
	}

	public String getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}

	public String getXuetang() {
		return xuetang;
	}

	public void setXuetang(String xuetang) {
		this.xuetang = xuetang;
	}
	
	

	
	
}
