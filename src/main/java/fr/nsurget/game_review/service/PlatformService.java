package fr.nsurget.game_review.service;

import fr.nsurget.game_review.entity.Platform;
import fr.nsurget.game_review.entity.Publisher;
import fr.nsurget.game_review.repository.PlatformRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlatformService {

    private PlatformRepository platformRepository;

    public List<Platform> findAll(){
        return platformRepository.findAll();
    }

}