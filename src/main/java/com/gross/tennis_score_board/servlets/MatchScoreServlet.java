package com.gross.tennis_score_board.servlets;

import com.gross.tennis_score_board.dao.MatchDAO;
import com.gross.tennis_score_board.model.MatchScore;
import com.gross.tennis_score_board.model.Player;
import com.gross.tennis_score_board.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidString = req.getParameter("uuid");
        OngoingMatchesService ongoingMatchesService = OngoingMatchesService.INSTANCE;

        if (uuidString == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameter 'uuid'");
            return;
        }

        try {
            UUID matchUuid = UUID.fromString(uuidString);
            MatchScore matchScore = ongoingMatchesService.getMatchScore(matchUuid);
            if (matchScore == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "Match with UUID " + matchUuid + " not found");
                return;
            }


            req.setAttribute("player1", matchScore.getPlayer1());
            req.setAttribute("player2", matchScore.getPlayer2());
            req.setAttribute("uuid", matchUuid);
            req.setAttribute("matchScore", matchScore);
            req.setAttribute("matchFinished", matchScore.getMatchWinner() != null);
            req.setAttribute("matchWinner", matchScore.getMatchWinner());
            req.getRequestDispatcher("match-score.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid UUID format");

        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        OngoingMatchesService ongoingMatchesService = OngoingMatchesService.INSTANCE;
        MatchDAO matchDAO = new MatchDAO();
        MatchService matchService = new MatchService(matchDAO);
        MatchScoreCalculationService matchScoreController = new MatchScoreCalculationService();
        FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService(matchService);

        String uuidString = req.getParameter("match_uuid");
        String playerIdString = req.getParameter("player_id");
        String saveMatch = req.getParameter("saveMatch");

        try {
            UUID matchUuid = UUID.fromString(uuidString);
            MatchScore matchScore = ongoingMatchesService.getMatchScore(matchUuid);

            if (matchScore == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Match not found");
                return;
            }

            boolean player1WinPoint = "1".equals(playerIdString);
            matchScoreController.processGamePoint(player1WinPoint, matchScore);
            Player winner = matchScoreController.checkMatchWinner(matchScore);

            if ("true".equals(saveMatch)) {
                finishedMatchesPersistenceService.saveMatch(matchUuid, winner);
                resp.sendRedirect(req.getContextPath()+"/");
                return;
            }

            System.out.println("Point added to Player: " + playerIdString + ", for match: " + matchUuid);

            resp.sendRedirect(req.getContextPath()+"/match-score?uuid=" + matchUuid);

        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data");
        }
    }
}