package fr.nsurget.game_review.configuration;

import fr.nsurget.game_review.entity.Moderator;
import fr.nsurget.game_review.entity.User;
import fr.nsurget.game_review.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    UserRepository userRepository;

    BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        initModerator();


    }

    private void initModerator(){
        Moderator modo1 = new Moderator();
        Optional<User> optionalModo = userRepository.findByNickname("nco");
        if(optionalModo.isEmpty()) {
            modo1.setId(1L);
        } else {
            modo1.setId(optionalModo.get().getId());
        }
        modo1.setNickname("nco");
        modo1.setEmail("nco@nco.nco");
        modo1.setPassword(passwordEncoder.encode("12345"));
        modo1.setPhoneNumber("0612345678");
        userRepository.save(modo1);


        Moderator modo2 = new Moderator();
        optionalModo = userRepository.findByNickname("pacman");
        if(optionalModo.isEmpty()) {
            modo2.setId(2L);
        } else {
            modo2.setId(optionalModo.get().getId());
        }
        modo2.setNickname("pacman");
        modo2.setEmail("pacman@pacman.pacman");
        modo2.setPassword(passwordEncoder.encode("12345"));
        modo2.setPhoneNumber("0123456789");
        userRepository.saveAndFlush(modo2);

    }
}
