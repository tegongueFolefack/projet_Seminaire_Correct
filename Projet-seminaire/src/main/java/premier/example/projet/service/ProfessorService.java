package premier.example.projet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import premier.example.projet.model.Enrollment;
import premier.example.projet.model.Params;
import premier.example.projet.model.Professor;
import premier.example.projet.model.Seminar;
import premier.example.projet.model.Student;
import premier.example.projet.repository.ProfessorRepository;
import premier.example.projet.repository.SeminarRepository;

@Data
@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private SeminarRepository seminarRepository;
	
	@Autowired
	private SeminarService seminarService;
	
	
	public Optional<Professor> getProfessorById(Long id){
		return professorRepository.findById(id);	
	}
	
	public Iterable<Professor> getAllProfessor(){
		return professorRepository.findAll();
	}
	
	public void deleteProfessor( Long id) {
		professorRepository.deleteById(id);
	}
	
	public Professor updateProfessor(Professor prof, long id ) {
		prof.setId(id);
		 prof = professorRepository.findById(id).get();
	   return professorRepository.save(prof);
	}
	
	public Professor saveProfessor(Professor professor) {
		Professor savedProfessor = professorRepository.save(professor);
		return savedProfessor;
	}

	public float getsalary(Long id) {
		 Professor p = this.getProfessorById(id).get();

		return p.getSalary();
		
	}
 
	public boolean verify(Long id) {
		 
	    if(this.getProfessorById(id).isEmpty())
	    	return false;
	    else
	    	return true;
	}

	
	


	
//	public List<Seminar> getSeminarsTaken(Long id){
//		List<Professor> prof = this.findByProfessor(id);
//		List <Seminar> seminars = new ArrayList<Seminar>();
//		for(Professor professor : prof) {
//			seminars.add((Seminar) professor.getSeminars());
//		}
//		return seminars;
//		
//	}
//	
//	
//	  
//	  public List<Professor> findByProfessor(long id) {
//		  return professorRepository. findByProfessor_id(id);
//	  }
		
	
}
