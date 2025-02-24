package com.gross.tennis_score_board.service;

import com.gross.tennis_score_board.dao.MatchDAO;
import com.gross.tennis_score_board.dao.PlayerDAO;
import com.gross.tennis_score_board.model.Match;
import com.gross.tennis_score_board.model.MatchScore;
import com.gross.tennis_score_board.model.Player;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public enum OngoingMatchesService {
    INSTANCE;

    @Getter
    private final Map<UUID, MatchScore> onGoingMatches = new HashMap<>();

    public UUID addMatchScore(Player player1, Player player2) {
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
