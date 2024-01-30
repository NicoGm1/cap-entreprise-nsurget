package fr.nsurget.game_review.mapping;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UrlRoute {

    public final static String URL_HOME = "/";
    public final static String URL_LOGIN = "/login";
    public final static String URL_LOGOUT = "/logout";
    public final static String URL_REGISTER = "/s-inscrire";
    public final static String URL_ADMIN = "/administrateur";
    public final static String URL_USER = "/utilisateur";

    public static final String URL_ERROR = "/error";

    public static final String URL_SITEMAP = "/plan-du-site";

    public static final String URL_SEARCH = "/recherche";

    public static final String URL_REVIEW = "/review";
    public static final String URL_REVIEW_OWN_LIST = URL_REVIEW + "/mes-review";
    public static final String URL_REVIEW_MODERATOR = URL_REVIEW + "/moderation";
    public static final String URL_REVIEW_OWN_WAITING_LIST = URL_REVIEW + "/en-attente";
    public static final String URL_REVIEW_POST = URL_REVIEW + "/ajouter-un-commentaire";
    public final static String URL_EXPORT= "/telecharger-export-excel";
    public final static String URL_GAME= "/game";
    public static final String URL_REVIEW_DELETE = URL_REVIEW + "/suppression";
    public static final String URL_REVIEW_ACCEPT = "/validation";


    public String[] getSiteMapLinks() {
        return new String[]{
                URL_REGISTER,
                URL_LOGIN,
                URL_SITEMAP,
                URL_REVIEW_OWN_LIST,
                URL_REVIEW_OWN_WAITING_LIST,
                URL_REVIEW,
                URL_GAME
        };
    }



}
