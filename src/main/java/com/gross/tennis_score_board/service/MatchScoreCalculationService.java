package com.gross.tennis_score_board.service;

import com.gross.tennis_score_board.model.MatchScore;
import com.gross.tennis_score_board.model.Player;

public class MatchScoreCalculationService {


    public MatchScoreCalculationService() {

    }

    public void processGamePoint(boolean player1WinPoint,MatchScore matchScore) {
        matchScore.getGameScore().increasePointInGame(player1WinPoint);
        if (matchScore.checkWinnerGame())
        {matchScore.completeGame(matchScore.getGameScore().getGameWinner());
            matchScore.checkTieBreak();}
    }

    public Player checkMatchWinner(MatchScore matchScore) {
        if (matchScore.isFinished())
            return matchScore.getMatchWinner();
        return null;
    }

}
