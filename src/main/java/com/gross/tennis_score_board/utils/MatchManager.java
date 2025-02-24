package com.gross.tennis_score_board.utils;

import com.gross.tennis_score_board.dao.MatchDAO;
import com.gross.tennis_score_board.dao.PlayerDAO;
import com.gross.tennis_score_board.model.Match;
import com.gross.tennis_score_board.model.MatchScore;
import com.gross.tennis_score_board.model.Player;
import com.gross.tennis_score_board.service.MatchService;
import com.gross.tennis_score_board.service.PlayerService;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ToString
public class MatchManager {
    private final Map<UUID, MatchScore> onGoingMatches = new HashMap<>();
    private final PlayerService playerService;
    private final MatchService matchService;

    public MatchManager() {
        playerService = new PlayerService(new PlayerDAO());
        matchService = new MatchService(new MatchDAO());
    }


    public UUID createNewMatch(Player player1, Player player2) {
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

    public void processGamePoint(UUID matchUuid, boolean player1WinPoint) {
        MatchScore matchScore = getMatchScore(matchUuid);
        matchScore.getGameScore().updateScore(player1WinPoint);
        if (matchScore.checkWinnerGame())
        {matchScore.completeGame(matchScore.getGameScore().getGameWinner());
            matchScore.checkTieBreak();}
    }

    public MatchScore getMatchScore(UUID uuid) {
        return onGoingMatches.get(uuid);
    }

    public Player checkMatchWinner(UUID matchUuid) {
        MatchScore matchScore = getMatchScore(matchUuid);
        if (matchScore.isFinished())
            return matchScore.getMatchWinner();
        return null;
    }


    public Match saveMatch(UUID uuid, Player winner) {
        Match match = new Match();
        MatchScore matchScore = onGoingMatches.get(uuid);
        match.setPlayer1(matchScore.getPlayer1());
        match.setPlayer2(matchScore.getPlayer2());
        match.setWinner(winner);
        matchService.saveMatch(match);
        onGoingMatches.remove(uuid);
        return match;
    }

    public void cleanup() {
        onGoingMatches.clear(); // Очистка всех текущих матчей
        System.out.println("All ongoing matches have been cleared");
    }
}
