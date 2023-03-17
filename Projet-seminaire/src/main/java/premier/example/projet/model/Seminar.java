package premier.example.projet.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Data

@Entity
public class Seminar {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int fees;
	
	@OneToMany (mappedBy = "seminar")
	private Collection<Enrollment> Enrollment;
	
	@ManyToMany (fetch = FetchType.EAGER) 
	
	private Collection<Professor>  professor = new ArrayList<>();
	
	
	public Seminar(String name, int fees) {
		super();
		this.name = name;
		this.fees = fees;
	}
	public Seminar() {
		super();
		
	}
	public int getSeminarNmber() {
		return id;
	}
	public void setSeminarNmber(int seminarNmber) {
		this.id = seminarNmber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	
	@Override
	public String toString() {
		return "Seminar [seminarNmber=" + id + ", name=" + name + ", fees=" + fees + "]";
	}
	
	
	
	
	
	

}
