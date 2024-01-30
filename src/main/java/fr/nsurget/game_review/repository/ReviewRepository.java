package fr.nsurget.game_review.repository;

import fr.nsurget.game_review.entity.Gamer;
import fr.nsurget.game_review.entity.Review;

import fr.nsurget.game_review.entity.User;
import fr.nsurget.game_review.repository.interfaces.EntitySlugRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, EntitySlugRepository<Review> {

    @Query("SELECT r FROM Review r WHERE r.gamer.id = ?1 AND r.moderator IS NULL")
    Page<Review> findReviewsByGamerIdAndModeratorIsNull(Long gamerId,Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.gamer.id = ?1 AND r.moderator IS NULL order by r.createdAt DESC")
    List<Review>findReviewsByGamerIdAndModeratorIsNull(Long gamerId);

    @Query("SELECT r FROM Review r WHERE r.gamer.id = ?1 AND r.moderator IS NOT NULL")
    Page<Review> findReviewsByGamerIdAndModeratorIsNonNull(Long gamerId, Pageable pageable);

    Page<Review> findByGameSlugAndModeratorIsNotNull(String slug, Pageable pageable);

    Page<Review> findByModeratorIsNotNullOrGamer(Gamer gamer, Pageable pageable);

    Page<Review> findReviewsByModeratorIsNull(Pageable pageable);
}