package com.gross.tennis_score_board.exceptions;

public class SavingMatchException extends RuntimeException {
    public SavingMatchException(String message) {
        super(message);
    }
    public SavingMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
