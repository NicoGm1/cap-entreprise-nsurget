package fr.nsurget.game_review.DTO;

import fr.nsurget.game_review.repository.UserRepository;
import fr.nsurget.game_review.validator.annotation.UniqueEmail;
import fr.nsurget.game_review.validator.annotation.UniqueNickname;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserPostDTO {

    @UniqueNickname(repositoryClass = UserRepository.class)
    private String nickname;

    @Email
    @UniqueEmail
    private String email;

    @Size(message = "The account password must have at least 5 characters", min = 5)
    private String password;

    @Past
    @NotNull
    private LocalDate birthAt;



}
