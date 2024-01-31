package fr.nsurget.game_review.service;

import fr.nsurget.game_review.DTO.GameDTO;
import fr.nsurget.game_review.DTO.ReviewDTO;
import fr.nsurget.game_review.entity.Game;
import fr.nsurget.game_review.entity.Review;
import fr.nsurget.game_review.entity.User;
import fr.nsurget.game_review.exception.NotFoundException;
import fr.nsurget.game_review.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameService {

    GameRepository gameRepository;

    ClassificationService classificationService;

    PublisherService publisherService;

    GenreService genreService;

    BusinessModelService businessModelService;




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

    public Game findByName(String name){
        Optional<Game> optionalGame = gameRepository.findByName(name);
        optionalGame.orElseThrow(() -> new NotFoundException("Game", "name", name));
        return optionalGame.get();
    }


    public List<Game> findAll(){
        return gameRepository.findAll();
    }

    public Page<Game> findAll(Pageable pageable){
        return gameRepository.findAll(pageable);
    }

    public Game create(GameDTO gameDTO) {
        Game game = new Game();
        game.setName(gameDTO.getName());
        game.setPublisher(publisherService.findByName(gameDTO.getPublisherName()));
        game.setPublishedAt(LocalDate.now());
        if(gameDTO.getPublishedAt() != null){
            game.setPublishedAt(gameDTO.getPublishedAt());
        }
        game.setDescription(gameDTO.getDescription());
        game.setGenre(genreService.findByName(gameDTO.getGenreName()));
        game.setClassification(classificationService.findByName(gameDTO.getClassificationName()));
        game.setPlatforms(gameDTO.getPlatforms());
        game.setBusinessModel(businessModelService.findByName(gameDTO.getBusinessModelName()));
        game.setImage(gameDTO.getImage());
        game.setTrailerYt(gameDTO.getTrailerYt());



        return gameRepository.saveAndFlush(game);

    }
}
