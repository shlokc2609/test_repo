package dropwizard.first.resources;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import dropwizard.first.core.Person;
import dropwizard.first.db.PersonDAO;
import io.dropwizard.testing.junit.ResourceTestRule;
public class FirstProjResourceTest {
	private static final PersonDAO dao = mock(PersonDAO.class);

	@ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new FirstProjResource(dao, null, null))
            .build();
	private final Person person = new Person(1, "blah", "blah@example.com");
	ArrayList<Person> ar = new ArrayList<Person>();
	
	@Before
    public void setup() {
		ar.add(person);	
        when(dao.findAll()).thenReturn(ar);
        // we have to reset the mock after each test because of the
        // @ClassRule, or use a @Rule as mentioned below.
        reset(dao);
    }
	@Test
    public void testGetPerson() {
		System.out.println(resources.client().target("/hello-world").request().get());
        assertThat(resources.client().target("/hello-world").request().get())
                .isEqualTo(ar);
        verify(dao).findAll();
    }

	
	
	

}
