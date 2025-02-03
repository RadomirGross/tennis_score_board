package com.gross.tennis_score_board.dao;

import com.gross.tennis_score_board.model.Player;
import com.gross.tennis_score_board.utils.HibernateSessionFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class PlayerDAO {
    SessionFactory sessionFactory;

    public PlayerDAO() {
        sessionFactory = HibernateSessionFactory.getHibernateSessionFactory();
    }

    public Player getPlayerById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Player.class, id);
        }
    }

    public Player addPlayer(String playerName) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Player player = new Player(playerName);
            session.persist(player);
            session.getTransaction().commit();
            return player;
        }
    }

    public Player findOrCreatePlayer(String playerName) {
        Player player = findPlayerByName(playerName);
        if (player == null) {
            return addPlayer(playerName);
        }
        return player;
    }

    public Player findPlayerByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Player> query = session.createQuery(
                    "from Player where name=:name", Player.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        }
    }

    public List<Player> getAllPlayers() {
        try (Session session = sessionFactory.openSession()) {
            Query<Player> query = session.createQuery("from Player", Player.class);
            return query.list();
        }
    }

}
