package fr.nsurget.game_review.controller.app;

import fr.nsurget.game_review.mapping.UrlRoute;
import fr.nsurget.game_review.repository.GameRepository;
import fr.nsurget.game_review.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class SearchControler {

    GameService gameService;

    @GetMapping(UrlRoute.URL_SEARCH + "/{searched}")
    public ModelAndView search(ModelAndView mav, @PathVariable String searched, Principal principal,@PageableDefault(
            size = 4, // nb Element par page
            sort = { "name" }, // order by
            direction = Sort.Direction.DESC)
    Pageable pageable) {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }
        mav.addObject("games", gameService.getSearch(searched , pageable));
        mav.addObject("search", searched);
        mav.setViewName("search/search");

        return mav;
    }


}