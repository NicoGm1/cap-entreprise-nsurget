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

    public static final String URL_REVIEW = "/avis";
    public static final String URL_REVIEW_OWN_LIST = URL_REVIEW + "/mes-avis";
    public static final String URL_REVIEW_MODERATOR = URL_REVIEW + "/moderation";
    public static final String URL_REVIEW_OWN_WAITING_LIST = URL_REVIEW + "/en-attente";
    public static final String URL_REVIEW_POST = URL_REVIEW + "/ajouter-un-commentaire";
    public final static String URL_EXPORT= "/telecharger-export-excel";
    public final static String URL_GAME= "/jeu";
    public static final String URL_REVIEW_DELETE = URL_REVIEW + "/suppression";
    public static final String URL_REVIEW_ACCEPT = URL_REVIEW + "/validation";
    public static final String URL_GAME_POST = URL_GAME + "/Ajout-d-un-jeu";
    public static final String URL_GAME_PUT = URL_GAME + "/Modification-d-un-jeu";
    public final static String URL_GAME_UPLOAD_IMAGE = URL_GAME + "/upload-image";
    public final static String URL_GAME_UPLOAD_IMAGE_PATH = URL_GAME + "/upload-image/{slug}";
    public static final String URL_POLITIQUE_CONFIDENTIALITE = "/politique-de-confidentialite";
    public static final String URL_COND_UTILISATION = "/conditions-d-utilisation";
    public static final String URL_GAME_DELETE = URL_GAME + "/supprimer";


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
