package com.gross.tennis_score_board.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "matches")
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Player player1;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Player player2;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Player winner;

public Match(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;
}
}
