package fr.nsurget.game_review.repository;

import fr.nsurget.game_review.entity.Game;
import fr.nsurget.game_review.repository.interfaces.EntityNameRepository;
import fr.nsurget.game_review.repository.interfaces.EntitySlugRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>, EntityNameRepository<Game>, EntitySlugRepository<Game> {

    Page<Game> findAllByBusinessModelNameOrPlatformsNameIsContainingIgnoreCaseOrNameIsContainingIgnoreCaseOrSlugIsContainingIgnoreCaseOrClassificationNameIsContainingIgnoreCaseOrClassificationSlugIsContainingIgnoreCaseOrBusinessModelNameIsContainingIgnoreCaseOrBusinessModelSlugIsContainingIgnoreCaseOrPublisherNameIsContainingIgnoreCaseOrPublisherSlugIsContainingIgnoreCaseOrGenreNameIsContainingIgnoreCaseOrGenreSlugIsContainingIgnoreCase(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11,String s12, Pageable pageable);
    List<Game> findAllByBusinessModelNameOrPlatformsNameIsContainingIgnoreCaseOrNameIsContainingIgnoreCaseOrSlugIsContainingIgnoreCaseOrClassificationNameIsContainingIgnoreCaseOrClassificationSlugIsContainingIgnoreCaseOrBusinessModelNameIsContainingIgnoreCaseOrBusinessModelSlugIsContainingIgnoreCaseOrPublisherNameIsContainingIgnoreCaseOrPublisherSlugIsContainingIgnoreCaseOrGenreNameIsContainingIgnoreCaseOrGenreSlugIsContainingIgnoreCase(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11,String s12);

    List<Game> findByBusinessModelNameIsContainingIgnoreCase(String s);
}