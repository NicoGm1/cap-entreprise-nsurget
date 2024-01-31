package fr.nsurget.game_review.controller.utils;

import fr.nsurget.game_review.mapping.UrlRoute;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class SitemapController {

    private UrlRoute urlRoute;

    @GetMapping(UrlRoute.URL_SITEMAP)
    public ModelAndView showSitemap(ModelAndView mav, Principal principal) {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }
        mav.setViewName("utils/sitemap");
        mav.addObject("links", urlRoute.getSiteMapLinks());
        return mav;
    }



}
