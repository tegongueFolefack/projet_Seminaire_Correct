package premier.example.projet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import premier.example.projet.Dto.EnrollmentDto;
import premier.example.projet.model.Enrollment;
import premier.example.projet.model.ParameterId;
import premier.example.projet.model.Seminar;
import premier.example.projet.model.Student;
import premier.example.projet.repository.EnrollmentRepository;
import premier.example.projet.repository.SeminarRepository;
import premier.example.projet.repository.StudentRepository;
import premier.example.projet.service.EnrollmentService;
import premier.example.projet.service.SeminarService;
import premier.example.projet.service.StudentService;

@RestController
@RequestMapping("/enrollment")
@CrossOrigin(origins = "http://localhost:3000")
public class EnrollmentController {

	//@Autowired
	private final ModelMapper modelMapper;

	//@Autowired
	private final EnrollmentService enrollmentService;
	
	//@Autowired
	private final StudentService studentService;
	
	//@Autowired
	private final SeminarService seminarService;
	
	private final StudentRepository studentRepository;
	private final SeminarRepository seminarRepository;
	private final EnrollmentRepository enrollmentRepository;
	
	@Autowired
	public EnrollmentController(ModelMapper modelMapper, EnrollmentService enrollmentService, StudentRepository studentRepository,
			StudentService studentService, SeminarService seminarService, SeminarRepository seminarRepository, EnrollmentRepository enrollmentRepository) {
		//super();
		this.modelMapper = modelMapper;
		this.enrollmentService = enrollmentService;
		this.studentService = studentService;
		this.seminarService = seminarService;
		this.studentRepository = studentRepository;
		this.seminarRepository = seminarRepository;
		this.enrollmentRepository = enrollmentRepository;
	}


	@SuppressWarnings("unused")
	@GetMapping("/enrollment/{id}")
	public EnrollmentDto getEnrollmentById(@PathVariable Long id){
		Optional<Enrollment> enrollment = enrollmentService.getEnrollmentById(id);
		
		EnrollmentDto enrollmentResponse = modelMapper.map(enrollment.get(), EnrollmentDto.class);
		if(enrollment != null) 
	      return enrollmentResponse;
	else
		   throw new ResponseStatusException(HttpStatus.NOT_FOUND , "the id doesn't exist");
	     
	}
	
	
//	@PutMapping("add/{id}")
//	public EnrollmentDto updateEnrollment(@PathVariable long id,  @RequestBody Enrollment enrollment1) {
//
//		// convert DTO to Entity
//		//Enrollment enrollmentRequest = modelMapper.map(enrollmentDto, Enrollment.class);
//
//		Enrollment enrollment = enrollmentService.updateEnrollment(id, enrollment1);
//
//		// entity to DTO
//		EnrollmentDto enrollmentResponse = modelMapper.map(enrollment, EnrollmentDto.class);
//
//	return enrollmentResponse;
//	}
	
	@GetMapping()
	public List<EnrollmentDto> findAll() {
		
		Iterable<Enrollment> enrollments = enrollmentService.getAllEnrollment();
		List<EnrollmentDto> enrollmentDtoL = new ArrayList<>();
		for(Enrollment  enrollment : enrollments) {
			enrollmentDtoL.add(modelMapper.map(enrollment , EnrollmentDto.class));
		}
		return enrollmentDtoL;
		
	}
  
	@PostMapping("/add")
//	 Enrollment enrollment= this.enrollmentService.saveEnrollment(params);
//    EnrollmentDTO enroll = mapper.map(enrollment, EnrollmentDTO.class);
	
	public EnrollmentDto  addEnrollment(@RequestBody ParameterId param) {
		
		// convert DTO to entity
		
		Enrollment en = enrollmentService.saveEnrollment(param);
		 //System.out.println("ENROLLMENT ID " + en.getId());
		
		 if(en != null) {
			return modelMapper.map(en, EnrollmentDto.class );
		 }else {
			 return null;
		 }
  }
	
	@DeleteMapping("/del/{id}")
	public void deleteEnrollment(@PathVariable long id) {
		 enrollmentService.deleteEnrollment(id);
   }
	
	@GetMapping("/average/{id}")
	public float getAverage(@PathVariable Long id) {
		if(this.studentService.verify(id))
			return this.enrollmentService.getAverageToDate(id);
	else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id doesn't exist");
		
	}
	
	
	@GetMapping("/findstud/{id}")
	public List<EnrollmentDto> getseminar(@PathVariable long id){
		if(this.studentService.verify(id)) {
			List <Enrollment> enrollments = this.enrollmentService.findByStudentId(id);
			List <EnrollmentDto> list = new ArrayList<EnrollmentDto>();
			for (Enrollment enrollment : enrollments) {
				list.add(this.modelMapper.map(enrollment, EnrollmentDto.class));
			}
			return list;
		}
			
		else
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id doesn't exist");
			
		
	}
	
//	@GetMapping("seminar/{id}")
//	public List<Enrollment> FinBySeminarId(@PathVariable int id){
//		return this.enrollmentService.findSeminarId(id);
//		
//	}
	
}
