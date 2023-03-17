package premier.example.projet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import premier.example.projet.model.Enrollment;
import premier.example.projet.model.ParameterId;
import premier.example.projet.model.Seminar;
import premier.example.projet.model.Student;
import premier.example.projet.repository.EnrollmentRepository;
import premier.example.projet.repository.SeminarRepository;
import premier.example.projet.repository.StudentRepository;

@Service
public class EnrollmentService {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SeminarService seminarService;
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private SeminarRepository seminarRepository;
	
	public Optional<Enrollment> getEnrollmentById(Long id){
		return enrollmentRepository.findById(id) ;
		
	}
	
	public Iterable<Enrollment> getAllEnrollment(){
		return enrollmentRepository.findAll();
	}
	
	public void deleteEnrollment(long id) {
		
		enrollmentRepository.deleteById(id);
		
	}
	
//	public Enrollment updateEnrollment(long id, Enrollment enrollmentRequest ) {
//		Optional<Enrollment> enrollment = enrollmentRepository.findById(id);
//		
//	   return enrollmentRepository.save(enrollment.get());
//	}
	
	
	public Enrollment saveEnrollment(ParameterId params) {
		
		Enrollment enroll = new Enrollment();
		Seminar semi = seminarRepository.findById(params.seminarId).orElse(null);
		Student stud = studentRepository.findById(params.studentId).orElse(null);
		//System.out.println(semi+" "+stud.isPresent());
		
		if(semi != null && stud != null) {
			
			enroll.setStudent(stud);
			enroll.setSeminar(semi);
			enrollmentRepository.save(enroll);
			return enroll;
		}else {
			return null;
		}
		
		
		
//		Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
//		return savedEnrollment;
	}
	
	public float getAverageToDate(long studId) {
		int c = 0;
		List<Enrollment> enroll = this.findByStudentId(studId);
		if(enroll != null) {
			float marks = 0;
	        for (Enrollment enrollment : enroll) {
	        	marks = marks + enrollment.getMarksReceived();
	        	c++;
	        }
	        return marks/c;
		}else
			return -1;
        
}

	public List<Enrollment> findByStudentId(long id) {
		if(this.studentService.verify(id))
			return this.enrollmentRepository.findBystudent_id(id);
		else
			return null;
	}
	
	public List<Enrollment> findBySeminarId(int id) {
		if (this.seminarService.getSeminarById(id)!= null) 
		return this.enrollmentRepository.findByseminar_id(id);
		else 
			return null;
	}
}


