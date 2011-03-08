package se.thematrix.cache;

import java.util.Collection;

import se.thematrix.model.User;

public interface DataCache {
	Collection<User> list();
	void put(Integer key, User value);
	void remove(Integer key);
	User get(Integer key);
	int size();
}
