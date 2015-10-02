package dropwizard.first.resources;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import dropwizard.first.core.Person;
import dropwizard.first.db.PersonDAO;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class FirstProjResource {

	private final String template;
    private final String defaultName;
    private final AtomicLong counter;
    private final PersonDAO peopleDAO;
    
   
    public FirstProjResource(PersonDAO pe, String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
        this.peopleDAO = pe;
    }
  
    @POST
    @Timed
    @Consumes("application/json")
    @UnitOfWork
    public Person create(Person param){
    	return peopleDAO.create(param);
    	
    }
    
    @PUT
    @Timed
    @Consumes("application/json")
    @UnitOfWork
    public Person update(Person param){
    	return peopleDAO.update(param);
    	
    }
    
    @GET
    @Timed
    @UnitOfWork
    public List<Person> listPeople() {
        return peopleDAO.findAll();
    }
}