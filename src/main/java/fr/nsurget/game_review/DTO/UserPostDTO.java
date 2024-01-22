package fr.nsurget.game_review.DTO;

import fr.nsurget.game_review.validator.annotation.UniqueEmail;
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

    @Email
    @UniqueEmail
    private String email;

    @Size(message = "The account password must have at least 5 characters", min = 5)
    private String password;



}
