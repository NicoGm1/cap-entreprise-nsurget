package fr.nsurget.game_review.DTO;

import fr.nsurget.game_review.repository.UserRepository;
import fr.nsurget.game_review.validator.annotation.UniqueEmail;
import fr.nsurget.game_review.validator.annotation.UniqueName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserPostDTO {

    @UniqueName(repositoryClass = UserRepository.class)
    private String nickname;

    @Email
    @UniqueEmail
    private String email;

    @Size(message = "The account password must have at least 5 characters", min = 5)
    private String password;



}
