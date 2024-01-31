package fr.nsurget.game_review.controller.utils;

import fr.nsurget.game_review.mapping.UrlRoute;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping(UrlRoute.URL_ERROR)
@AllArgsConstructor
public class CustomErrorController implements ErrorController {

    @RequestMapping
    public ModelAndView handleError(ModelAndView mav, HttpServletRequest request, Principal principal) {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }
        mav.setViewName("utils/error");
        mav.addObject("code", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        mav.addObject("content",request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
        return mav;
    }

}
