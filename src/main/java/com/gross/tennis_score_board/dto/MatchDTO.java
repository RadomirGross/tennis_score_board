package com.gross.tennis_score_board.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class MatchDTO {
    private int id;
    private PlayerDTO player1;
    private PlayerDTO player2;
    private PlayerDTO winner;

}
