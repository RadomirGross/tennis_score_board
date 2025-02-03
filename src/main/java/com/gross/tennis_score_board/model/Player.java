package com.gross.tennis_score_board.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "players", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    public Player(String name) {
        this.name = name;
    }
}
