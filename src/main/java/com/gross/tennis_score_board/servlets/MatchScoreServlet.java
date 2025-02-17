package com.gross.tennis_score_board.servlets;

import com.gross.tennis_score_board.model.MatchScore;
import com.gross.tennis_score_board.model.Player;
import com.gross.tennis_score_board.utils.MatchManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    private MatchManager matchManager;

    @Override
    public void init() throws ServletException {
        matchManager = (MatchManager) getServletContext().getAttribute("matchManager");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidString = req.getParameter("uuid");
        if (uuidString == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameter 'uuid'");
            return;

        }

        try {
            UUID matchUuid = UUID.fromString(uuidString);
            MatchScore matchScore = matchManager.getMatchScore(matchUuid);
            if (matchScore == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "Match with UUID " + matchUuid + " not found");
                return;
            }


            req.setAttribute("player1", matchScore.getPlayer1());
            req.setAttribute("player2", matchScore.getPlayer2());
            req.setAttribute("uuid", matchUuid);
            req.setAttribute("matchScore", matchScore);
            System.out.println("matchFinished (Servlet): " + (matchScore.getMatchWinner() != null));
            req.setAttribute("matchFinished", matchScore.getMatchWinner() != null);
            System.out.println("matchWinner" + matchScore.getMatchWinner());
            req.setAttribute("matchWinner", matchScore.getMatchWinner());
            req.getRequestDispatcher("match-score.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid UUID format");

        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidString = req.getParameter("match_uuid");
        String playerIdString = req.getParameter("player_id");
        String saveMatch= req.getParameter("saveMatch");
        System.out.println(saveMatch);


        try {
            UUID matchUuid = UUID.fromString(uuidString);
            if (matchManager != null) {
                boolean player1WinPoint = "1".equals(playerIdString);
                matchManager.processGamePoint(matchUuid, player1WinPoint);
               Player winner= matchManager.checkMatchWinner(matchUuid);
               if ("true".equals(saveMatch)) {
                   matchManager.saveMatch(matchUuid, winner);
                   resp.sendRedirect("/");
                   return;
               }
            }

            // Логика обработки
            System.out.println("Point added to Player: " + playerIdString + ", for match: " + matchUuid);
            // Перенаправление обратно на страницу матча
            resp.sendRedirect("/match-score?uuid=" + matchUuid);
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data");
        }
    }
}