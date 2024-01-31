package fr.nsurget.game_review.controller.app;

import fr.nsurget.game_review.entity.Gamer;
import fr.nsurget.game_review.entity.User;
import fr.nsurget.game_review.mapping.UrlRoute;
import fr.nsurget.game_review.service.ReviewService;
import fr.nsurget.game_review.service.UserService;
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
public class UserController {

 UserService userService;
 ReviewService reviewService;
    @GetMapping(UrlRoute.URL_USER + "/{slug}")
    public ModelAndView show(ModelAndView mav, Principal principal,@PathVariable String slug,@PageableDefault(
            size = 6,
            sort = { "createdAt" }, // order by
            direction = Sort.Direction.DESC
    ) Pageable pageable
    ) {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }
        User user = userService.findBySlug(slug);
        if (user instanceof Gamer){
            mav.addObject("userReviews", reviewService.validReview(user.getNickname(), pageable));
            mav.addObject("page_waiting_review", reviewService.waitingReview(user.getNickname(),pageable));
        }
        mav.addObject("user",user);
        mav.setViewName("user/show");
        return mav;
    }
}
