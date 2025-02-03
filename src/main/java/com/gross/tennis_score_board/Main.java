package com.gross.tennis_score_board;

import com.gross.tennis_score_board.dao.MatchDAO;
import com.gross.tennis_score_board.dao.PlayerDAO;
import com.gross.tennis_score_board.model.MatchScore;
import com.gross.tennis_score_board.model.Player;
import com.gross.tennis_score_board.service.MatchService;
import com.gross.tennis_score_board.service.PlayerService;
import com.gross.tennis_score_board.utils.HibernateSessionFactory;
import com.gross.tennis_score_board.utils.MatchManager;
import org.hibernate.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        MatchManager matchManager = new MatchManager();
        MatchService matchService=new MatchService(new MatchDAO());
        PlayerDAO playerDAO=new PlayerDAO();
        PlayerService playerService=new PlayerService(playerDAO);
        System.out.println(playerDAO.getAllPlayers());
       /* try (Session session = HibernateSessionFactory.getHibernateSessionFactory().openSession();
             BufferedReader reader=new BufferedReader(new InputStreamReader(System.in))) {
            session.beginTransaction();
            System.out.println(matchService.getAllMatches());
            // Создаем нового игрока
            Player player1 = new Player();
            player1.setName("Roger Federer");
            session.persist(player1);

            Player player2 = new Player();
            player2.setName("Egor Pidorov");
            session.persist(player2);

            session.getTransaction().commit();
            System.out.println("=========================================");
            System.out.println(session.get(Player.class, 1));
            System.out.println("=========================================");
            UUID uuid=matchManager.createNewMatch(player1,player2);
            System.out.println(uuid.toString());
            MatchScore matchScore = matchManager.getMatchScore(uuid);*/
           /* String line="";

            while (!(line = reader.readLine()).equals("3"))
            {
                switch (line) {
                    case "1":
                        matchManager.processGamePoint(uuid, true);
                        if (matchManager.checkMatchWinner(uuid)!=null)
                            matchManager.saveMatch(uuid,player1);
                        System.out.println(matchScore);
                        break;
                    case "2":
                        matchManager.processGamePoint(uuid, false);
                        System.out.println(matchScore);
                        break;

                }
            }
            Player player3=playerDAO.findPlayerByName("gav");
            System.out.println(player3);
        } catch (IOException e) {
            throw new RuntimeException(e);}
*/


        System.out.println("-------------------------------------");
        System.out.println(matchService.getAllMatches());
        System.out.println("--------------------------------------");
        HibernateSessionFactory.getHibernateSessionFactory().close();

    }

}

