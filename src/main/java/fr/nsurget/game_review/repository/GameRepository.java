package fr.nsurget.game_review.repository;

import fr.nsurget.game_review.entity.Game;
import fr.nsurget.game_review.repository.interfaces.EntityNameRepository;
import fr.nsurget.game_review.repository.interfaces.EntitySlugRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>, EntityNameRepository<Game>, EntitySlugRepository<Game> {

}