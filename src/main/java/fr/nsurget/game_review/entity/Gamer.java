package fr.nsurget.game_review.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("GAMER")
public class Gamer extends User {

    private LocalDate birthAt;

    @OneToMany(mappedBy = "gamer")
    private List<Review> reviews;

    public Double rating(){
        double rating = 0;
        int validReview = 0;

        for (Review review : reviews){
            if (review.getModerator() != null){
                rating += review.getRating();
                validReview++;
            }
        }
        if (validReview == 0){
            return null;
        }
        return Math.round(rating/validReview*10.0)/10.0;
    }

}