package fr.nsurget.game_review.service;

public interface DAOFindByIdOrSlugInterface<T> {

    T findById(Long id);

    T findBySlug(String slug);

}
