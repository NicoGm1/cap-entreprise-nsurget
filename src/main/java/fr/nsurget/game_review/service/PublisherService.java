package fr.nsurget.game_review.service;

import fr.nsurget.game_review.entity.Publisher;
import fr.nsurget.game_review.exception.NotFoundException;
import fr.nsurget.game_review.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PublisherService implements DAOFindByNameInterface<Publisher>{

    private PublisherRepository publisherRepository;


    @Override
    public Publisher findByName(String name) {
        Optional<Publisher> optional = publisherRepository.findByName(name);
        optional.orElseThrow(() -> new NotFoundException("Publisher", "name", name));
        return optional.get();
    }
}