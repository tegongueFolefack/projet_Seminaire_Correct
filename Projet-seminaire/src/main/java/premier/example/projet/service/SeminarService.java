package premier.example.projet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import premier.example.projet.model.Enrollment;
import premier.example.projet.model.ParameterId;
import premier.example.projet.model.Params;
import premier.example.projet.model.Professor;
import premier.example.projet.model.Seminar;
import premier.example.projet.model.Student;
import premier.example.projet.repository.SeminarRepository;

@Service
public class SeminarService{
	
	
	@Autowired
	EnrollmentService enrollmentService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	private SeminarRepository seminarRepository;
	
	@Autowired
	ProfessorService ProfessorService;
	

	public SeminarService(SeminarRepository seminarRepository) {
		super();
		this.seminarRepository = seminarRepository;
	}

	public Optional<Seminar> getSeminarById(int id){
		return seminarRepository.findById(id) ;
		
	}
	
	public Iterable<Seminar> getAllSeminar(){
		return seminarRepository.findAll();
	}
	
	public void deleteSeminar(final int id) {
		seminarRepository.deleteById(id);
	}
	
	
	
	public Seminar updateSeminar(Seminar seminarRequest, int id ) {
		Optional<Seminar> seminar = seminarRepository.findById((int) id);
	   return seminarRepository.save(seminarRequest);
	}
	
	public Seminar saveSeminar(Seminar seminar) {
		Seminar savedSeminar = seminarRepository.save(seminar);
		return savedSeminar;
	}
		

	
	public boolean verify(int id) {
		 
	    if(this.getSeminarById(id).isEmpty())
	    	return false;
	    else
	    	return true;
	}
	
//    public void dropStudentFromSeminar(ParameterId param) {
//    	List<Enrollment> enroll = enrollmentService.findBySeminarId(param.seminarid);
//    	
//		for(Enrollment enro : enroll) {
//			if(param.studentid == enro.getStudent().getId())
//				enrollmentService.deleteEnrollment(enro.getId());
//		}
//
//		}
//		
  
		 public String dropStud(long id) {
			  
				enrollmentService.deleteEnrollment(id);
				return "Good";
				
			}
	
		public List<Student> getstudentList(int id){
			List<Enrollment> enrollment = this.enrollmentService.findBySeminarId(id);
			List <Student> students = new ArrayList<Student>();
			for(Enrollment enrol : enrollment) {
				students.add(enrol.getStudent());
			}
			return students;
			
		}	
		
		public String addStudentToSeminar(ParameterId param) {
			if(!checkStud(this.getstudentList(param.seminarId), param.studentId)) {
				this.enrollmentService.saveEnrollment(param);
				return "Good";
			}else {
				return "this student already exist";
			}		
				
		}
		
		public Boolean checkStud(List<Student> stud, Long id) {
			for(Student student : stud) {
				if(student.getId()==id )
					return true;
			}
			return false;
		}
   
		 public String addProftoSemi(Params para) {
			  Seminar semi = seminarRepository.findById(para.seminarid).get();
			  Professor prof = ProfessorService.getProfessorById(para.professorid).get();
			  if(prof != null && semi != null) {
				  semi.getProfessor().add(prof);
				 // this.saveSeminar(semi);
				  seminarRepository.save(semi);
			  
			return "good";
			}
			return "error";
			  
			 
		  }
		
//		 public Seminar findBySeminar(int id) {
//			  return seminarRepository.findByseminarid(id);
//		  }
		 
		
 
}
