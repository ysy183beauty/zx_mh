package com.jeecms.core.entity;

import java.io.Serializable;
import java.util.Date;


public class MsgInterfaceCheck implements Serializable {
	private static final long serialVersionUID = 1L;

	public MsgInterfaceCheck(String id,String phone,Date getTime){
		this.getTime=getTime;
		this.id=id;
		this.phone=phone;
	}
	private java.lang.String id;
	private java.lang.String phone;

	public Date getGetTime() {
		return getTime;
	}

	public void setGetTime(Date getTime) {
		this.getTime = getTime;
	}

	private java.util.Date getTime;
	private java.lang.String username;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}