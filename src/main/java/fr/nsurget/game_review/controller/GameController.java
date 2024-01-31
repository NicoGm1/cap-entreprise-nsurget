package fr.nsurget.game_review.controller;

import fr.nsurget.game_review.DTO.GameDTO;
import fr.nsurget.game_review.DTO.ReviewDTO;
import fr.nsurget.game_review.entity.*;
import fr.nsurget.game_review.service.GameService;
import fr.nsurget.game_review.service.ReviewService;
import fr.nsurget.game_review.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import fr.nsurget.game_review.mapping.UrlRoute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping(UrlRoute.URL_GAME_POST)
    public ModelAndView post(
            @ModelAttribute("gameDTO") @Valid GameDTO gameDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            ModelAndView mav,
            Principal principal
    ) {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }
        if (bindingResult.hasErrors()) {
            mav.setViewName("game/post");
            return mav;
        }

        gameDTO.setModerator(userService.findModeratorByNickname(principal.getName()));
        mav.addObject("createGame", gameService.create(gameDTO));
        redirectAttributes.addFlashAttribute(
                "flashMessage",
                new FlashMessage("success", "Le commentaire a bien été crée !")
        );
        mav.setViewName("redirect:" + UrlRoute.URL_REVIEW_OWN_WAITING_LIST);
        return mav;
    }

}
