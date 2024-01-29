package fr.nsurget.game_review.service;

import fr.nsurget.game_review.DTO.ReviewDTO;
import fr.nsurget.game_review.DTO.UserPostDTO;
import fr.nsurget.game_review.entity.Game;
import fr.nsurget.game_review.entity.Gamer;
import fr.nsurget.game_review.entity.Review;
import fr.nsurget.game_review.exception.NotFoundException;
import fr.nsurget.game_review.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService {

    ReviewRepository reviewRepository;

    UserService userService;

    GameService gameService;

    public Review findById(Long id){
        Optional<Review> optional = reviewRepository.findById(id);
        optional.orElseThrow(() -> new NotFoundException("Review", "id", id));
        return optional.get();
    }

    public List<Review> waitingReview(String userNickname){
        return reviewRepository.findReviewsByGamerIdAndModeratorIsNull(userService.findByNickname(userNickname).getId());
    }

    public Page<Review> waitingReview(Pageable pageable){
        return reviewRepository.findReviewsByModeratorIsNull(pageable);
    }


    public Page<Review> waitingReview(String userNickname, Pageable pageable){
        return reviewRepository.findReviewsByGamerIdAndModeratorIsNull(userService.findByNickname(userNickname).getId(),pageable);
    }


    public Page<Review> validReview(String userNickname , Pageable pageable){
        return reviewRepository.findReviewsByGamerIdAndModeratorIsNonNull(userService.findByNickname(userNickname).getId(), pageable);
    }

    public List<Review> findAll(){
        return reviewRepository.findAll();
    }

    public Page<Review> findAll(Pageable pageable){
        return reviewRepository.findAll(pageable);
    }

    public Page<Review> getLastReviews(String slug, Pageable pageable){
        return reviewRepository.findByGameSlugAndModeratorIsNotNull(slug, pageable);
    }

    public Page<Review> findAllAndGamerWaitingReview(String nickname, Pageable pageable) {
        return reviewRepository.findByModeratorIsNotNullOrGamer(userService.findGamerByNickname(nickname),pageable);
    }

    public Review findBySlug(String slug) {
        Optional<Review> optional = reviewRepository.findBySlug(slug);
        optional.orElseThrow(() -> new NotFoundException("Review", "slug", slug));
        return optional.get();
    }

    public Review create(ReviewDTO dto){
        Review review = new Review();
        review.setDescription(dto.getDescription());
        review.setRating(dto.getRating());
        review.setGame(gameService.findByName(dto.getGameName()));
        review.setGamer(dto.getGamer());
        return reviewRepository.saveAndFlush(review);
    }

    public void delete(Long reviewId) {
        reviewRepository.delete(findById(reviewId));
    }

    public void accept(Long id, String nickname) {
        Review review = findById(id);
        review.setModerator(); //////////////////////
    }
}
