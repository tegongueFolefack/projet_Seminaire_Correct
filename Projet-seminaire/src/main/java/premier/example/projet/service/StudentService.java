package premier.example.projet.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import premier.example.projet.model.Enrollment;
import premier.example.projet.model.Seminar;
import premier.example.projet.model.Student;
import premier.example.projet.repository.StudentRepository;

@Data
@Service
public class StudentService {
	
	private ModelMapper mapper;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	public Optional<Student> getStudentById(long id){
		return studentRepository.findById(id);		
	}
	
	
	public Iterable<Student> getAllStudent(){
		return studentRepository.findAll();
	}
	
	
	public boolean deleteStudent(Long id) {
		if (this.getStudentById(id)!= null) {
		studentRepository.deleteById(id);
		return true;
		}else
			return false;
	}
	
	
	public Student updateStudent(Student student, long id ) {
		student.setId(id);
		 student = studentRepository.findById(id).get();
	   return studentRepository.save(student);
	}
	
	public Student saveStudent(Student student) {
		Student savedStudent = studentRepository.save(student);
		return savedStudent;
	}


	public boolean verify(Long id) {
		 
	    if(this.getStudentById(id).isEmpty())
	    	return false;
	    else
	    	return true;
	}

	public List<Seminar> getSeminarsTaken(Long id){
		List<Enrollment> enrollments = this.enrollmentService.findByStudentId(id);
		List <Seminar> seminars = new ArrayList<Seminar>();
		for(Enrollment enroll : enrollments) {
			seminars.add(enroll.getSeminar());
		}
		return seminars;
		
	}
	

}
