package se.thematrix.restlets;


public interface Cache {
	   String getCache();
	   String get(Integer key);
	   void add(Integer userId, String userName, String password, String firstName, String lastName);
	   void remove(Integer key);
}
