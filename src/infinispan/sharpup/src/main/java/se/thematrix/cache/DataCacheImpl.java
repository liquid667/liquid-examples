package se.thematrix.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.infinispan.Cache;
import org.infinispan.query.CacheQuery;
import org.infinispan.query.QueryFactory;
import org.infinispan.query.backend.QueryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.thematrix.model.User;

public class DataCacheImpl implements DataCache {
	
	private static final Logger log = LoggerFactory.getLogger(DataCacheImpl.class);
	
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
		log.debug("userName:{}, passWord:{}", userName, password);
		QueryFactory qf = new QueryFactory(cache, qh);
		try {
			//CacheQuery cq = qf.getBasicQuery("userName", userName);
			MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_29, new String[]{"userName", "password"}, new StandardAnalyzer(Version.LUCENE_29));
			String query = "userName:" + userName+" AND password:" +password;
			
			Query luceneQuery = parser.parse(query);
			
			CacheQuery cq = qf.getQuery(luceneQuery, User.class);
			
			int hits = cq.getResultSize();
			if(hits > 0) return true;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<User> searchForUser() {
		return new ArrayList();
	}
	
}
