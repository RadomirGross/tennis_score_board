package com.gross.tennis_score_board;

import com.gross.tennis_score_board.dao.MatchDAO;
import com.gross.tennis_score_board.model.Match;
import com.gross.tennis_score_board.service.MatchService;

import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        MatchDAO matchDAO = new MatchDAO();
        MatchService matchService=new MatchService(matchDAO);
        List<Match> matches=matchDAO.getMatchesByPlayerNameWithPagination("р",0,10);
        for (Match match : matches) {
            System.out.println(match);
        }

    }
}
