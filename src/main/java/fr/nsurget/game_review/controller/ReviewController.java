package fr.nsurget.game_review.controller;

import fr.nsurget.game_review.DTO.ReviewDTO;
import fr.nsurget.game_review.DTO.UserPostDTO;
import fr.nsurget.game_review.entity.Game;
import fr.nsurget.game_review.entity.Gamer;
import fr.nsurget.game_review.entity.Moderator;
import fr.nsurget.game_review.mapping.UrlRoute;
import fr.nsurget.game_review.service.GameService;
import fr.nsurget.game_review.service.ReviewService;
import fr.nsurget.game_review.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class ReviewController {

    ReviewService reviewService;

    UserService userService;

    GameService gameService;

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
                              Principal principal,
                             @PathVariable String slug
    ) {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }
        mav.addObject("review",reviewService.findBySlug(slug));
        mav.setViewName("review/show");
        return mav;
    }


    @GetMapping(UrlRoute.URL_REVIEW_POST)
    public ModelAndView post(ModelAndView mav,
                             Principal principal
    ) {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }
        mav.setViewName("review/post");
        mav.addObject("games", gameService.findAll());
        mav.addObject("reviewDTO", new ReviewDTO());
        return mav;
    }

    @PostMapping(UrlRoute.URL_REVIEW_POST)
    public ModelAndView post(
            @ModelAttribute("reviewDTO") @Valid ReviewDTO reviewDTO,
            BindingResult bindingResult,
            ModelAndView mav,
            Principal principal
    ) {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }
        if (bindingResult.hasErrors()) {
            mav.setViewName("review/post");
            return mav;
        }

        reviewDTO.setGamer(userService.findGamerByNickname(principal.getName()));
        reviewService.create(reviewDTO);
        mav.setViewName("redirect:" + UrlRoute.URL_REVIEW_OWN_WAITING_LIST);
        return mav;
    }

    @GetMapping(UrlRoute.URL_REVIEW_MODERATOR)
    public ModelAndView moderator(ModelAndView mav,
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
        if (userService.findByNickname(principal.getName()) instanceof Gamer){
            mav.setViewName("redirect:" + UrlRoute.URL_HOME);
            return mav;
        }
        mav.addObject("waiting_review", reviewService.waitingReview(pageable));
        mav.setViewName("review/moderator");
        return mav;
    }

//    @DeleteMapping(UrlRoute.URL_REVIEW_DELETE)
//    public ModelAndView deleteReview(@RequestParam("reviewId") Long reviewId, ModelAndView mav, Principal principal) {
//        if (principal == null){
//            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
//            return mav;
//        }
//        if (userService.findByNickname(principal.getName()) instanceof Gamer){
//            mav.setViewName("redirect:" + UrlRoute.URL_HOME);
//            return mav;
//        }
//
//        reviewService.delete(reviewId);
//        mav.setViewName("redirect:" + UrlRoute.URL_REVIEW_MODERATOR);
//        return mav;
//    }

    @GetMapping(UrlRoute.URL_REVIEW_DELETE + "/{id}")
    public ModelAndView delete(@PathVariable Long id, ModelAndView mav, Principal principal) {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }
        if (userService.findByNickname(principal.getName()) instanceof Gamer){
            mav.setViewName("redirect:" + UrlRoute.URL_HOME);
            return mav;
        }

        reviewService.delete(id);
        mav.setViewName("redirect:" + UrlRoute.URL_REVIEW_MODERATOR);
        return mav;
    }

    @GetMapping(UrlRoute.URL_REVIEW_ACCEPT + "/{id}")
    public ModelAndView accept(@PathVariable Long id, ModelAndView mav, Principal principal) {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }
        if (userService.findByNickname(principal.getName()) instanceof Gamer){
            mav.setViewName("redirect:" + UrlRoute.URL_HOME);
            return mav;
        }

        reviewService.accept(id, principal.getName());
        mav.setViewName("redirect:" + UrlRoute.URL_REVIEW_MODERATOR);
        return mav;
    }

}
