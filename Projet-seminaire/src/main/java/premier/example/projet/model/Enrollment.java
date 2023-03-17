package premier.example.projet.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
public class Enrollment {
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "marksReceived")
	private float marksReceived ;
		
	@ManyToOne
	 private Student student;
	 @ManyToOne
	 private Seminar seminar;
	 
	 public Enrollment( int marksReceived) {
			super();
			this.marksReceived = marksReceived;	
		}

	public Enrollment(Student student, Seminar seminar) {
		super();
		this.student = student;
		this.seminar = seminar;
	}


}