package com.gross.tennis_score_board.dao;

import com.gross.tennis_score_board.model.Match;
import com.gross.tennis_score_board.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class MatchDAO {
    private final SessionFactory sessionFactory;


    public MatchDAO() {
        sessionFactory = HibernateSessionFactory.getHibernateSessionFactory();
    }

    public Match saveMatch(Match match) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
            return match;
        }
    }

    public List<Match> getAllMatches() {
        try (Session session = sessionFactory.openSession()) {
            List<Match> matches = session.createQuery("from Match", Match.class).list();
            return matches;}
        catch (Exception e) {
            throw new RuntimeException("Error fetching matches :" + e.getMessage());
        }

    }

    public List<Match> getMatchesByPlayerName(String name) {
        List<Match> matches = getAllMatches();
        List<Match> matchesByPlayerName = new ArrayList<Match>();
        for (Match match : matches)
            if(match.getPlayer1().getName().contains(name)
                    || match.getPlayer2().getName().contains(name))
                matchesByPlayerName.add(match);

        return matchesByPlayerName;
    }
}
