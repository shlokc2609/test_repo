package dropwizard.first;

import dropwizard.first.core.Person;
import dropwizard.first.db.PersonDAO;
import dropwizard.first.resources.FirstProjResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class FirstProjApplication extends Application<FirstProjConfiguration> {
	
	private final HibernateBundle<FirstProjConfiguration> hibernateBundle =
            new HibernateBundle<FirstProjConfiguration>(Person.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(FirstProjConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };
            
	public static void main(String[] args) throws Exception {
        new FirstProjApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<FirstProjConfiguration> bootstrap) {
        // nothing to do yet
    	bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(FirstProjConfiguration configuration,
                    Environment environment) {
        // nothing to do yet
    	final PersonDAO dao = new PersonDAO(hibernateBundle.getSessionFactory());
    	final FirstProjResource resource = new FirstProjResource(dao,
    	        configuration.getTemplate(),
    	        configuration.getDefaultName()
    	    );
    	
    	    environment.jersey().register(resource);
    }


}
