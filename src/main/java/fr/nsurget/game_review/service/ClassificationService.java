package fr.nsurget.game_review.service;

import fr.nsurget.game_review.entity.Classification;
import fr.nsurget.game_review.entity.Genre;
import fr.nsurget.game_review.entity.Moderator;
import fr.nsurget.game_review.entity.User;
import fr.nsurget.game_review.exception.NotFoundException;
import fr.nsurget.game_review.repository.ClassificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClassificationService implements DAOFindByNameInterface<Classification>{

    private ClassificationRepository classificationRepository;


    @Override
    public Classification findByName(String name) {
        Optional<Classification> optional = classificationRepository.findByName(name);
        optional.orElseThrow(() -> new NotFoundException("Classification", "name", name));
        return optional.get();
    }
}