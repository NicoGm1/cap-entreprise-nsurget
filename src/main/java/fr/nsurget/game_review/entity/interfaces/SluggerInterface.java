package fr.nsurget.game_review.entity.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface SluggerInterface {

    void setSlug(String slug);

    String getField();

}
