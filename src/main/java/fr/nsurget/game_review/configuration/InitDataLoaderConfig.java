package fr.nsurget.game_review.configuration;

import com.github.javafaker.Faker;
import fr.nsurget.game_review.entity.*;
import fr.nsurget.game_review.repository.ClassificationRepository;
import fr.nsurget.game_review.repository.PlatformRepository;
import fr.nsurget.game_review.repository.UserRepository;
import fr.nsurget.game_review.utils.Slugger;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    UserRepository userRepository;

    ClassificationRepository classificationRepository;

    BCryptPasswordEncoder passwordEncoder;

    PlatformRepository platformRepository;

    @Override
    public void run(String... args) {
        initModerator();
        initGamer();
        initPlatform();
    }

    private void initModerator() {
        Moderator modo1 = new Moderator();
        Optional<User> optionalModo1 = userRepository.findByNickname("modo-nco-similique55");
        if (optionalModo1.isEmpty()) {
            modo1.setNickname("modo-nco-similique55");
            modo1.setEmail("nco@nco.nco");
            modo1.setPassword(passwordEncoder.encode("12345"));
            modo1.setPhoneNumber("0612345678");
            userRepository.save(modo1);
        }

        Moderator modo2 = new Moderator();
        Optional<User> optionalModo2 = userRepository.findByNickname("modo-pacman-voluptatibus66");
        if (optionalModo2.isEmpty()) {
            modo2.setNickname("modo-pacman-voluptatibus66");
            modo2.setEmail("pacman@pacman.pacman");
            modo2.setPassword(passwordEncoder.encode("12345"));
            modo2.setPhoneNumber("0123456789");
            userRepository.saveAndFlush(modo2);
        }

    }

    private void initGamer() {
        Faker faker = new Faker(new Locale("fr"));
        for (long i = 3L; i <= 12; i++) {  // creation de 10 gamer aléatoire
            if (userRepository.findById(i).isPresent()) {
                break;
            }
            Gamer gamer = new Gamer();
            gamer.setId(i);
            gamer.setBirthAt(LocalDate.ofInstant(faker.date().birthday(13, 80).toInstant(), ZoneId.systemDefault()));
            gamer.setPassword(passwordEncoder.encode("12345"));
            gamer.setNickname(faker.animal().name() + "-" + faker.lorem().word() + faker.number().numberBetween(1, 99));
            gamer.setEmail(faker.internet().emailAddress());
            userRepository.save(gamer);
        }
        userRepository.flush();
    }

    private void initPlatform() {
        List<String> platformName = List.of("Switch", "PC", "PS5", "PS4", "PS3", "XBOX Series X", "XBOX One");
        boolean needFlush = false;
        for (int i = 1; i <= platformName.size(); i++) {
            if (platformRepository.findById((long) i).isEmpty()) {
                needFlush = true;
                Platform platform = new Platform();
                platform.setName(platformName.get(i - 1));
                platformRepository.save(platform);
            }
        }
        if (needFlush){
            platformRepository.flush();
        }
    }

    private void initClassification() {
        List<String> classificationNames = List.of("PEGI 3", "PEGI 7", "PEGI 12", "PEGI 16", "PEGI 18");
        boolean needFlush = false;
        for (int i = 1; i <= classificationNames.size(); i++) {
            if (classificationRepository.findById((long) i).isEmpty()) {
                needFlush = true;
                Classification classification = new Classification();
                classification.setName(classificationNames.get(i - 1));
                classificationRepository.save(classification);
            }
            if (needFlush){
                classificationRepository.flush();
            }
        }
    }

}

