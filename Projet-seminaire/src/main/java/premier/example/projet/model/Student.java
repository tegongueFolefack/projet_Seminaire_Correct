package premier.example.projet.model;

import java.util.Collection;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "student")
@DiscriminatorValue("stud")
public class Student extends Person {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentNumber;
	
	private double averagemark;
	
	@OneToMany (mappedBy = "student")
	private Collection<Enrollment> Enrollment;
	
	public Student() {
		super();
	}

	public Student(String name, char sex, String address, int phone, String email) {
		super(name, sex, address, phone, email);
		// TODO Auto-generated constructor stub
	}

	public Student(String name, char sex, String address, int phone, String email, Long studentNumber,
			double averagemark) {
		super(name, sex, address, phone, email);
		this.studentNumber = studentNumber;
		this.averagemark = averagemark;
		
	}

	


	

	

	

	

	



	
	
	
	
	



	
	
	



}
