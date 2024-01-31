package fr.nsurget.game_review.service;

public interface DAOFindByNameInterface<T> {

    T findByName(String name);

}
