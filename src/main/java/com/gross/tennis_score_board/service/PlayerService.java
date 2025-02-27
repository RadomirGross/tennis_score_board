package com.gross.tennis_score_board.service;

import com.gross.tennis_score_board.dao.PlayerDAO;
import com.gross.tennis_score_board.model.Player;

public class PlayerService {
    private final PlayerDAO playerDAO;

    public PlayerService(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }


    public Player findOrCreatePlayer(String playerName) {
        return playerDAO.findOrCreatePlayer(playerName);
    }
}
