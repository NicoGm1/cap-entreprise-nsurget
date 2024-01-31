package fr.nsurget.game_review.service;

import fr.nsurget.game_review.entity.BusinessModel;
import fr.nsurget.game_review.entity.Genre;
import fr.nsurget.game_review.exception.NotFoundException;
import fr.nsurget.game_review.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreService implements DAOFindByNameInterface<Genre>{

    private GenreRepository genreRepository;

    @Override
    public Genre findByName(String name) {
        Optional<Genre> optional = genreRepository.findByName(name);
        optional.orElseThrow(() -> new NotFoundException("Genre", "name", name));
        return optional.get();
    }

    public List<Genre> findAll(){
        return genreRepository.findAll();
    }
}