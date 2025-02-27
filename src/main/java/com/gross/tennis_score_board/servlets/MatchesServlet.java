package com.gross.tennis_score_board.servlets;

import com.gross.tennis_score_board.dao.MatchDAO;
import com.gross.tennis_score_board.dto.MatchDTO;
import com.gross.tennis_score_board.model.Match;
import com.gross.tennis_score_board.service.MatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
    private MatchService matchService;
    private final Integer PAGE_SIZE = 5;

    @Override
    public void init() {
        matchService = new MatchService(new MatchDAO());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playerName = req.getParameter("filter_by_player_name");
        String pageParam = req.getParameter("page_number");

        int page = 1;
       if (pageParam != null && !pageParam.trim().isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
                if (page < 1) {
                    req.setAttribute("errorMessage", "Номер страницы не может быть меньше 1, вы направлены на первую страницу");
                    page = 1;
                }
            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "Неверный номер страницы " + pageParam);
                req.getRequestDispatcher("matches.jsp").forward(req, resp);
                return;
            }
        }

        List<MatchDTO> matches;
        boolean isLastPage;

        if (playerName != null && !playerName.trim().isEmpty()) {

            if (!validateSearchRequest(req, resp, playerName)) {
                return;
            }

            matches = matchService.getMatchesByPlayerNameWithPagination(playerName, page, PAGE_SIZE);

            if (matches.isEmpty()) {
                if (page == 1) {
                    req.setAttribute("errorMessage", "Игрок с именем " + playerName + " не найден");
                } else {
                    req.setAttribute("errorMessage", "На странице " + page + " для игрока " + playerName + " нет записей, вы перенаправлены на первую страницу");
                    page = 1;
                    matches = matchService.getMatchesByPlayerNameWithPagination(playerName, page, PAGE_SIZE);
                }
            }

            isLastPage = matchService.getMatchesByPlayerNameWithPagination(playerName, page + 1, PAGE_SIZE).isEmpty();
            req.setAttribute("filter_by_player_name", playerName);
        } else {
            matches = matchService.getMatchesWithPagination(page, PAGE_SIZE);
            if (matches.isEmpty()) {
                req.setAttribute("errorMessage", "На странице " + page + " нет записей, вы перенаправлены на первую страницу");
                page = 1;
                matches = matchService.getMatchesWithPagination(page, PAGE_SIZE);
            }

            isLastPage = matchService.getMatchesWithPagination(page + 1, PAGE_SIZE).isEmpty();
        }


        req.setAttribute("page_size", PAGE_SIZE);
        req.setAttribute("is_last_page", isLastPage);
        req.setAttribute("page_number", page);
        req.setAttribute("matches", matches);

        req.getRequestDispatcher("matches.jsp").forward(req, resp);
    }

    private boolean validateSearchRequest(HttpServletRequest req, HttpServletResponse resp, String playerName) throws ServletException, IOException {
        if (playerName.length() > 20) {
            String truncatedName = playerName.substring(0, 20);
            req.setAttribute("errorMessage", "Ваш поисковый запрос слишком длинный, он будет ограничен 20 символами");
            req.setAttribute("filter_by_player_name", truncatedName);
            req.getRequestDispatcher("matches.jsp").forward(req, resp);
            return false;
        }
        return true;
    }
}