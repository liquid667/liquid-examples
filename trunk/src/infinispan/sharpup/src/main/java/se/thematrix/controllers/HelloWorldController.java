package se.thematrix.controllers;

import se.thematrix.cache.DataCacheImpl;
import se.thematrix.model.User;

/**
 * A typical simple backing bean, that is backed to <code>helloworld.jsp</code>
 * 
 */
public class HelloWorldController {
    
    //properties
	private String userId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;

	private DataCacheImpl cache = new DataCacheImpl();

	public HelloWorldController() {}
    
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Object[] getUsers(){
    	return cache.list().toArray();
    }

    /**
     * Method that is backed to a submit button of a form.
     */
    public String send(){
        //do real logic, return a string which will be used for the navigation system of JSF
        return "success";
    }
    
    public String add(){
    	int uId = Integer.parseInt(getUserId());
    	User user = new User(uId, getUserName(), getPassword(), getFirstName(), getLastName(), null);
    	
    	cache.put(uId, user);
    	
    	return "success";
    }
    
    public String delete(){
    	
    	int id = Integer.parseInt(getUserId());
    	
   		cache.remove(id);
    	
    	return "success";
    }
    
    public String login(){
    	
    	return "success";
    }
}
