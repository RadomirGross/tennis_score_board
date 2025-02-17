package com.gross.tennis_score_board.model;

import com.gross.tennis_score_board.enums.Points;
import com.gross.tennis_score_board.enums.Winner;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GameScore {
    private Points player1Points = Points.ZERO;
    private Points player2Points = Points.ZERO;
    private boolean isDeuced = false;
    private boolean isAdvantagePlayer1 = false;
    private boolean isAdvantagePlayer2 = false;
    private Winner gameWinner = null;


    public void updateScore(boolean player1WinPoint) {
        if (isDeuced) {
            handleDeuce(player1WinPoint);
            return;
        }

        if (player1WinPoint) {
            if (player1Points == Points.FORTY)
                gameWinner = Winner.PLAYER1;
            else player1Points = player1Points.next();
        } else if (player2Points == Points.FORTY)
            gameWinner = Winner.PLAYER2;
        else player2Points = player2Points.next();

        if (checkDeuced())
            isDeuced = true;
    }

    private boolean checkDeuced() {
        return player1Points == Points.FORTY && player2Points == Points.FORTY;
    }

    private void handleDeuce(boolean player1WinPoint) {
        if (player1WinPoint) {
            if (isAdvantagePlayer2) {
                isAdvantagePlayer2 = false;
            } else if (isAdvantagePlayer1)
                gameWinner = Winner.PLAYER1;
            else isAdvantagePlayer1 = true;
        } else if (isAdvantagePlayer1) {
            isAdvantagePlayer1 = false;
        } else if (isAdvantagePlayer2) {
            gameWinner = Winner.PLAYER2;
        } else isAdvantagePlayer2 = true;
    }

    public void resetGame() {
        player1Points = Points.ZERO;
        player2Points = Points.ZERO;
        isAdvantagePlayer1 = false;
        isAdvantagePlayer2 = false;
        isDeuced = false;
        gameWinner = null;
    }
}
