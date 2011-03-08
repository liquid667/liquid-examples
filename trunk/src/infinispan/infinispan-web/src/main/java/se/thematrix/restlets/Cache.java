package se.thematrix.restlets;


public interface Cache {
	   String getCache();
	   String get(Integer key);
	   void add(Integer key, String name, String address, int age);
	   void remove(Integer key);
}
