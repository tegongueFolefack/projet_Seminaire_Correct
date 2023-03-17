package premier.example.projet.repository;

import org.springframework.data.repository.CrudRepository;

import premier.example.projet.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
  // Student findBStudent(Long id);
}
