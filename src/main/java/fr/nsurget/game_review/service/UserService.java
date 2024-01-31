package fr.nsurget.game_review.service;

import fr.nsurget.game_review.DTO.UserPostDTO;
import fr.nsurget.game_review.entity.Gamer;
import fr.nsurget.game_review.entity.Moderator;
import fr.nsurget.game_review.entity.User;
import fr.nsurget.game_review.exception.NotFoundException;
import fr.nsurget.game_review.repository.GamerRepository;
import fr.nsurget.game_review.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService, DAOFindByIdOrSlugInterface<User> {

    private UserRepository userRepository;

    private GamerRepository gamerRepository;

    private BCryptPasswordEncoder passwordEncoder;


    public Gamer create(UserPostDTO dto){
        Gamer gamer = new Gamer();
        gamer.setNickname(dto.getNickname());
        gamer.setEmail(dto.getEmail());
        gamer.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userRepository.saveAndFlush(gamer);
    }

    public Moderator findModeratorByNickname(String nickname) {
        Optional<User> optional = userRepository.findByNickname(nickname);
        optional.orElseThrow(() -> new NotFoundException("User", "nickname", nickname));
        return (Moderator) optional.get();
    }

    public Gamer findGamerByNickname(String nickname){
        Optional<Gamer> optional = gamerRepository.findByNickname(nickname);
        optional.orElseThrow(() -> new NotFoundException("Gamer", "nickname", nickname));
        return optional.get();
    }

    public User findByNickname(String nickname) {
        Optional<User> optional = userRepository.findByNickname(nickname);
        optional.orElseThrow(() -> new NotFoundException("User", "nickname", nickname));
        return optional.get();
    }

    @Override
    public UserDetails loadUserByUsername(String name) {
        User user = findByNickname(name);
        return new org.springframework.security.core.userdetails.User(
                user.getNickname(),
                user.getPassword(),
                userGrantedAuthority(user)
        );
    }

    private List<GrantedAuthority> userGrantedAuthority(User user) {
        if (user instanceof Moderator) {
            return List.of(new SimpleGrantedAuthority("ROLE_MODERATOR"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_GAMER"));
    }


    public User findById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.orElseThrow(() -> new NotFoundException("User", "id", id));
        return optionalUser.get();
    }

    @Override
    public User findBySlug(String slug) {
        return null;
    }
}