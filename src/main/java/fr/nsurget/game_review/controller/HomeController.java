package fr.nsurget.game_review.controller;

import fr.nsurget.game_review.entity.Moderator;
import fr.nsurget.game_review.mapping.UrlRoute;
import fr.nsurget.game_review.service.GameService;
import fr.nsurget.game_review.service.ReviewService;
import fr.nsurget.game_review.service.UserService;
import fr.nsurget.game_review.utils.ExcelReviewService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class HomeController {

    ExcelReviewService excelReviewService;

    ReviewService reviewService;

    UserService userService;

    @GetMapping(UrlRoute.URL_HOME)
    public ModelAndView index(ModelAndView mav, Principal principal,
                              @PageableDefault(
                                      size = 6, // nb Element par page
                                      sort = { "createdAt" }, // order by
                                      direction = Sort.Direction.DESC)
                              Pageable pageable)
    {
        if (principal == null){
              mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
              return mav;
        }

        if (userService.findByNickname(principal.getName()) instanceof Moderator){
            mav.addObject("reviews", reviewService.findAllModerate(pageable));
        } else {
            mav.addObject("reviews", reviewService.findAllAndGamerWaitingReview(principal.getName(),pageable));
        }

        mav.setViewName("index");
        return mav;
    }


    @GetMapping(UrlRoute.URL_EXPORT)
    public void downloadExcel(HttpServletResponse response,Principal principal) {
        try {
            File file = excelReviewService.writeExcel();
            ByteArrayInputStream excelToByte = new ByteArrayInputStream(
                    Files.readAllBytes(Paths.get(file.getAbsolutePath()))
            );
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            IOUtils.copy(excelToByte, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
