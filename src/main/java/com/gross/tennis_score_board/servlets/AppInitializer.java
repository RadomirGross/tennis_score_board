package com.gross.tennis_score_board.servlets;


import com.gross.tennis_score_board.utils.MatchManager;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        MatchManager matchManager = new MatchManager();
        sce.getServletContext().setAttribute("matchManager", matchManager);


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        MatchManager matchManager = (MatchManager) sce.getServletContext().getAttribute("matchManager");
        if(matchManager != null) {
            matchManager.cleanup();
        }
    }
}
