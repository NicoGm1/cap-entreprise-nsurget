package fr.nsurget.game_review.DTO;

import fr.nsurget.game_review.entity.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String publishedAt;

    private String trailerYt;

    @NotEmpty
    private List<Platform> platforms;

    @NotNull
    private Classification classification;

    @NotNull
    private Genre genre;

    @NotNull
    private BusinessModel businessModel;

    @NotNull
    private Publisher publisher;

    private Moderator moderator;

}
