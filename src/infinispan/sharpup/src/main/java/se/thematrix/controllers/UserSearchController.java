package se.thematrix.controllers;

public class UserSearchController {
	
	public String queryString;
	
	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String searchUser(){
		return "success";
	}

}
