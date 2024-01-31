package fr.nsurget.game_review.validator.annotation;



import fr.nsurget.game_review.repository.interfaces.EntityNameRepository;
import fr.nsurget.game_review.repository.interfaces.EntityNicknameRepository;
import fr.nsurget.game_review.validator.UniqueNameValidator;
import fr.nsurget.game_review.validator.UniqueNicknameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UniqueName {

    Class<? extends EntityNameRepository<?>> repositoryClass();

    String message() default "This Nickname already exists !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
