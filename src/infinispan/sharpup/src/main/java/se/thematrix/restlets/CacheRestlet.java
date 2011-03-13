package se.thematrix.restlets;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import se.thematrix.cache.DataCacheImpl;
import se.thematrix.model.User;

@Path("/infinispan")
@Produces("text/plain")
public class CacheRestlet implements Cache {

	DataCacheImpl cache = new DataCacheImpl();

	@GET
	@Path("/caches")
	public String getCache() {
		return cache.list().toString();
	}

	@GET
	@Path("/cache/{key}")
	public String get(@PathParam("key") Integer key) {
		return "cache: " + cache.get(key);
	}

	@PUT
	@Path("/cache/{key}/{name}/{address}/{age}")
	public void add(@PathParam("key") Integer key, @PathParam("name") String name, @PathParam("address") String address, @PathParam("age") int age) {
		User u = new User(name, address);
		cache.put(key, u);
	}

	@DELETE
	@Path("/cache/{key}")
	public void remove(@PathParam("key") Integer key) {
		cache.remove(key);
	}

}
