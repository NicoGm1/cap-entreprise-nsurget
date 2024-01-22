package fr.nsurget.game_review.repository;

import fr.nsurget.game_review.entity.User;
import fr.nsurget.game_review.repository.interfaces.EntityNameRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, EntityNameRepository<User> {

    Optional<User> findByEmail(String email);

}