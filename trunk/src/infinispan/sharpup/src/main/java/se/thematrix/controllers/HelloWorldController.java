package se.thematrix.controllers;

import javax.ejb.EJB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.thematrix.dao.BreedDao;
import se.thematrix.dao.DogDao;
import se.thematrix.model.Breed;
import se.thematrix.model.Dog;

/**
 * A typical simple backing bean, that is backed to <code>helloworld.jsp</code>
 * 
 */
public class HelloWorldController {
	
	private final static Logger log = LoggerFactory.getLogger(HelloWorldController.class);
    
    //properties
	private String userId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	
	@EJB(name="dogDao")
	private DogDao dogDao;
	
	@EJB(name="breedDao")
	private BreedDao breedDao;

	//private DataCacheImpl cache = new DataCacheImpl();
	
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
//		return cache.list().toArray();
		return null;
    }

    /**
     * Method that is backed to a submit button of a form.
     */
    public String send(){
        //do real logic, return a string which will be used for the navigation system of JSF
        return "success";
    }
    
    public String add(){
//    	int uId = (cache.size() + 1);
//    	User user = new User(uId, getUserName(), getPassword(), getFirstName(), getLastName(), null);
//    	
//    	cache.put(uId, user);
    	
		Breed collie = new Breed();
		collie.setName("Collie");
		breedDao.persist(collie);
		
		Dog dina = new Dog();
		dina.setName("Dina");
		dina.setBreed(collie);
		long dinaId = dina.getId();
		dogDao.persist(dina);
		
		Dog dd = dogDao.findById(dinaId);
		log.debug("dinaDog: {}", dd);
    	
    	return "success";
    }
    
    public String delete(){
//    	
//    	int id = Integer.parseInt(getUserId());
//    	
//   		cache.remove(id);
    	
    	return "success";
    }
    
    public String edit(){
    	
//    	int id = Integer.parseInt(getUserId());
//    	User u = cache.get(id);
//    	
//    	setUserId(getUserId());
//    	setUserName(u.getUserName());
//    	setFirstName(u.getFirstName());
//    	setLastName(u.getLastName());
//    	setPassword(u.getPassword());
    	
    	return "success";
    }
    
    public String update(){
//    	int uId = Integer.parseInt(getUserId());
//    	User user = new User(uId, getUserName(), getPassword(), getFirstName(), getLastName(), null);
//    	
//    	cache.put(uId, user);
//    	
    	return "success";
    }
    
    public String login(){
    	boolean isValid = true;//cache.checkLogin(getUserName(), getPassword());
    	
    	if(!isValid){
    		return "skit";
    	}
    	
    	return "success";
    }
}
