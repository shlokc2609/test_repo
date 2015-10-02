package dropwizard.first.db;
import java.util.List;

import org.hibernate.SessionFactory;

import com.google.common.base.Optional;

import dropwizard.first.core.Person;
import io.dropwizard.hibernate.AbstractDAO;
public class PersonDAO extends AbstractDAO<Person>{

	public PersonDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
//		ss = sessionFactory;
		// TODO Auto-generated constructor stub
	}
    public Optional<Person> findById(Long id) {
        return Optional.fromNullable(get(id));
    }
   
    public Person create(Person person) {
    
        return persist(person);
    }
	public List<Person> findAll() {
		// TODO Auto-generated method stub
	
		return list(namedQuery("com.example.helloworld.core.Person.findAll"));
	}
	public Person update(Person param) {
		// TODO Auto-generated method stub
		Person person = this.get(param.getId());
		if(param.getName() !=null)
		{
			person.setName(param.getName());
		}
		if(param.getDesc() !=null)
		{
			person.setDesc(param.getDesc());
		}
		persist(person);
		return null;
	}

}
