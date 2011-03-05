package se.thematrix;

import java.util.Collection;

import org.infinispan.Cache;
import org.infinispan.manager.CacheContainer;
import org.infinispan.manager.DefaultCacheManager;

public class DataCacheImpl implements DataCache {

	private final static CacheContainer container = new DefaultCacheManager();
	private final static Cache<String,String> cache = container.getCache("user-cache");

	@Override
	public int size() {
		return cache.size();
	}

	@Override
	public Collection<String> list() {
		return cache.values();
	}

	@Override
	public void put(String key, String value) {
		cache.put(key, value);
	}

	@Override
	public void remove(String key) {
		cache.remove(key);		
	}

	@Override
	public String get(String key) {
		return cache.get(key);
	}
	
}
