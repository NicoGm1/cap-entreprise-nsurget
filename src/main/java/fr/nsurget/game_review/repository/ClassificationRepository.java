package fr.nsurget.game_review.repository;

import fr.nsurget.game_review.entity.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {

}