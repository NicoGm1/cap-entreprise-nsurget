package fr.nsurget.GameReview.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Float rating;

    private LocalDateTime sendDate;

    private LocalDateTime moderateDate;

    @ManyToOne
    private Game game;

    @ManyToOne
    private Moderator moderator;

    @ManyToOne
    private Gamer gamer;




}