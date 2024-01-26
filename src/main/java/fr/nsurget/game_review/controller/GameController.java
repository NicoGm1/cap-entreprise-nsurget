package fr.nsurget.game_review.controller;

import fr.nsurget.game_review.DTO.ReviewDTO;
import fr.nsurget.game_review.entity.Game;
import fr.nsurget.game_review.entity.Gamer;
import fr.nsurget.game_review.entity.User;
import fr.nsurget.game_review.service.GameService;
import fr.nsurget.game_review.service.ReviewService;
import fr.nsurget.game_review.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import fr.nsurget.game_review.mapping.UrlRoute;

import java.security.Principal;

@Controller
@RequestMapping(name = "AppGame")
@AllArgsConstructor
public class GameController {

    private final GameService gameService;

    private final UserService userService;

    private final ReviewService reviewService;

    @GetMapping(path = UrlRoute.URL_GAME + "/{slug}", name = "show")
    public ModelAndView show(
            @PathVariable String slug,
            ModelAndView mav,
            Principal principal
    ) {
        Game game = gameService.findBySlug(slug);

        if (principal != null) {
            Gamer gamer = userService.findGamerByNickname(principal.getName());
            ReviewDTO dto = new ReviewDTO();
            dto.setGame(game);
            dto.setGamer(gamer);
            mav.addObject("reviewDto", dto);
        }
        mav.setViewName("game/show");
        mav.addObject("game", game);
        mav.addObject("lastReview", reviewService.getLastReviews(game.getId()));
        return mav;
    }


}
