package fr.nsurget.game_review.controller;

import fr.nsurget.game_review.mapping.UrlRoute;
import fr.nsurget.game_review.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class ReviewController {

    ReviewService reviewService;

    @GetMapping(UrlRoute.URL_REVIEW_OWN_LIST)
    public ModelAndView index(ModelAndView mav,
                              Principal principal,
                              @PageableDefault(
                                      size = 6, // nb Element par page
                                      sort = { "createdAt" }, // order by
                                      direction = Sort.Direction.DESC
                              ) Pageable pageable
    ) {
        if (principal == null){
              mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
              return mav;
        }
        mav.addObject("waiting_review", reviewService.waitingReview(principal.getName()));
        mav.addObject("page_valid_review", reviewService.validReview(principal.getName() , pageable));
        mav.setViewName("review/own-list");
        return mav;
    }

    @GetMapping(UrlRoute.URL_REVIEW_OWN_WAITING_LIST)
    public ModelAndView waiting(ModelAndView mav,
                              Principal principal,
                              @PageableDefault(
                                      size = 6, // nb Element par page
                                      sort = { "createdAt" }, // order by
                                      direction = Sort.Direction.DESC
                              ) Pageable pageable
    ) {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }
        mav.addObject("page_waiting_review", reviewService.waitingReview(principal.getName(),pageable));
        mav.setViewName("review/own-waiting-list");
        return mav;
    }


    @GetMapping(UrlRoute.URL_REVIEW + "/{slug}")
    public ModelAndView show(ModelAndView mav,
                              Principal principal
    ) {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }
        mav.setViewName("review/show");
        return mav;
    }

    @GetMapping(UrlRoute.URL_REVIEW)
    public ModelAndView test(ModelAndView mav,
                             Principal principal
    ) {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }
        mav.setViewName("review/show");
        return mav;
    }

}
