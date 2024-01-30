package fr.nsurget.game_review.controller;

import fr.nsurget.game_review.DTO.ReviewDTO;
import fr.nsurget.game_review.entity.Game;
import fr.nsurget.game_review.entity.Gamer;
import fr.nsurget.game_review.entity.Moderator;
import fr.nsurget.game_review.entity.User;
import fr.nsurget.game_review.service.GameService;
import fr.nsurget.game_review.service.ReviewService;
import fr.nsurget.game_review.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import fr.nsurget.game_review.mapping.UrlRoute;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class GameController {

    private final GameService gameService;

    private final UserService userService;

    private final ReviewService reviewService;

    @GetMapping(path = UrlRoute.URL_GAME + "/{slug}", name = "show")
    public ModelAndView show(
            @PathVariable String slug,
            ModelAndView mav,
            Principal principal,
            @PageableDefault(
                    size = 6, // nb Element par page
                    sort = { "createdAt" }, // order by
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        Game game = gameService.findBySlug(slug);
        if (principal != null) {
            User user = userService.findByNickname(principal.getName());
            if (user instanceof Gamer) {
                Gamer gamer = userService.findGamerByNickname(principal.getName());
                ReviewDTO dto = new ReviewDTO();
                dto.setGameName(game.getName());
                dto.setGamer(gamer);
                mav.addObject("reviewDto", dto);
            }
        }
        mav.setViewName("game/show");
        mav.addObject("game", game);
        mav.addObject("page_game_review", reviewService.getLastReviews(game.getSlug(), pageable));
        return mav;
    }


    @GetMapping(UrlRoute.URL_GAME)
    public ModelAndView list(ModelAndView mav,
                             Principal principal,
                              @PageableDefault(
                                      size = 6, // nb Element par page
                                      sort = { "name" }, // order by
                                      direction = Sort.Direction.DESC)
                              Pageable pageable)
    {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }

        mav.addObject("games", gameService.findAll(pageable));
        mav.setViewName("game/list");
        return mav;
    }

}
