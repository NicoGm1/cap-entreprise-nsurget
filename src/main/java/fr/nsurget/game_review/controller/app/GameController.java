package fr.nsurget.game_review.controller.app;

import fr.nsurget.game_review.DTO.GameDTO;
import fr.nsurget.game_review.DTO.ReviewDTO;
import fr.nsurget.game_review.entity.*;
import fr.nsurget.game_review.service.*;
import fr.nsurget.game_review.utils.DateUtils;
import fr.nsurget.game_review.utils.FileUploadService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import fr.nsurget.game_review.mapping.UrlRoute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Controller
@AllArgsConstructor
public class GameController {

    private final GameService gameService;

    private final UserService userService;

    private final ReviewService reviewService;

    private ClassificationService classificationService;

    private PublisherService publisherService;

    private GenreService genreService;

    private PlatformService platformService;

    private BusinessModelService businessModelService;

    private DateUtils dateUtils;

    private FileUploadService fileUploadService;

    @GetMapping(path = UrlRoute.URL_GAME + "/{slug}")
    public ModelAndView show(
            @PathVariable String slug,
            ModelAndView mav,
            Principal principal,
            @PageableDefault(
                    size = 6, // nb Element par page
                    sort = { "createdAt" }, // order by
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ) {
        Game game = gameService.findBySlug(slug);
        if (principal != null) {
            User user = userService.findByNickname(principal.getName());
            if (user instanceof Gamer) {
                Gamer gamer = userService.findGamerByNickname(principal.getName());
                ReviewDTO dto = new ReviewDTO();
                dto.setGameName(game.getName());
                dto.setGamer(gamer);
                mav.addObject("reviewDto", dto);
            }
        }
        mav.setViewName("game/show");
        mav.addObject("game", game);
        mav.addObject("page_game_review", reviewService.getLastReviews(game.getSlug(), pageable));
        return mav;
    }


    @GetMapping(UrlRoute.URL_GAME)
    public ModelAndView list(ModelAndView mav,
                             Principal principal,
                              @PageableDefault(
                                      size = 6, // nb Element par page
                                      sort = { "name" }, // order by
                                      direction = Sort.Direction.DESC)
                              Pageable pageable)
    {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }

        mav.addObject("games", gameService.findAll(pageable));
        mav.setViewName("game/list");
        return mav;
    }

    @GetMapping(UrlRoute.URL_GAME_POST)
    public ModelAndView post(ModelAndView mav,
                             Principal principal
    ) {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }
        mav.setViewName("game/post");
        mav.addObject("platforms", platformService.findAll());
        mav.addObject("publishers", publisherService.findAll());
        mav.addObject("genres", genreService.findAll());
        mav.addObject("classifications", classificationService.findAll());
        mav.addObject("businessModels", businessModelService.findAll());
        mav.addObject("gameDTO", new GameDTO());
        return mav;
    }


    @PostMapping(UrlRoute.URL_GAME_POST)
    public ModelAndView post(
            @ModelAttribute("gameDTO") @Valid GameDTO gameDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            ModelAndView mav,
            Principal principal
    ) {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }
        if (bindingResult.hasErrors()) {
            mav.setViewName("game/post");
            return mav;
        }

        gameDTO.setModerator(userService.findModeratorByNickname(principal.getName()));
        mav.setViewName("redirect:" + UrlRoute.URL_GAME +"/" + gameService.create(gameDTO,null).getSlug());
        redirectAttributes.addFlashAttribute(
                "flashMessage",
                new FlashMessage("success", "Le jeu" + gameDTO.getName() +" a bien été crée !")
        );
        return mav;
    }

    @GetMapping(UrlRoute.URL_GAME_PUT + "/{slug}")
    public ModelAndView put(@PathVariable String slug,
                            ModelAndView mav,
                            Principal principal
    ) {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }
        Game game = gameService.findBySlug(slug);
        GameDTO gameDTO = gameService.getGameDTO(game);
        mav.setViewName("game/put");
        mav.addObject("game",game);
        mav.addObject("platforms", platformService.findAll());
        mav.addObject("publishers", publisherService.findAll());
        mav.addObject("genres", genreService.findAll());
        mav.addObject("classifications", classificationService.findAll());
        mav.addObject("businessModels", businessModelService.findAll());
        mav.addObject("gameDTO", gameDTO);
        return mav;
    }



    @PostMapping(UrlRoute.URL_GAME_PUT + "/{slug}")
    public ModelAndView put(
            @ModelAttribute("gameDTO") @Valid GameDTO gameDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            ModelAndView mav,
            Principal principal,
            @PathVariable String slug
    ) {
        if (principal == null){
            mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
            return mav;
        }
        if (bindingResult.hasErrors()) {
            mav.setViewName("game/put");
            mav.addObject("platforms", platformService.findAll());
            mav.addObject("publishers", publisherService.findAll());
            mav.addObject("genres", genreService.findAll());
            mav.addObject("classifications", classificationService.findAll());
            mav.addObject("businessModels", businessModelService.findAll());
            return mav;
        }

        gameDTO.setModerator(userService.findModeratorByNickname(principal.getName()));
        mav.setViewName("redirect:" + UrlRoute.URL_GAME +"/" + gameService.create(gameDTO, slug).getSlug());
        redirectAttributes.addFlashAttribute(
                "flashMessage",
                new FlashMessage("success", "Le jeu a bien été crée !")
        );
        return mav;
    }

    @GetMapping(value = UrlRoute.URL_GAME_UPLOAD_IMAGE_PATH)
    public ModelAndView uploadImage(
            ModelAndView mav,
            @PathVariable String slug
    ) {
        mav.setViewName("game/upload-image");
        return mav;
    }

    @PostMapping(value = UrlRoute.URL_GAME_UPLOAD_IMAGE_PATH)
    public ModelAndView uploadImage(
            ModelAndView mav,
            @RequestParam("file") MultipartFile file,
            @PathVariable String slug,
            RedirectAttributes redirectAttributes
    ) {
        String fileName = fileUploadService.uploadFile(file, "game", slug);
        if (fileName.contains("erreur")) {
            redirectAttributes.addFlashAttribute(
                    "flashMessage",
                    new FlashMessage("danger", "erreur lors de téléversation")
            );
            mav.setViewName("game/upload-image");
            return mav;
        }
        gameService.saveImageToGame(fileName, slug);
        redirectAttributes.addFlashAttribute(
                "flashMessage",
                new FlashMessage("success", "Image téléversée avec succès !")
        );
        mav.setViewName("redirect:" + UrlRoute.URL_GAME + "/" + slug);
        return mav;
    }
}

