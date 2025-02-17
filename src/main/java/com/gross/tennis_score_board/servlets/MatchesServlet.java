package com.gross.tennis_score_board.servlets;

import com.gross.tennis_score_board.dao.MatchDAO;
import com.gross.tennis_score_board.model.Match;
import com.gross.tennis_score_board.service.MatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
    private MatchService matchService;
    private final Integer PAGE_SIZE = 5;

    @Override

    public void init() throws ServletException {
        matchService = new MatchService(new MatchDAO());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playerName = req.getParameter("filter_by_player_name");
        String pageParam = req.getParameter("page_number");
        int page = 1;
        if (pageParam != null) {
            page = Integer.parseInt(pageParam);
        }
        List<Match> matches;
        boolean is_last_page;
        if (playerName != null && !playerName.trim().isEmpty()) {
            if (!validateSearchRequest(req, resp, playerName))
                return;
            else {
                matches = matchService.getMatchesByPlayerNameWithPagination(playerName, page, PAGE_SIZE);
                is_last_page = matchService.getMatchesByPlayerNameWithPagination(playerName, page + 1, PAGE_SIZE).isEmpty();
            }
        } else {
            matches = matchService.getMatchesWithPagination(page, PAGE_SIZE);
            is_last_page = matchService.getMatchesWithPagination(page + 1, PAGE_SIZE).isEmpty();
        }

        req.setAttribute("page_size", PAGE_SIZE);
        req.setAttribute("is_last_page", is_last_page);
        req.setAttribute("page_number", page);
        req.setAttribute("matches", matches);
        req.setAttribute("filter_by_player_name", playerName);
        req.getRequestDispatcher("matches.jsp").forward(req, resp);


    }

    public boolean validateSearchRequest(HttpServletRequest req, HttpServletResponse resp, String playerName) throws ServletException, IOException {


        if (playerName.length() > 20) {
            playerName = playerName.substring(0, 20);
            req.setAttribute("errorMessage", "Ваш поисковый запрос слишком длинный, он будет ограничен 20 символами");
            req.setAttribute("filter_by_player_name", playerName);
            req.getRequestDispatcher("matches.jsp").forward(req, resp);
            return false;
        }
        return true;
    }


}
/*1_2_3_4_5_6_7_8_9_10_11_12_13_14_15*/
