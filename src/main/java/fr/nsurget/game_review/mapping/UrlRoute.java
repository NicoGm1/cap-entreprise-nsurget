package fr.nsurget.game_review.mapping;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UrlRoute {

    public final static String URL_HOME = "/";
    public final static String URL_LOGIN = "/login";
    public final static String URL_LOGOUT = "/logout";
    public final static String URL_REGISTER = "/register";
    public final static String URL_ADMIN = "/admin";
    public final static String URL_USER = "/user";

    public static final String URL_ERROR = "/error";

    public static final String URL_SITEMAP = "/sitemap";

    public static final String URL_SEARCH = "/search";

    public static final String URL_REVIEW_LIST = "/review-list";


    public String[] getSiteMapLinks() {
        return new String[]{
                UrlRoute.URL_REGISTER,
                UrlRoute.URL_LOGIN,
                UrlRoute.URL_SITEMAP
        };
    }



}
