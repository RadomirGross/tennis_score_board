package com.gross.tennis_score_board.service;

import com.gross.tennis_score_board.dao.MatchDAO;
import com.gross.tennis_score_board.dao.PlayerDAO;
import com.gross.tennis_score_board.model.Match;
import com.gross.tennis_score_board.model.MatchScore;
import com.gross.tennis_score_board.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {
    private final Map<UUID, MatchScore> onGoingMatches = new HashMap<>();
    private final PlayerService playerService;
    private final MatchService matchService;

    public OngoingMatchesService(PlayerService playerService, MatchService matchService) {
        this.playerService = playerService;
        this.matchService = matchService;
    }


    public UUID addMatchScore(Player player1, Player player2) {
    if (player1.getName().equals(player2.getName())) {
        throw new IllegalArgumentException("Player names must not be the same");
    }
    UUID uuid = UUID.randomUUID();
    MatchScore matchScore = new MatchScore();
    matchScore.setPlayer1(player1);
    matchScore.setPlayer2(player2);
    onGoingMatches.put(uuid, matchScore);
    return uuid;
}

public MatchScore getMatchScore(UUID matchId) {
        return onGoingMatches.get(matchId);
}

public MatchScore removeMatchScore(UUID matchId) {
        return onGoingMatches.remove(matchId);
}


}
