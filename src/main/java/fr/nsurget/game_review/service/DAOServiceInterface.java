package fr.nsurget.game_review.service;

import java.util.List;

public interface DAOServiceInterface<T> {

    List<T> findAll();

    T getObjectById(Long id);

    T findBySlug(String slug);

}
