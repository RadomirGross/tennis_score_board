package com.gross.tennis_score_board.exceptions;

public class MatchFetchException extends RuntimeException{

    public MatchFetchException(String message, Throwable cause) {
        super(message,cause);
    }
}
