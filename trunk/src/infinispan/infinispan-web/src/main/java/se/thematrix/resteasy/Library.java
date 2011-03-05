package se.thematrix.resteasy;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import se.thematrix.DataCacheImpl;

@Path("/library")
@Produces("text/plain")
public class Library {
	
	DataCacheImpl cache = new DataCacheImpl();
	
	
   @GET
   @Path("/caches")
   public String getCache() {
	   return cache.list().toString(); 
   }

   @GET
   @Path("/cache/{key}")
   public String get(@PathParam("key") String key) {
	   return "cache: " + cache.get(key);
   }

   @PUT
   @Path("/cache/{key}/{value}")
   public void add(@PathParam("key") String key, @PathParam("value") String value) {
	   cache.put(key, value);
   }
   
   @DELETE
   @Path("/cache/{key}")
   public void remove(@PathParam("key") String key){
	   cache.remove(key);
   }
}
   
