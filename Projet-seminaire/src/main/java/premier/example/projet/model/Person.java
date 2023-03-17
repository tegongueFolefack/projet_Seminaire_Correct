package premier.example.projet.model;

import javax.persistence.Column;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE" )
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private String name;
	private char sex;
	private String address;
	private int phone;
	private String email;
	
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(String name, char sex, String address, int phone, String email) {
		super();
		this.name = name;
		this.sex = sex;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	
	
	
	
	
	
	}


	

