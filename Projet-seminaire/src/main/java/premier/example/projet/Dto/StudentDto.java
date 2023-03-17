package premier.example.projet.Dto;

import java.util.Collection;

import lombok.Data;
import premier.example.projet.model.Enrollment;

@Data
public class StudentDto extends PersonDto {
	
	
	private double averagemark;
	//private Collection<EnrollmentDto> Enrollment;

}
