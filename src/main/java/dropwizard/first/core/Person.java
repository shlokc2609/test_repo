package dropwizard.first.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ser.Serializers;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
@Entity
@Table(name = "person")
@NamedQueries({
    @NamedQuery(
            name = "com.example.helloworld.core.Person.findAll",
            query = "SELECT p FROM Person p"
    )
})
public class Person implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "name", nullable = false)
	private String name;
	
   
    public Person() {
        // Jackson deserialization
    }

    public Person(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description=description;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }
    
    @JsonProperty
    public String getDesc() {
        return description;
    }
   
    public void setDesc(String description) {
        this.description = description;
    }
    
    @JsonProperty
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    

}