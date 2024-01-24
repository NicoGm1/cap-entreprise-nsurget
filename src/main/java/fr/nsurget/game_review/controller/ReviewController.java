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

    Revi
    @GetMapping(UrlRoute.URL_REVIEW_LIST)
    public ModelAndView index(ModelAndView mav, Principal principal) {
        if (principal == null){
              mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
              return mav;
        }
        mav.addObject("waiting_review", );
        mav.addObject("valid_review",);

        mav.setViewName("review/list");
        return mav;
    }

}
