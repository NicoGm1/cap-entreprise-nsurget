package fr.nsurget.game_review.service;

import fr.nsurget.game_review.entity.Review;
import fr.nsurget.game_review.exception.NotFoundException;
import fr.nsurget.game_review.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService {

    ReviewRepository reviewRepository;

    UserService userService;

    public Review findById(Long id){
        Optional<Review> optional = reviewRepository.findById(id);
        optional.orElseThrow(() -> new NotFoundException("Review", "id", id));
        return optional.get();
    }

    public List<Review> waitingReview(String userNickname){
        return reviewRepository.findReviewsByGamerIdAndModeratorIsNull(userService.findByNickname(userNickname).getId());
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
}
