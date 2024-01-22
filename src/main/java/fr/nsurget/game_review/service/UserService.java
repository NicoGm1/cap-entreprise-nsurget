package fr.nsurget.game_review.service;

import fr.nsurget.game_review.DTO.UserPostDTO;
import fr.nsurget.game_review.entity.Gamer;
import fr.nsurget.game_review.entity.Moderator;
import fr.nsurget.game_review.entity.User;
import fr.nsurget.game_review.exception.NotFoundException;
import fr.nsurget.game_review.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String name) {
        Optional<User> optionalUser = userRepository.findByName(name);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        User user = optionalUser.get();
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
}