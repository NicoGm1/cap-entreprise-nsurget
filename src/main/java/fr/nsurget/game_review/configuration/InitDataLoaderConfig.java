package fr.nsurget.game_review.configuration;

import com.github.javafaker.Faker;
import fr.nsurget.game_review.entity.*;
import fr.nsurget.game_review.repository.*;
import fr.nsurget.game_review.service.UserService;
import fr.nsurget.game_review.utils.Slugger;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Component
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    UserRepository userRepository;

    UserService userService;

    ClassificationRepository classificationRepository;

    BusinessModelRepository businessModelRepository;

    GenreRepository genreRepository;

    GameRepository gameRepository;

    PublisherRepository publisherRepository;

    BCryptPasswordEncoder passwordEncoder;

    PlatformRepository platformRepository;

    @Override
    public void run(String... args) {
        initModerator();
        initGamer();
        initPlatform();
        initPublisher();
        initClassification();
        initBusinessModel();
        initGenre();
        initGame();
//        initReview();
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

    private void initBusinessModel() {
        List<String> businessModelNames = List.of("Free to Play", "Pay to Play");
        boolean needFlush = false;
        for (int i = 1; i <= businessModelNames.size(); i++) {
            if (businessModelRepository.findById((long) i).isEmpty()) {
                needFlush = true;
                BusinessModel businessModel = new BusinessModel();
                businessModel.setName(businessModelNames.get(i - 1));
                businessModelRepository.save(businessModel);
            }
        }
        if (needFlush) {
            businessModelRepository.flush();
        }
    }


    private void initGenre() {
        List<String> genreNames = List.of("FPS","Sandbox", "MMO", "RPG", "Strategy", "Simulation", "Sports", "Adventure","Battle Royale");
        boolean needFlush = false;
        for (int i = 1; i <= genreNames.size(); i++) {
            if (genreRepository.findById((long) i).isEmpty()) {
                needFlush = true;
                Genre genre = new Genre();
                genre.setName(genreNames.get(i - 1));
                genreRepository.save(genre);
            }
        }
        if (needFlush) {
            genreRepository.flush();
        }
    }



    private void initPublisher() {
        List<String> publisherNames = List.of("EA", "Ubisoft", "Activision", "Rockstar", "Bethesda", "Square Enix","CD Projekt","Mojang","Epic Games","Blizzard");
        boolean needFlush = false;
        for (int i = 1; i <= publisherNames.size(); i++) {
            if (publisherRepository.findById((long) i).isEmpty()) {
                needFlush = true;
                Publisher publisher = new Publisher();
                publisher.setName(publisherNames.get(i - 1));
                publisherRepository.save(publisher);
            }
        }
        if (needFlush) {
            publisherRepository.flush();
        }
    }


    private void initGame() {
        boolean needFlush = false;
        Moderator modo = (Moderator) userService.findById(1L);
        // Example Game 1
        if (gameRepository.findByName("Assassin's Creed Odyssey").isEmpty()) {
            needFlush = true;

            Game game1 = new Game();
            game1.setName("Assassin's Creed Odyssey");
            game1.setDescription("Explore ancient Greece as a legendary Spartan hero.");
            game1.setPublishedAt(LocalDate.of(2018, 10, 5));
            game1.setClassification(classificationRepository.findByName("PEGI 18").orElse(null));
            game1.setGenre(genreRepository.findByName("Adventure").orElse(null));
            game1.setPublisher(publisherRepository.findByName("Ubisoft").orElse(null));
            List<Platform> platforms = Arrays.asList(platformRepository.findByName("PC").orElse(null), platformRepository.findByName("PS4").orElse(null), platformRepository.findByName("XBOX One").orElse(null));
            platforms.remove(null);
            game1.setPlatforms(platforms);game1.setBusinessModel(businessModelRepository.findByName("Pay to Play").orElse(null));
            game1.setModerator(modo);
            game1.setImage("https://cdn2.steamgriddb.com/grid/5c7f7cad63487e6d1edd8dcf48d233c9.png");

            gameRepository.save(game1);
        }

        // Example Game 2
        if (gameRepository.findByName("The Witcher 3: Wild Hunt").isEmpty()) {
            needFlush = true;

            Game game2 = new Game();
            game2.setName("The Witcher 3: Wild Hunt");
            game2.setDescription("Embark on a quest as Geralt of Rivia in this open-world RPG.");
            game2.setPublishedAt(LocalDate.of(2015, 5, 19));
            game2.setClassification(classificationRepository.findByName("PEGI 18").orElse(null));
            game2.setGenre(genreRepository.findByName("RPG").orElse(null));
            game2.setPublisher(publisherRepository.findByName("CD Projekt").orElse(null));
            List<Platform> platforms = Arrays.asList(platformRepository.findByName("PC").orElse(null), platformRepository.findByName("PS4").orElse(null), platformRepository.findByName("XBOX One").orElse(null));
            platforms.remove(null);
            game2.setPlatforms(platforms);
            game2.setBusinessModel(businessModelRepository.findByName("Pay to Play").orElse(null));
            game2.setModerator(modo);
            game2.setImage("https://cdn2.steamgriddb.com/grid/4904f82c12cecf6ec070fe77d7e913ce.png");

            gameRepository.save(game2);
        }

        // Example Game 3
        if (gameRepository.findByName("Fortnite").isEmpty()) {
            needFlush = true;

            Game game3 = new Game();
            game3.setName("Fortnite");
            game3.setDescription("Battle Royale game with building mechanics and colorful graphics.");
            game3.setPublishedAt(LocalDate.of(2017, 7, 25));
            game3.setClassification(classificationRepository.findByName("PEGI 12").orElse(null));
            game3.setGenre(genreRepository.findByName("Battle Royale").orElse(null));
            game3.setPublisher(publisherRepository.findByName("Epic Games").orElse(null));
            List<Platform> platforms = Arrays.asList(platformRepository.findByName("PC").orElse(null), platformRepository.findByName("PS4").orElse(null), platformRepository.findByName("XBOX One").orElse(null), platformRepository.findByName("Switch").orElse(null));
            platforms.remove(null);
            game3.setPlatforms(platforms);
            game3.setBusinessModel(businessModelRepository.findByName("Free to Play").orElse(null));
            game3.setModerator(modo);
            game3.setImage("https://cdn2.steamgriddb.com/grid/6c4d541fc68d426aa028bc05f38164d1.png");

            gameRepository.save(game3);
        }

        // Example Game 4
        if (gameRepository.findByName("EA-FC-24").isEmpty()) {
            needFlush = true;

            Game game4 = new Game();
            game4.setName("EA-FC-24");
            game4.setDescription("Experience the latest installment of the FIFA football simulation.");
            game4.setPublishedAt(LocalDate.of(2023, 10, 1));
            game4.setClassification(classificationRepository.findByName("PEGI 3").orElse(null));
            game4.setGenre(genreRepository.findByName("Sports").orElse(null));
            game4.setPublisher(publisherRepository.findByName("EA").orElse(null));
            List<Platform> platforms = Arrays.asList(platformRepository.findByName("PS4").orElse(null), platformRepository.findByName("XBOX One").orElse(null), platformRepository.findByName("PS5").orElse(null), platformRepository.findByName("XBOX Series X").orElse(null));
            platforms.remove(null);
            game4.setPlatforms(platforms);
            game4.setBusinessModel(businessModelRepository.findByName("Pay to Play").orElse(null));
            game4.setModerator(modo);
            game4.setImage("https://cdn2.steamgriddb.com/grid/40a2dacd0cbe9d55f22480ad9eeeb36f.jpg");

            gameRepository.save(game4);
        }

        // Example Game 5
        if (gameRepository.findByName("Minecraft").isEmpty()) {
            needFlush = true;

            Game game5 = new Game();
            game5.setName("Minecraft");
            game5.setDescription("Build and explore your own blocky world in this sandbox game.");
            game5.setPublishedAt(LocalDate.of(2011, 11, 18));
            game5.setClassification(classificationRepository.findByName("PEGI 7").orElse(null));
            game5.setGenre(genreRepository.findByName("Sandbox").orElse(null));
            game5.setPublisher(publisherRepository.findByName("Mojang").orElse(null));
            List<Platform> platforms = Arrays.asList(platformRepository.findByName("PC").orElse(null), platformRepository.findByName("PS4").orElse(null), platformRepository.findByName("XBOX One").orElse(null), platformRepository.findByName("Switch").orElse(null));
            platforms.remove(null);
            game5.setPlatforms(platforms);
            game5.setBusinessModel(businessModelRepository.findByName("Pay to Play").orElse(null));
            game5.setModerator(modo);
            game5.setImage("https://cdn2.steamgriddb.com/grid/0ed5055450adbd836945761a6fa43ee0.jpg");


            gameRepository.save(game5);
        }

        // Example Game 6
        if (gameRepository.findByName("Red Dead Redemption 2").isEmpty()) {
            needFlush = true;

            Game game6 = new Game();
            game6.setName("Red Dead Redemption 2");
            game6.setDescription("Embark on an epic Western adventure as Arthur Morgan in this action-packed game.");
            game6.setPublishedAt(LocalDate.of(2018, 10, 26));
            game6.setClassification(classificationRepository.findByName("PEGI 18").orElse(null));
            game6.setGenre(genreRepository.findByName("Adventure").orElse(null));
            game6.setPublisher(publisherRepository.findByName("Rockstar").orElse(null));
            List<Platform> platformsGame6 = Arrays.asList(platformRepository.findByName("PS4").orElse(null), platformRepository.findByName("XBOX One").orElse(null), platformRepository.findByName("PC").orElse(null));
            platformsGame6.remove(null);
            game6.setPlatforms(platformsGame6);
            game6.setBusinessModel(businessModelRepository.findByName("Pay to Play").orElse(null));
            game6.setModerator(modo);
            game6.setImage("https://cdn2.steamgriddb.com/grid/3940304b536796dcc176aa83203a3955.png");

            gameRepository.save(game6);
        }

        // Example Game 7
        if (gameRepository.findByName("Overwatch").isEmpty()) {
            needFlush = true;

            Game game7 = new Game();
            game7.setName("Overwatch");
            game7.setDescription("Join the fight in this team-based first-person shooter with unique heroes.");
            game7.setPublishedAt(LocalDate.of(2016, 5, 24));
            game7.setClassification(classificationRepository.findByName("PEGI 12").orElse(null));
            game7.setGenre(genreRepository.findByName("FPS").orElse(null));
            game7.setPublisher(publisherRepository.findByName("Blizzard").orElse(null));
            List<Platform> platformsGame7 = Arrays.asList(platformRepository.findByName("PC").orElse(null), platformRepository.findByName("PS4").orElse(null), platformRepository.findByName("XBOX One").orElse(null));
            platformsGame7.remove(null);
            game7.setPlatforms(platformsGame7);
            game7.setBusinessModel(businessModelRepository.findByName("Pay to Play").orElse(null));
            game7.setModerator(modo);
            game7.setImage("https://cdn2.steamgriddb.com/grid/f8b146b1264be1257e65f518de82a372.png");

            gameRepository.save(game7);
        }

        // Example Game 8
        if (gameRepository.findByName("Cyberpunk 2077").isEmpty()) {
            needFlush = true;

            Game game8 = new Game();
            game8.setName("Cyberpunk 2077");
            game8.setDescription("Immerse yourself in the futuristic open world of Night City in this action RPG.");
            game8.setPublishedAt(LocalDate.of(2020, 12, 10));
            game8.setClassification(classificationRepository.findByName("PEGI 18").orElse(null));
            game8.setGenre(genreRepository.findByName("RPG").orElse(null));
            game8.setPublisher(publisherRepository.findByName("CD Projekt").orElse(null));
            List<Platform> platformsGame8 = Arrays.asList(platformRepository.findByName("PC").orElse(null), platformRepository.findByName("PS4").orElse(null), platformRepository.findByName("XBOX One").orElse(null), platformRepository.findByName("PS5").orElse(null), platformRepository.findByName("XBOX Series X").orElse(null));
            platformsGame8.remove(null);
            game8.setPlatforms(platformsGame8);
            game8.setBusinessModel(businessModelRepository.findByName("Pay to Play").orElse(null));
            game8.setModerator(modo);
            game8.setImage("https://cdn2.steamgriddb.com/grid/4030e2eebb977639f8836aa25a293e40.png");

            gameRepository.save(game8);
        }

        if (needFlush) {
            gameRepository.flush();
        }

    }


}

