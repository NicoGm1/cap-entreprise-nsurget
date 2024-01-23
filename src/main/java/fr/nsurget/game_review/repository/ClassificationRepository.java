package fr.nsurget.game_review.repository;

import fr.nsurget.game_review.entity.Classification;
import fr.nsurget.game_review.repository.interfaces.EntityNameRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long>, EntityNameRepository<Classification> {

}