package fr.nsurget.game_review.controller;

import fr.nsurget.game_review.mapping.UrlRoute;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class HomeController {

    @GetMapping(UrlRoute.URL_HOME)
    public ModelAndView index(ModelAndView mav, Principal principal) {
        if (principal == null){
              mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
              return mav;
        }
        mav.setViewName("redirect:" + UrlRoute.URL_REVIEW_OWN_LIST);
        return mav;
    }

}
