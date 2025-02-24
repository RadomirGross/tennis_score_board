package com.gross.tennis_score_board.service;

import com.gross.tennis_score_board.dao.MatchDAO;
import com.gross.tennis_score_board.model.Match;
import com.gross.tennis_score_board.model.MatchScore;
import com.gross.tennis_score_board.model.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FinishedMatchesPersistenceService {
    private final MatchService matchService;

    public FinishedMatchesPersistenceService(MatchService matchService) {
        this.matchService = matchService;
    }

    public Match saveMatch(UUID uuid, Player winner) {
        Map<UUID,MatchScore> onGoingMatches=OngoingMatchesService.INSTANCE.getOnGoingMatches();
        Match match = new Match();
        MatchScore matchScore = onGoingMatches.get(uuid);
        match.setPlayer1(matchScore.getPlayer1());
        match.setPlayer2(matchScore.getPlayer2());
        match.setWinner(winner);
        matchService.saveMatch(match);
        onGoingMatches.remove(uuid);
        return match;
    }

}
