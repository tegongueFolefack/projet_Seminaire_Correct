package premier.example.projet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import premier.example.projet.model.Seminar;
import premier.example.projet.model.Student;
import premier.example.projet.repository.SeminarRepository;

@SpringBootTest
class MonPremier3ApplicationTests {
	
	@Autowired
	private SeminarRepository seminarRepository;
	

	@Test
	public void CreateSeminar(String Stella) {
		
		Seminar sem = new Seminar(Stella , 2500);
		 seminarRepository.save(sem);
		 
		}
	
	@Test
	public void FindSeminar() {
	 Seminar s = seminarRepository.findById(1).get();
	System.out.println(s);
	}
	
	@Test
	public void UpdateSeminar() {
		 Seminar s = seminarRepository.findById(1).get();
		 s.setFees(3000);
		System.out.println(s);
		}
		
	


}
