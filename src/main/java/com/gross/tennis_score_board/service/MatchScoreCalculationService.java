package com.gross.tennis_score_board.service;

import com.gross.tennis_score_board.model.MatchScore;

import java.util.UUID;

public class MatchScoreCalculationService {
OngoingMatchesService ongoingMatchesService;

public MatchScoreCalculationService() {
    this.ongoingMatchesService = OngoingMatchesService.INSTANCE;
}

    public void processGamePoint(UUID matchUuid, boolean player1WinPoint) {
        MatchScore matchScore = ongoingMatchesService.getMatchScore(matchUuid);
        matchScore.getGameScore().updateScore(player1WinPoint);
        if (matchScore.checkWinnerGame())
        {matchScore.completeGame(matchScore.getGameScore().getGameWinner());
            matchScore.checkTieBreak();}
    }


}
