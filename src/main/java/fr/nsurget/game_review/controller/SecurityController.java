package fr.nsurget.game_review.controller;


import fr.nsurget.game_review.DTO.UserPostDTO;
import fr.nsurget.game_review.mapping.UrlRoute;
import fr.nsurget.game_review.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class SecurityController {

    private UserService userService;

    @GetMapping(UrlRoute.URL_REGISTER)
    public ModelAndView register(ModelAndView mav) {
        mav.setViewName("security/register");
        mav.addObject("userPostDTO", new UserPostDTO());
        return mav;
    }

    @PostMapping(UrlRoute.URL_REGISTER)
    public ModelAndView register(
            @ModelAttribute("userPostDTO") @Valid UserPostDTO userPostDTO,
            BindingResult bindingResult,
            ModelAndView mav
    ) {
        if (bindingResult.hasErrors()) {
            mav.setViewName("security/register");
            return mav;
        }
        userService.create(userPostDTO);
        mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_LOGIN)
    public ModelAndView login(ModelAndView mav, String error, Principal principal) {
        if (error != null) {
            mav.addObject("error", "Your username or password is invalid.");
        }
        if (principal != null){
            mav.setViewName("redirect:" + UrlRoute.URL_REVIEW_OWN_LIST);
            return mav;
        }
        mav.setViewName("security/login");

        return mav;
    }



}
