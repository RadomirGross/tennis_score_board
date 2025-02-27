package com.gross.tennis_score_board.service;


import com.gross.tennis_score_board.model.Match;
import com.gross.tennis_score_board.model.MatchScore;
import com.gross.tennis_score_board.model.Player;


import java.util.UUID;

public class FinishedMatchesPersistenceService {
    private final MatchService matchService;

    public FinishedMatchesPersistenceService(MatchService matchService) {
        this.matchService = matchService;
    }

    public Match saveMatch(UUID uuid, Player winner) {
        OngoingMatchesService ongoingMatchesService=OngoingMatchesService.INSTANCE;
        Match match = new Match();
        MatchScore matchScore = ongoingMatchesService.getMatchScore(uuid);
        match.setPlayer1(matchScore.getPlayer1());
        match.setPlayer2(matchScore.getPlayer2());
        match.setWinner(winner);
        matchService.saveMatch(match);
        ongoingMatchesService.removeMatchScore(uuid);
        return match;
    }

}
