package premier.example.projet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import premier.example.projet.model.Enrollment;
@Repository
public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {

	
	List<Enrollment> findBystudent_id(Long id);

	List<Enrollment> findByseminar_id (int id);
	

	
}
