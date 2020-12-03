package com.sebpo.foodbooking.pojo;

public class Data{
	private String name;
	private String token;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"name = '" + name + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}
