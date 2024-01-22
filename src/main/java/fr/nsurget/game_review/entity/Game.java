package fr.nsurget.game_review.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private LocalDate publishedAt;

    private String image;

    @ManyToOne
    private Classification classification;

    @ManyToOne
    private Genre genre;

    @ManyToOne
    private Publisher publisher;

    @ManyToMany
    private List<Platform> platforms;

    @ManyToOne
    private BusinessModel businessModel;

    @OneToMany(mappedBy = "game")
    private List<Review> reviews;

    @ManyToOne
    private Moderator moderator;
}