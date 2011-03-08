package se.thematrix.cache;

import java.util.Collection;

import org.infinispan.Cache;
import org.infinispan.manager.CacheContainer;
import org.infinispan.manager.DefaultCacheManager;

import se.thematrix.model.User;

public class DataCacheImpl implements DataCache {
	
	private static CacheContainer container = new DefaultCacheManager("/infinispan-config.xml");
	private static Cache<Integer,User> cache = container.getCache();
	
	@Override
	public int size() {
		return cache.size();
	}

	@Override
	public Collection<User> list() {
		return cache.values();
	}

	@Override
	public void put(Integer key, User value) {
		cache.put(key, value);
	}

	@Override
	public void remove(Integer key) {
		cache.remove(key);		
	}

	@Override
	public User get(Integer key) {
		return cache.get(key);
	}
	
}
