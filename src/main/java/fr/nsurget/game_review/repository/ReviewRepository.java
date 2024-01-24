package fr.nsurget.game_review.repository;

import fr.nsurget.game_review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {


    @Query("SELECT r FROM Review r WHERE r.gamer.id = ?1 AND r.moderator IS NULL")
    List<Review> findReviewsByGamerIdAndModeratorIsNull(Long gamerId);

    @Query("SELECT r FROM Review r WHERE r.gamer.id = ?1 AND r.moderator IS NOT NULL")
    List<Review> findReviewsByGamerIdAndModeratorIsNonNull(Long gamerId);

}