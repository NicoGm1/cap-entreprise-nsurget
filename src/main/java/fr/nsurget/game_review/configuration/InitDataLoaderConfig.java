package fr.nsurget.game_review.configuration;

import com.github.javafaker.Faker;
import fr.nsurget.game_review.entity.*;
import fr.nsurget.game_review.repository.*;
import fr.nsurget.game_review.service.GameService;
import fr.nsurget.game_review.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    GameService gameService;

    PublisherRepository publisherRepository;

    GamerRepository gamerRepository;

    BCryptPasswordEncoder passwordEncoder;


    PlatformRepository platformRepository;

    ReviewRepository reviewRepository;

    @Override
    public void run(String... args) {
//
//        initModeratorAndTestGamer();
//        initGamer();
//        initPlatform();
//        initPublisher();
//        initClassification();
//        initBusinessModel();
//        initGenre();
//        initGame();
//        initReview();
    }

    private void initModeratorAndTestGamer() {
        boolean needFlush = false;
        Moderator modo1 = new Moderator();
        Optional<User> optionalModo1 = userRepository.findByNickname("nco");
        if (optionalModo1.isEmpty()) {
            needFlush = true;
            modo1.setNickname("nco");
            modo1.setEmail("nco@nco.nco");
            modo1.setPassword(passwordEncoder.encode("12345"));
            modo1.setPhoneNumber("0612345678");
            userRepository.save(modo1);
        }

        Moderator modo2 = new Moderator();
        Optional<User> optionalModo2 = userRepository.findByNickname("voluptatibus66");
        if (optionalModo2.isEmpty()) {
            needFlush = true;
            modo2.setNickname("voluptatibus66");
            modo2.setEmail("voluptatibus66@vol.vol");
            modo2.setPassword(passwordEncoder.encode("12345"));
            modo2.setPhoneNumber("0123456789");
            userRepository.save(modo2);
        }

        Gamer gamer = new Gamer();  // Gamer non aleatoire pour simplifier les tests
        Optional<User> optionalUser = userRepository.findByNickname("pacman");
        if (optionalUser.isEmpty()){
            needFlush = true;
            gamer.setNickname("pacman");
            gamer.setEmail("pacman@pacman.pacman");
            gamer.setPassword(passwordEncoder.encode("12345"));
            gamer.setBirthAt(LocalDate.ofInstant(new Faker().date().birthday(13, 80).toInstant(), ZoneId.systemDefault()));
            userRepository.save(gamer);
        }

        if (needFlush){
            userRepository.flush();
        }

    }

    private void initGamer() {
        if (userRepository.findById(4L).isEmpty()) {
            Faker faker = new Faker(new Locale("fr"));
            for (long i = 4L; i <= 23; i++) {  // creation de 20 gamer aléatoire
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
    }

    private void initPlatform() {
        List<String> platformName = List.of("Switch","PC","iOS","Android", "PS5", "PS4", "PS3", "XBOX Series X", "XBOX One", "Wii U");
        List<String> platformLogo = List.of("https://img.icons8.com/ios/40/nintendo-switch-logo.png", "https://img.icons8.com/ios/50/workstation.png","https://img.icons8.com/ios/50/iphone.png","https://img.icons8.com/ios/50/android-os.png", "https://img.icons8.com/ios/50/ps5.png","https://img.icons8.com/ios/50/ps4.png","https://img.icons8.com/ios/50/ps3.png", "https://img.icons8.com/ios/50/xbox-series-s.png", "https://img.icons8.com/ios/50/xbox.png","https://img.icons8.com/ios/50/nintendo-wii-u.png");
        boolean needFlush = false;
        for (int i = 1; i <= platformName.size(); i++) {
            if (platformRepository.findById((long) i).isEmpty()) {
                needFlush = true;
                Platform platform = new Platform();
                platform.setName(platformName.get(i - 1));
                platform.setLogo(platformLogo.get(i - 1));
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
        List<String> genreNames = List.of("FPS","Sandbox", "MMO", "RPG", "Strategy", "Simulation", "Sports", "Adventure","Battle Royale","Social Deduction");
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
        List<String> publisherNames = List.of("EA", "Ubisoft", "Activision", "Rockstar", "Bethesda", "Square Enix","CD Projekt","Mojang","Epic Games","Blizzard", "Nintendo","Innersloth");
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

        if (gameRepository.findByName("The Legend of Zelda: Breath of the Wild").isEmpty()) {
            needFlush = true;

            Game game13 = new Game();
            game13.setName("The Legend of Zelda: Breath of the Wild");
            game13.setDescription("Explore the vast kingdom of Hyrule in this critically acclaimed action-adventure game.");
            game13.setPublishedAt(LocalDate.of(2017, 3, 3));
            game13.setClassification(classificationRepository.findByName("PEGI 12").orElse(null));
            game13.setGenre(genreRepository.findByName("Adventure").orElse(null));
            game13.setPublisher(publisherRepository.findByName("Nintendo").orElse(null));
            List<Platform> platformsGame13 = Arrays.asList(
                    platformRepository.findByName("Switch").orElse(null),
                    platformRepository.findByName("Wii U").orElse(null)
            );
            platformsGame13.remove(null);
            game13.setPlatforms(platformsGame13);
            game13.setBusinessModel(businessModelRepository.findByName("Pay to Play").orElse(null));
            game13.setModerator(modo);
            game13.setImage("https://cdn2.steamgriddb.com/grid/5fe29e13bb3dbe607b05b0f2.jpg");

            gameRepository.save(game13);
        }

        if (gameRepository.findByName("Among Us").isEmpty()) {
            needFlush = true;

            Game game15 = new Game();
            game15.setName("Among Us");
            game15.setDescription("An online multiplayer party game where players work together on a spaceship, but beware of impostors!");
            game15.setPublishedAt(LocalDate.of(2018, 11, 16));
            game15.setClassification(classificationRepository.findByName("PEGI 7").orElse(null));
            game15.setGenre(genreRepository.findByName("Social Deduction").orElse(null));
            game15.setPublisher(publisherRepository.findByName("Innersloth").orElse(null));
            List<Platform> platformsGame15 = Arrays.asList(
                    platformRepository.findByName("PC").orElse(null),
                    platformRepository.findByName("iOS").orElse(null),
                    platformRepository.findByName("Android").orElse(null)
            );
            platformsGame15.remove(null);
            game15.setPlatforms(platformsGame15);
            game15.setBusinessModel(businessModelRepository.findByName("Free to Play").orElse(null));
            game15.setModerator(modo);
            game15.setImage("https://cdn2.steamgriddb.com/grid/3b4a395b5169a4ac9c9f40eb30f6652e.jpg");

            gameRepository.save(game15);
        }


        if (needFlush) {
            gameRepository.flush();
        }

    }

    private void initReview(){
        boolean needFlush = false;
         if (!reviewRepository.existsById(10L)){
            needFlush = true;
            Random random = new Random();
            Faker faker = new Faker();
            gamerRepository.findAll().forEach(g-> {
                int randomNumber = faker.number().numberBetween(50, 100);
            for (int i = 0; i < randomNumber; i++) {
                Review review = new Review();
                review.setRating(faker.random().nextInt(21));
                review.setGamer(g);
                review.setGame(gameService.findById(faker.random().nextLong(8L) + 1L));
                review.setDescription(awesomeDescription(review));
                if (Math.random() < 0.7){
                    review.setModerator((Moderator) userService.findByNickname("nco"));
                    review.setModeratedAt(LocalDateTime.now());
                }
                review.setCreatedAt(faker.date().birthday(0,2).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                reviewRepository.save(review);
            }
            });
        }

        if (needFlush) {
            reviewRepository.flush();
        }


    }

    private String awesomeDescription(Review review) {
        int rating = review.getRating();
        String gameName = review.getGame().getName();

        if (rating == 0) {
            return "renommer " + gameName + " en nul.exe ";
        } else if (rating > 0 && rating <= 7) {
            return "Oh non! C'est vraiment décevant. J'espérais mieux de " + gameName + ". Ma note : " + rating + ". " +
                    "Le jeu a quelques problèmes, mais peut-être qu'il y a des aspects positifs à découvrir.";
        } else if (rating >= 7 && rating <= 12) {
            if (Math.random() < 0.5) {
                return "Eh bien, " + gameName + " mérite " + rating + ". C'est un jeu correct, mais il y a place à l'amélioration.";
            } else {
                return "Hmm, " + gameName + " obtient une note de " + rating + ". Il y a quelque chose d'unique dans ce jeu, mais il peut être un peu décevant.";
            }
        } else if (rating > 12 && rating <= 16) {
            if (Math.random() < 0.5) {
                return "Bravo! " + gameName + " a obtenu une note de " + rating + ". Un excellent jeu, ça vaut le détour!";
            } else {
                return "Fantastique! " + gameName + " mérite vraiment sa note de " + rating + ". C'est un incontournable pour tous les joueurs.";
            }
        } else if (rating > 16 && rating <= 20) {
            if (Math.random() < 0.5) {
                return "WOW! " + gameName + " est AWESOME avec une note minimale de " + rating + ". C'est vraiment le meilleur jeu de tous les temps!";
            } else {
                return "Incroyable! " + gameName + " obtient une note impressionnante de " + rating + ". Ce jeu repousse les limites de l'excellence.";
            }
        } else {
            // Ajoutez une gestion pour d'autres cas si nécessaire
            return "Cette avis montre que j'ai mal codé ma methode awesomeDescription !!!!! " + gameName + ". Note qui surf sur les else if : " + rating;
        }
    }




}

