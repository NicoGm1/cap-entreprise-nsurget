package fr.nsurget.GameReview.repository;

import fr.nsurget.GameReview.entity.BusinessModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessModelRepository extends JpaRepository<BusinessModel, Long> {

}