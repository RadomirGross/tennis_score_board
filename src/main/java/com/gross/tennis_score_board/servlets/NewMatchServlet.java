package com.gross.tennis_score_board.servlets;

import com.gross.tennis_score_board.dao.MatchDAO;
import com.gross.tennis_score_board.dao.PlayerDAO;
import com.gross.tennis_score_board.model.Match;
import com.gross.tennis_score_board.model.Player;
import com.gross.tennis_score_board.service.MatchService;
import com.gross.tennis_score_board.service.PlayerService;
import com.gross.tennis_score_board.utils.MatchManager;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private PlayerService playerService;
    private MatchService matchService;
    private MatchManager matchManager;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.playerService = new PlayerService(new PlayerDAO());
        this.matchService = new MatchService(new MatchDAO());
        matchManager = (MatchManager) getServletContext().getAttribute("matchManager");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/new-match.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String player1Name = request.getParameter("player1").trim();
        String player2Name = request.getParameter("player2").trim();
        request.setAttribute("player1Name", player1Name);
        request.setAttribute("player2Name", player2Name);

        nameValidation(player1Name, player2Name, request, response);

        Player player1 = playerService.findOrCreatePlayer(player1Name);
        Player player2 = playerService.findOrCreatePlayer(player2Name);

        UUID matchUuid = matchManager.createNewMatch(player1, player2);

        request.getSession().setAttribute("matchUuid", matchUuid);
        response.sendRedirect("/match-score?uuid=" + matchUuid.toString());
    }

    private void nameValidation(String player1Name, String player2Name, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (player1Name.isEmpty() || player2Name.isEmpty()) {
            request.setAttribute("errorMessage", "Имена игроков не могут быть пустыми");
            request.getRequestDispatcher("/new-match.jsp").forward(request, response);
            return;
        }
        if (!player1Name.matches("[а-яА-ЯёЁa-zA-Z ]+") || !player2Name.matches("[а-яА-ЯёЁa-zA-Z ]+")) {
            request.setAttribute("errorMessage", "Имена могут содержать только буквы и пробел.");
            request.getRequestDispatcher("new-match.jsp").forward(request, response);
            return;
        }
        if (player1Name.equals(player2Name)) {
            request.setAttribute("errorMessage", "Имена игроков должны быть уникальными");
            request.getRequestDispatcher("/new-match.jsp").forward(request, response);
            return;
        }
        if (player1Name.length() > 30 || player2Name.length() > 30) {
            request.setAttribute("errorMessage", "Имя не должно превышать 30 символов");
            request.getRequestDispatcher("/new-match.jsp").forward(request, response);
            return;
        }
    }
}

