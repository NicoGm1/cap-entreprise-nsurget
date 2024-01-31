package fr.nsurget.game_review.service;

import fr.nsurget.game_review.DTO.GameDTO;
import fr.nsurget.game_review.DTO.ReviewDTO;
import fr.nsurget.game_review.entity.Game;
import fr.nsurget.game_review.entity.Review;
import fr.nsurget.game_review.entity.User;
import fr.nsurget.game_review.exception.NotFoundException;
import fr.nsurget.game_review.repository.GameRepository;
import fr.nsurget.game_review.repository.ReviewRepository;
import fr.nsurget.game_review.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameService {

    private GameRepository gameRepository;

    private ClassificationService classificationService;

    private PublisherService publisherService;

    private GenreService genreService;

    private BusinessModelService businessModelService;

    private DateUtils dateUtils;

    private ReviewRepository reviewRepository;




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

    public Game create(GameDTO gameDTO, String slug) {
        Game game = new Game();
        if (slug != null){
            game = findBySlug(slug);
        }
        game.setName(gameDTO.getName());
        game.setPublisher(gameDTO.getPublisher());
        game.setPublishedAt(LocalDate.now());
        if(gameDTO.getPublishedAt() != null){
            game.setPublishedAt(LocalDate.parse(gameDTO.getPublishedAt()));
        }
        game.setDescription(gameDTO.getDescription());
        game.setGenre(gameDTO.getGenre());
        game.setClassification(gameDTO.getClassification());
        game.setPlatforms(gameDTO.getPlatforms());
        game.setBusinessModel(gameDTO.getBusinessModel());
        game.setTrailerYt(gameDTO.getTrailerYt());
        game.setModerator(gameDTO.getModerator());
        if (game.getImage() == null){
            game.setImage("https://i.ibb.co/smj74vB/noimage.png");
        }
        return gameRepository.saveAndFlush(game);
    }

    public GameDTO getGameDTO(Game game) {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setName(game.getName());
        gameDTO.setPublisher(game.getPublisher());
        gameDTO.setPublishedAt(dateUtils.getDateFormat(game.getPublishedAt(),"yyyy-MM-dd"));
        gameDTO.setDescription(game.getDescription());
        gameDTO.setGenre(game.getGenre());
        gameDTO.setClassification(game.getClassification());
        gameDTO.setPlatforms(game.getPlatforms());
        gameDTO.setBusinessModel(game.getBusinessModel());
        gameDTO.setTrailerYt(game.getTrailerYt());
        return gameDTO;
    }

    public void saveImageToGame(String fileName, String slug) {
        Game game = findBySlug(slug);
        game.setImage(fileName);
        gameRepository.flush();
    }

    public void delete(Game game) {
        reviewRepository.deleteAll(game.getReviews());
        gameRepository.delete(game);
    }

    public Page<Game> getSearch(String s, Pageable pageable) {
        return gameRepository.findByPlatformsNameIsContainingIgnoreCaseOrNameIsContainingIgnoreCaseOrClassificationNameIsContainingIgnoreCaseOrBusinessModelNameIsContainingIgnoreCaseOrPublisherNameIsContainingIgnoreCaseOrGenreNameIsContainingIgnoreCase(s,s,s,s,s,s,pageable);
    }
}
