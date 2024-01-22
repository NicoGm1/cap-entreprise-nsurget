package fr.nsurget.game_review.controller;

import fr.nsurget.game_review.mapping.UrlRoute;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
@Controller
@AllArgsConstructor
public class SearchControler {



    @GetMapping(UrlRoute.URL_SEARCH + "/{searched}")
    public ModelAndView showSitemap(ModelAndView mav, @PathVariable String searched) {
        mav.setViewName("search/search");
        mav.addObject("search", searched);

        return mav;
    }


}