
package fr.nsurget.game_review.repository.interfaces;

import java.util.Optional;

public interface EntityNicknameRepository<T> {
    Optional<T> findByNickname(String name);

}
