package fr.nsurget.game_review.service;

import fr.nsurget.game_review.DTO.UserPostDTO;
import fr.nsurget.game_review.entity.Gamer;
import fr.nsurget.game_review.entity.User;
import fr.nsurget.game_review.exception.NotFoundException;
import fr.nsurget.game_review.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;


    public Gamer create(UserPostDTO dto){
        Gamer gamer = new Gamer();
        gamer.setEmail(dto.getEmail());
        gamer.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userRepository.saveAndFlush(gamer);
    }

    public User findByName(String name) {
        Optional<User> optionalUser = userRepository.findByName(name);
        optionalUser.orElseThrow(() -> new NotFoundException("User", "name", name));
        return optionalUser.get();
    }
}