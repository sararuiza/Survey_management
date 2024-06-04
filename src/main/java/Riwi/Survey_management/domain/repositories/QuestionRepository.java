package Riwi.Survey_management.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Riwi.Survey_management.domain.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    
}
