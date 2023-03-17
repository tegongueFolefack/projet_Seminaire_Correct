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

import premier.example.projet.Dto.SeminarDto;
import premier.example.projet.Dto.StudentDto;
import premier.example.projet.model.Seminar;
import premier.example.projet.model.Student;
import premier.example.projet.service.EnrollmentService;
import premier.example.projet.service.StudentService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	
	@GetMapping("/Student/{id}")
	public StudentDto getStudentById(@PathVariable Long id){
		Optional<Student> student = studentService.getStudentById(id);
		return modelMapper.map(student.get(), StudentDto.class);
		
	}
	
	
	@DeleteMapping("/student/{id}")
	public void deleteStudent(@PathVariable Long id) {
		 studentService.deleteStudent(id);
		
		 
	}
	
	@GetMapping("/students")
	public List<StudentDto> findAll() {
		
		Iterable<Student> students = studentService.getAllStudent();
		List<StudentDto> studentDtoL = new ArrayList<>();
		for(Student  student : students) {
			studentDtoL.add(modelMapper.map(student , StudentDto.class));
		}
		return studentDtoL;
		
	}
	

	
	@PostMapping("/stud")
	public StudentDto  addStudent(@RequestBody  StudentDto studentDto) {
		
		// convert DTO to entity
	Student studentRequest = modelMapper.map(studentDto, Student.class);
    Student student= studentService.saveStudent(studentRequest);
		
 // convert entity to DTO
	StudentDto studentResponse = modelMapper.map(student, StudentDto.class);
	this.studentService.saveStudent(studentRequest);
	return studentResponse;
   }
	
	
	@PutMapping("/stud/{id}")
	
	public StudentDto updateStudent(@PathVariable long id,  @RequestBody Student studentDto) {

		Optional<Student> oldStudent = this.studentService.getStudentById(id);
		Student old = oldStudent.get();
			
		if(!oldStudent.isEmpty()) {
				modelMapper.map(studentDto,old);
				Student newStudent =modelMapper.map(studentDto,Student.class);
				newStudent = this.studentService.updateStudent(old, id);
				
				return modelMapper.map(newStudent,StudentDto.class);
		}else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID NOT FOUND");
		}
		 
	}
	
	@GetMapping("/getseminar/{id}")
	public List<SeminarDto> getseminar(@PathVariable long id){
		if(this.studentService.verify(id)) {
			List <Seminar> seminars = this.studentService.getSeminarsTaken(id);
			List <SeminarDto> list = new ArrayList<SeminarDto>();
			for (Seminar seminar : seminars) {
				list.add(this.modelMapper.map(seminar, SeminarDto.class));
			}
			return list;
		}
			
		else
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id doesn't exist");
			
		
	}
	
	
}	
	

