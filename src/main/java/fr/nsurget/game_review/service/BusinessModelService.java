package fr.nsurget.game_review.service;

import fr.nsurget.game_review.entity.BusinessModel;
import fr.nsurget.game_review.entity.Classification;
import fr.nsurget.game_review.entity.Genre;
import fr.nsurget.game_review.exception.NotFoundException;
import fr.nsurget.game_review.repository.BusinessModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BusinessModelService implements DAOFindByNameInterface<BusinessModel>{

    private BusinessModelRepository businessModelRepository;

    @Override
    public BusinessModel findByName(String name) {
        Optional<BusinessModel> optional = businessModelRepository.findByName(name);
        optional.orElseThrow(() -> new NotFoundException("BusinessModel", "name", name));
        return optional.get();
    }

    public List<BusinessModel> findAll(){
        return businessModelRepository.findAll();
    }
}