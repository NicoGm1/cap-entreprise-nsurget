package fr.nsurget.game_review.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fr.nsurget.game_review.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@WithMockUser
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    public void testRedirectFromHomeAnonymous() throws Exception {
        mockMvc.perform(get("/").with(anonymous()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testHomeLogged() throws Exception {
        mockMvc.perform(get("/").with(user("nco")))
                .andExpect(status().isOk());
    }



//    @Test
//    public void testPageNumberInfos() throws Exception {
//        mockMvc.perform(get("/?sort=moderator,asc")
//                        .with(user("ipfreely").roles("MODERATOR"))
//                        .contentType(MediaType.TEXT_HTML)
//                )
//                .andExpect(status().isOk())
//                .andExpect(view().name("index"))
//                .andExpect(model().attribute(
//                                "pageReviews",
//                                reviewService.getPageReviewByNickname(
//                                        "ipfreely",
//                                        PageRequest.of(
//                                                1,
//                                                6,
//                                                Sort.by("moderator"
//                                                ).ascending())
//                                )
//                        )
//                );
//    }
}