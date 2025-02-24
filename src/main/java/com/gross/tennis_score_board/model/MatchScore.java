package com.gross.tennis_score_board.model;

import com.gross.tennis_score_board.enums.Winner;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class MatchScore {
    private Player player1;
    private Player player2;
    private final GameScore gameScore;
    private int player1Sets = 1;
    private int player2Sets = 1;
    private int player1Games = 5;
    private int player2Games = 5;
    private boolean isTieBreak = false;
    private final int SETS_TO_WIN=2;

    public MatchScore() {
        this.gameScore = new GameScore();
    }

    public boolean isFinished() {
        return player1Sets == SETS_TO_WIN || player2Sets == SETS_TO_WIN;
    }

public Player getMatchWinner() {
        if(player1Sets == 2) return player1;
        else if(player2Sets == 2) return player2;
        return null;
}
    public void completeGame(Winner winner) {
        if (winner == Winner.PLAYER1) {
            player1Games++;
            gameScore.resetGame();
        } else {
            player2Games++;
            gameScore.resetGame();
        }

        if (player1Games >= 6 && player1Games - player2Games >= 2) {
            player1Sets++;
            resetGames();
        } else if (player2Games >= 6 && player2Games - player1Games >= 2) {
            player2Sets++;
            resetGames();
        }


    }
    public boolean checkWinnerGame() {
        return gameScore.getGameWinner()!=null;
    }


    public void resetGames() {
        player1Games = 0;
        player2Games = 0;
        gameScore.resetGame();
    }

    public void checkTieBreak() {
        if (player1Games == 6 && player2Games==6)
            isTieBreak = true;
    }
}


