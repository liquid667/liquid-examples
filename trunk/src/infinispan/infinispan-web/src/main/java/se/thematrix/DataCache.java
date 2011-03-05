package se.thematrix;

import java.util.Collection;

public interface DataCache {
	Collection<String> list();
	void put(String key, String value);
	void remove(String key);
	String get(String key);
	int size();
}
