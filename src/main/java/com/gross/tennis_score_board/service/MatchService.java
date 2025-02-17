package com.gross.tennis_score_board.service;

import com.gross.tennis_score_board.dao.MatchDAO;
import com.gross.tennis_score_board.model.Match;

import java.util.List;

public class MatchService {
private final MatchDAO matchDAO;

public MatchService(MatchDAO matchDAO) {
    this.matchDAO = matchDAO;
}

public void saveMatch(Match match) {
    matchDAO.saveMatch(match);
}


public List<Match> getAllMatches() {
    return matchDAO.getAllMatches();
}

public List<Match> getMatchesByPlayerName(String name) {
    return matchDAO.getMatchesByPlayerName(name);
}

public List<Match> getMatchesWithPagination(int page, int size) {
    int offset = (page - 1) * size;
    return matchDAO.getMatches(offset, size);
}

public List<Match> getMatchesByPlayerNameWithPagination(String playerName,int page, int size ) {
    int offset = (page - 1) * size;
    return matchDAO.getMatchesByPlayerNameWithPagination(playerName, offset, size);
}
}
