package premier.example.projet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import premier.example.projet.model.Params;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import premier.example.projet.Dto.ProfessorDto;
import premier.example.projet.Dto.SeminarDto;
import premier.example.projet.Dto.StudentDto;
import premier.example.projet.model.Professor;
import premier.example.projet.model.Seminar;
import premier.example.projet.model.Student;
import premier.example.projet.service.ProfessorService;

@RestController
@RequestMapping("/professor")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfessorController {
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("/{id}")
//	public Iterable<Professor> getProfessor(){
//		 return    professorService.getProfessor();
//	}
//	
	public ProfessorDto getProfessorById(@PathVariable Long id){
		Optional<Professor> professor = professorService.getProfessorById(id);
		
		ProfessorDto professorResponse = modelMapper.map(professor.get(), ProfessorDto.class);
	     return professorResponse;
		
	}
	
	
	@GetMapping()
	public List<ProfessorDto> findAll() {
		
		Iterable<Professor> professors = professorService.getAllProfessor();
		List<ProfessorDto> professorDtoL = new ArrayList<>();
		for(Professor  professor : professors) {
			professorDtoL.add(modelMapper.map(professor , ProfessorDto.class));
		}
		return professorDtoL;
		
	}
	

	@PostMapping("/prof")
//	public Professor addProfessor(@RequestBody Professor professor) {
//		return this. professorService.saveProfessor(professor);
//	}
    
   public ProfessorDto  addProfessor(@RequestBody ProfessorDto professorDto) {
		
		// convert DTO to entity
	Professor professorRequest = modelMapper.map(professorDto, Professor.class);
    Professor professor= professorService.saveProfessor(professorRequest);
		
 // convert entity to DTO
	ProfessorDto professorResponse = modelMapper.map(professor, ProfessorDto.class);
	this.professorService.saveProfessor(professorRequest);
	return professorResponse;
   }
	
	@PutMapping("/prof/{id}")
	public ProfessorDto updateProfessorr( @RequestBody Professor professorDto, @PathVariable long id ) {

		Optional<Professor> oldStudent = this.professorService.getProfessorById(id);
		Professor old = oldStudent.get();
			
		if(!oldStudent.isEmpty()) {
				modelMapper.map(professorDto,old);
				Professor newProfessor =modelMapper.map(professorDto,Professor.class);
				newProfessor = this.professorService.updateProfessor( old,id);
				
				return modelMapper.map(newProfessor,ProfessorDto.class);
		}else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NOT FOUND");
			}

	
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteProfessor(@PathVariable Long id) {
		 professorService.deleteProfessor(id);
		
    }
	
	@GetMapping("/salary/{id}")
	public float salary(@PathVariable Long id) {
		
		return this.professorService.getsalary(id);
	}
	
//	@GetMapping("getsem/{id}")
//	public List<SeminarDto> getseminar(@PathVariable long id){
//		if(this.professorService.verify(id)) {
//			List <Seminar> seminars = this.professorService.getSeminarsTaken(id);
//			List <SeminarDto> list = new ArrayList<SeminarDto>();
//			for (Seminar seminar : seminars) {
//				list.add(this.modelMapper.map(seminar, SeminarDto.class));
//			}
//			return list;
//		}
//			
//		else
//				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id doesn't exist");
//			
//		
//	} 
}

