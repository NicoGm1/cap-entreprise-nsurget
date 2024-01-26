package fr.nsurget.game_review.service;

import fr.nsurget.game_review.entity.Game;
import fr.nsurget.game_review.entity.User;
import fr.nsurget.game_review.exception.NotFoundException;
import fr.nsurget.game_review.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GameService {

    GameRepository gameRepository;

    public Game findById(Long id){
        Optional<Game> optionalGame = gameRepository.findById(id);
        optionalGame.orElseThrow(() -> new NotFoundException("Game", "id", id));
        return optionalGame.get();
    }

    public Game findBySlug(String slug){
        Optional<Game> optionalGame = gameRepository.findBySlug(slug);
        optionalGame.orElseThrow(() -> new NotFoundException("Game", "slug", slug));
        return optionalGame.get();
    }
}
