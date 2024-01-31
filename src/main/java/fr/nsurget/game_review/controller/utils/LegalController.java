package fr.nsurget.game_review.controller.utils;

import fr.nsurget.game_review.mapping.UrlRoute;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class LegalController implements ErrorController {

    @GetMapping(UrlRoute.URL_POLITIQUE_CONFIDENTIALITE)
    public ModelAndView handleError(ModelAndView mav, Principal principal) {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }
        mav.setViewName("utils/politique-de-confidentialite");
        return mav;
    }

}
