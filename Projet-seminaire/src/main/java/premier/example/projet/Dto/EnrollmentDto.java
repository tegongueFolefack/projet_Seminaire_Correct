package premier.example.projet.Dto;

import lombok.Data;
import premier.example.projet.model.Seminar;
import premier.example.projet.model.Student;
@Data
public class EnrollmentDto {
	private long id;
	int marksReceived ;
	 private StudentDto student;
	 private SeminarDto seminar;
	
}
