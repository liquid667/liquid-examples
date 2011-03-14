package se.thematrix.cache;

import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.lucene.queryParser.ParseException;
import org.infinispan.Cache;
import org.infinispan.query.CacheQuery;
import org.infinispan.query.QueryFactory;
import org.infinispan.query.backend.QueryHelper;

import se.thematrix.model.User;

public class DataCacheImpl implements DataCache {
	
	private final static Cache<Integer,User> cache = MyCacheContainer.getCache("Users");
	private final static QueryHelper qh = new QueryHelper(cache, new Properties(), User.class);
	
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
	
	public boolean checkLogin(String userName, String password){
		QueryFactory qf = new QueryFactory(cache, qh);
		try {
			CacheQuery cq = qf.getBasicQuery("userName", userName);
			int hits = cq.getResultSize();
			if(hits > 0) return true;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
