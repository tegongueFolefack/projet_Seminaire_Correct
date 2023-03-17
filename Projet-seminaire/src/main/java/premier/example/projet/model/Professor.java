package premier.example.projet.model;

import java.util.ArrayList;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity

@DiscriminatorValue("prof")
public class Professor extends Person {
	@Column(name="salary")
	private float salary;
		
	@ManyToMany (mappedBy = "professor")
	private Collection<Seminar> seminars = new ArrayList<>();
	
	public Professor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Professor(String name, char sex, String address, int phone, String email) {
		super(name, sex, address, phone, email);
		// TODO Auto-generated constructor stub
	}

	public Professor(String name, char sex, String address, int phone, String email, float salary) {
		super(name, sex, address, phone, email);
		this.salary = salary;
	}

	

	
	
	
}	


	