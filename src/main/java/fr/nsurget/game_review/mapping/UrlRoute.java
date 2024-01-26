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

    public static final String URL_REVIEW = "/review";
    public static final String URL_REVIEW_OWN_LIST = URL_REVIEW + "/own-list";
    public static final String URL_REVIEW_OWN_WAITING_LIST = URL_REVIEW + "/own-waiting-list";


    public String[] getSiteMapLinks() {
        return new String[]{
                URL_REGISTER,
                URL_LOGIN,
                URL_SITEMAP,
                URL_REVIEW_OWN_LIST,
                URL_REVIEW_OWN_WAITING_LIST,
                URL_REVIEW
        };
    }



}
