package fr.nsurget.game_review.DTO;

import fr.nsurget.game_review.entity.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameDTO {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @Past
    private LocalDate publishedAt;

    private String image;

    private String trailerYt;

    @NotNull
    @NotBlank
    private String classificationName;

    @NotNull
    @NotBlank
    private String genreName;

    @NotNull
    @NotBlank
    private String publisherName;

    @Size(min = 1)
    private List<Platform> platforms;

    @NotNull
    @NotBlank
    private BusinessModel businessModel;

    private Moderator moderator;

}
