package se.thematrix.controllers;

import se.thematrix.cache.DataCacheImpl;

public class UserSearchController {
	
	private DataCacheImpl cache = new DataCacheImpl();

	private String queryString;
	
	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String searchUser(){
		
		cache.searchForUser();
		return "success";
	}

}
