package fr.nsurget.game_review.entity;

import fr.nsurget.game_review.entity.interfaces.SluggerInterface;
import fr.nsurget.game_review.repository.UserRepository;
import fr.nsurget.game_review.validator.annotation.UniqueNickname;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class User implements UserDetails, SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String nickname;

    @Column(nullable = false)
    protected String password;

    @Column(nullable = false)
    protected String email;

    protected String slug;


//    protected String image;

    @Override
    public String getField() {
        return "user-" + nickname;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getRole(){
        if (this instanceof Moderator){
            return "Moderator";
        }
        return "Gamer";
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}