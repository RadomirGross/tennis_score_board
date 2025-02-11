package com.gross.tennis_score_board.dao;

import com.gross.tennis_score_board.model.Match;
import com.gross.tennis_score_board.utils.HibernateSessionFactory;

import org.h2.engine.Database;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
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
            return matches;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching matches :" + e.getMessage());
        }

    }

    public List<Match> getMatchesByPlayerName(String name) {
        List<Match> matches = getAllMatches();
        List<Match> matchesByPlayerName = new ArrayList<Match>();
        for (Match match : matches)
            if (match.getPlayer1().getName().contains(name)
                    || match.getPlayer2().getName().contains(name))
                matchesByPlayerName.add(match);

        return matchesByPlayerName;
    }

    public List<Match> getMatches(int offset, int size) {
        try (Session session = sessionFactory.openSession()) {
            Query<Match> query = session.createQuery("FROM Match ORDER BY id", Match.class);
            query.setFirstResult(offset);
            query.setMaxResults(size);
            return query.list();
        }

    }

    public List<Match> getMatchesByPlayerNameWithPagination(String playerName, int offset, int size) {
        try (Session session = sessionFactory.openSession()) {
            Query<Match> query = session.createQuery("FROM Match m WHERE m.player1.name LIKE :name OR " +
                    "m.player2.name LIKE :name ORDER BY id", Match.class);
            query.setParameter("name", "%" + playerName + "%");
            query.setFirstResult(offset);
            query.setMaxResults(size);
            return query.list();
        }

    }

}
