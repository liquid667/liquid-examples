package se.thematrix.controllers;

import se.thematrix.DataCacheImpl;

/**
 * A typical simple backing bean, that is backed to <code>helloworld.jsp</code>
 * 
 */
public class HelloWorldController {
    
    //properties
	private String name;
	private String key;

	private DataCacheImpl cache = new DataCacheImpl();

	public HelloWorldController() {}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public Object[] getNames(){
    	return cache.list().toArray();
    }

	public int getSize(){
    	return cache.size();
    }

    /**
     * Method that is backed to a submit button of a form.
     */
    public String send(){
        //do real logic, return a string which will be used for the navigation system of JSF
        return "success";
    }
    
    public String add(){
    	
    	cache.put(getName(), getName());
    	
    	return "success";
    }
    
    public String delete(){
    	
//    	FacesContext context = FacesContext.getCurrentInstance();
//    	String thisKey = (String)context.getExternalContext().getRequestParameterMap().get("key");
    	String id = getKey();
    	
    	if(id != null){
    		cache.remove(id);
    	}
    	
    	return "success";
    }
}
