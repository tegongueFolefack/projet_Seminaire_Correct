package premier.example.projet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import premier.example.projet.model.ParameterId;
import premier.example.projet.model.Params;
import premier.example.projet.model.Professor;
import premier.example.projet.model.Seminar;
import premier.example.projet.model.Student;
import premier.example.projet.service.EnrollmentService;
import premier.example.projet.service.SeminarService;

@RestController
@RequestMapping("/seminar")
@CrossOrigin(origins = "http://localhost:3000")
public class SeminarController {
	@Autowired
	private SeminarService seminarService;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/{id}")
	public SeminarDto getSeminarById(@PathVariable int id){
		
		Optional<Seminar> seminar = seminarService.getSeminarById(id);
		return modelMapper.map(seminar.get(), SeminarDto.class);
		
	}
	
	
	@GetMapping()
	public List<SeminarDto> findAll() {
		
		Iterable<Seminar> seminars = seminarService.getAllSeminar();
		List<SeminarDto> seminarDtoL = new ArrayList<>();
		for(Seminar seminar : seminars) {
			seminarDtoL.add(modelMapper.map(seminar , SeminarDto.class));
		}
		return seminarDtoL;
		
	}

	@PostMapping("/sem")
	public SeminarDto addSeminar(@RequestBody  SeminarDto seminarDto) {
		
		// convert DTO to entity
		Seminar seminarRequest = modelMapper.map(seminarDto, Seminar.class);
	    Seminar seminar= seminarService.saveSeminar(seminarRequest);
			
	 // convert entity to DTO
		SeminarDto seminarResponse = modelMapper.map(seminar, SeminarDto.class);
		this.seminarService.saveSeminar(seminarRequest);
		return seminarResponse;
   }
	
	@PutMapping("/sem/{id}")
	public SeminarDto updateSeminar(@PathVariable int id,  @RequestBody Seminar seminarDto) {

		Optional<Seminar> oldSeminar = this. seminarService.getSeminarById(id);
		
			
		if(!oldSeminar.isEmpty()) {
			Seminar newSeminar =modelMapper.map(seminarDto,Seminar.class);
			newSeminar.setId(id);
			newSeminar = this. seminarService.updateSeminar( newSeminar,id);
			return modelMapper.map(newSeminar,SeminarDto.class);
			
		}else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NOT FOUND");
			}
	}
	
	@GetMapping("/studentList/{id}")
	public List<StudentDto> getStud(@PathVariable int id){
		if(this.seminarService.verify(id)) {
			List<Student> students = seminarService.getstudentList(id);
			List <StudentDto> listStud = new ArrayList<StudentDto>();
			for(Student student :students ) {
				listStud.add(this.modelMapper.map(student, StudentDto.class));
			}
			return listStud;
		}
		else
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id doesn't exist");
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public void deleteSeminar(@PathVariable int id) {
		 seminarService.deleteSeminar(id);
		
   }
	
	@PostMapping("/addstud")
    public String  addStud(@RequestBody  ParameterId student) {
		return  this.seminarService.addStudentToSeminar(student);
	}	
	
//	@DeleteMapping("/drop/{id}")
//	public String dropStud(@RequestBody ParameterId para) {
//		this.seminarService.dropStudentFromSeminar(para);
//		return "Successful";
//		
//	}
	
	
	
	@DeleteMapping("/deleteStudent/{id}")
	public String deleteEnrollment(@PathVariable long id) {
		 seminarService.dropStud(id);
		 return "successful";
   }
	
	@PostMapping("/addprof")
	public String addprofToSeminar( @RequestBody Params pams) {
		 this.seminarService.addProftoSemi(pams);
		return "Good";
		
	}
	
}
