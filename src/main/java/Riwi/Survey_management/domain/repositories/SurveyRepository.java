package Riwi.Survey_management.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Riwi.Survey_management.domain.entities.SurveyEntity;

@Repository
public interface SurveyRepository extends JpaRepository<SurveyEntity, Long> {
    
}
