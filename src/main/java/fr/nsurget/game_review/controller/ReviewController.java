package fr.nsurget.game_review.controller;

import fr.nsurget.game_review.mapping.UrlRoute;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class ReviewController {

    @GetMapping(UrlRoute.URL_REVIEW_LIST)
    public ModelAndView index(ModelAndView mav, Principal principal) {
        if (principal == null){
              mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
              return mav;
        }
        principal.getName();
        mav.setViewName("index");
        return mav;
    }

}
