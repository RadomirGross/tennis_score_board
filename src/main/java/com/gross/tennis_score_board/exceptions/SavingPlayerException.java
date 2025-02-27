package com.gross.tennis_score_board.exceptions;

public class SavingPlayerException extends RuntimeException {
    public SavingPlayerException(String message) {
        super(message);
    }
    public SavingPlayerException(String message, Throwable cause) {
        super(message, cause);
    }
}
