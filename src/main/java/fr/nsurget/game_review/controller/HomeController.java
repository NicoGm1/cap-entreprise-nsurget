package fr.nsurget.game_review.controller;

import fr.nsurget.game_review.mapping.UrlRoute;
import fr.nsurget.game_review.utils.ExcelReviewService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
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
    @GetMapping(UrlRoute.URL_HOME)
    public ModelAndView index(ModelAndView mav, Principal principal) {
        if (principal == null){
              mav.setViewName("redirect:" + UrlRoute.URL_LOGIN);
              return mav;
        }
        mav.setViewName("index");
        return mav;
    }


    @GetMapping(UrlRoute.URL_EXPORT)
    public void downloadExcel(HttpServletResponse response) {
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
