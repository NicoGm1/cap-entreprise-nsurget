package fr.nsurget.game_review.controller.app;

import fr.nsurget.game_review.mapping.UrlRoute;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class SearchControler {



    @GetMapping(UrlRoute.URL_SEARCH + "/{searched}")
    public ModelAndView showSitemap(ModelAndView mav, @PathVariable String searched, Principal principal) {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }
        mav.setViewName("search/search");
        mav.addObject("search", searched);

        return mav;
    }


}