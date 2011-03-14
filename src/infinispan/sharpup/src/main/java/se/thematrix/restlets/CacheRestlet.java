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
	public String get(@PathParam("userId") Integer userId) {
		return "cache: " + cache.get(userId);
	}

	@PUT
	@Path("/cache/{userId}/{userName}/{password}/{firstName}/{lastName}")
	public void add(@PathParam("userId") Integer userId, 
					@PathParam("userName") String userName, 
					@PathParam("password") String password, 
					@PathParam("firstName") String firstName, 
					@PathParam("lastName") String lastName) {
		User u = new User(userId, userName, password, firstName, lastName, null);
		cache.put(userId, u);
	}

	@DELETE
	@Path("/cache/{key}")
	public void remove(@PathParam("userId") Integer userId) {
		cache.remove(userId);
	}

}
