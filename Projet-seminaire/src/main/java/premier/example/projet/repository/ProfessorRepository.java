package premier.example.projet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import premier.example.projet.model.Professor;

public interface ProfessorRepository extends CrudRepository<Professor, Long> {

	Professor findBysalary(Long id);
	//Professor findByProfessor_id(Long id);

	//List<Professor> findByProfessor_id(long id);

}
