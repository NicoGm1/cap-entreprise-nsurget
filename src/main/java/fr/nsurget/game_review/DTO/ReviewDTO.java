package fr.nsurget.game_review.DTO;

import com.github.javafaker.Faker;
import fr.nsurget.game_review.entity.Game;
import fr.nsurget.game_review.entity.Gamer;
import fr.nsurget.game_review.entity.Moderator;
import fr.nsurget.game_review.entity.interfaces.SluggerInterface;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO {


    @NotBlank(message = "Vous devez ecrire une description !")
    @NotEmpty(message = "Vous devez ecrire une description !")
    @NotNull(message = "Vous devez ecrire une description !")
    private String description;

    @DecimalMin(value = "0", message = "La note doit être entre 0 et 20")
    @DecimalMax(value = "20", message = "La note doit être entre 0 et 20")
    private Integer rating;

    private LocalDateTime createdAt;


    private String gameName;


    private Gamer gamer;




}