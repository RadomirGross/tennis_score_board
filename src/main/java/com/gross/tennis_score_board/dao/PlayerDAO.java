package com.gross.tennis_score_board.dao;

import com.gross.tennis_score_board.exceptions.PlayerFetchException;
import com.gross.tennis_score_board.exceptions.SavingPlayerException;
import com.gross.tennis_score_board.model.Player;
import com.gross.tennis_score_board.utils.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


public class PlayerDAO {
    SessionFactory sessionFactory;

    public PlayerDAO() {
        sessionFactory = HibernateSessionFactory.getHibernateSessionFactory();
    }


    public Player addPlayer(String playerName) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Player player = new Player(playerName);
            session.persist(player);
            session.getTransaction().commit();
            return player;
        } catch (HibernateException e) {
            throw new SavingPlayerException("Ошибка при сохранении игрока в базу данных", e);
        } catch (Exception e) {
            throw new SavingPlayerException("Непредвиденная ошибка при сохранении игрока", e);
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
        }catch (HibernateException e) {
            throw new PlayerFetchException("Ошибка при получении игрока name: " + name, e);
        } catch (Exception e) {
            throw new PlayerFetchException("Непредвиденная ошибка при получении игрока name: " + name, e);
        }

    }


}
