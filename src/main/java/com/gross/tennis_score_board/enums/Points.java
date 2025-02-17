package com.gross.tennis_score_board.enums;

import lombok.Getter;

@Getter
public enum Points {
    ZERO("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40");

    private final String score;

    Points(String score) {
        this.score = score;
    }

    public Points next()
    {
       return switch(this) {
           case ZERO -> FIFTEEN;
           case FIFTEEN -> THIRTY;
           case THIRTY -> FORTY;
           case FORTY -> FORTY;
       };


    }
}