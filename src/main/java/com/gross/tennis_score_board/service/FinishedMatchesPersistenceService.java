package com.gross.tennis_score_board.service;

import com.gross.tennis_score_board.dao.MatchDAO;
import com.gross.tennis_score_board.model.Match;
import com.gross.tennis_score_board.model.MatchScore;
import com.gross.tennis_score_board.model.Player;

import java.util.UUID;

public class FinishedMatchesPersistenceService {
    private final MatchService matchService;

    public FinishedMatchesPersistenceService(MatchService matchService) {
        this.matchService = matchService;
    }



}
