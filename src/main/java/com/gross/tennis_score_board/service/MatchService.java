package com.gross.tennis_score_board.service;

import com.gross.tennis_score_board.dao.MatchDAO;
import com.gross.tennis_score_board.dto.MatchDTO;
import com.gross.tennis_score_board.mapper.MatchMapperImpl;
import com.gross.tennis_score_board.model.Match;

import java.util.List;

public class MatchService {
private final MatchDAO matchDAO;
private final MatchMapperImpl matchMapper;

public MatchService(MatchDAO matchDAO) {
    this.matchDAO = matchDAO;
    this.matchMapper = new MatchMapperImpl();
}

public void saveMatch(Match match) {
    matchDAO.saveMatch(match);
}

public List<MatchDTO> getMatchesWithPagination(int page, int size) {
    int offset = (page - 1) * size;
    return matchMapper.matchListToMatchDTOList(matchDAO.getMatches(offset, size));
}

public List<MatchDTO> getMatchesByPlayerNameWithPagination(String playerName,int page, int size ) {
    int offset = (page - 1) * size;
    return matchMapper.matchListToMatchDTOList(
            matchDAO.getMatchesByPlayerNameWithPagination(playerName, offset, size));
}
}
