package premier.example.projet.Dto;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Data;
import premier.example.projet.model.Seminar;

@Data
public class ProfessorDto extends PersonDto {
	
	private float salary;
	//private Collection<SeminarDto> seminars = new ArrayList<>();


	
}
