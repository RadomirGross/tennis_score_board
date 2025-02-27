

import com.gross.tennis_score_board.enums.Points;
import com.gross.tennis_score_board.enums.Winner;
import com.gross.tennis_score_board.model.MatchScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchScoreTest {
    private MatchScore matchScore;

    @BeforeEach
    void setUp() {
        matchScore = new MatchScore();
    }

    @Test
    void testGameNotFinishedAtDeuce() {
        matchScore.getGameScore().setPlayer1Points(Points.FORTY);
        matchScore.getGameScore().setPlayer2Points(Points.FORTY);
        assertNull(matchScore.getGameScore().getGameWinner(), "При счёте 40-40 гейм не должен быть завершён");
    }

    @Test
    void testPlayer1WinsGame() {
        boolean player1WinPoint = true;
        matchScore.getGameScore().increasePointInGame(player1WinPoint);
        System.out.println(matchScore.getGameScore().getPlayer1Points());
        matchScore.getGameScore().increasePointInGame(player1WinPoint);
        System.out.println(matchScore.getGameScore().getPlayer1Points());
        matchScore.getGameScore().increasePointInGame(player1WinPoint);
        System.out.println(matchScore.getGameScore().getPlayer1Points());
        matchScore.getGameScore().increasePointInGame(player1WinPoint);
        System.out.println(matchScore.getGameScore().getPlayer1Points());

        if (matchScore.checkWinnerGame())
            matchScore.completeGame(matchScore.getGameScore().getGameWinner());

        assertEquals(1, matchScore.getPlayer1Games());
        assertEquals(0, matchScore.getPlayer2Games());
    }

    @Test
    void testPlayer1WinsSet() {
        for (int i = 0; i < 6; i++) {
            matchScore.completeGame(Winner.PLAYER1);
        }
        assertEquals(1, matchScore.getPlayer1Sets());
        assertEquals(0, matchScore.getPlayer2Sets());
        assertEquals(0, matchScore.getPlayer1Games(), "После победы в сете количество геймов должно сбрасываться");
        assertEquals(0, matchScore.getPlayer2Games());
    }

    @Test
    void testSetNotFinishedAtFiveSix() {
        for (int i = 0; i < 5; i++) {
            matchScore.completeGame(Winner.PLAYER1);
        }
        for (int i = 0; i < 6; i++) {
            matchScore.completeGame(Winner.PLAYER2);
        }
        assertEquals(0, matchScore.getPlayer1Sets());
        assertEquals(0, matchScore.getPlayer2Sets());
        assertEquals(5, matchScore.getPlayer1Games());
        assertEquals(6, matchScore.getPlayer2Games());
    }

    @Test
    void testSetFinishedAtSevenFive() {
        for (int i = 0; i < 5; i++) {
            matchScore.completeGame(Winner.PLAYER1);
        }
        for (int i = 0; i < 7; i++) {
            matchScore.completeGame(Winner.PLAYER2);
        }
        assertEquals(0, matchScore.getPlayer1Sets());
        assertEquals(1, matchScore.getPlayer2Sets());
        assertEquals(0, matchScore.getPlayer1Games());
        assertEquals(0, matchScore.getPlayer2Games());
    }

    @Test
    void testTieBreakActivated() {
        for (int i = 0; i < 6; i++) {
            matchScore.completeGame(Winner.PLAYER1);
            matchScore.completeGame(Winner.PLAYER2);
        }
        matchScore.checkTieBreak();
        assertTrue(matchScore.isTieBreak(), "При счёте 6-6 должен начаться тайбрейк");
    }

    @Test
    void testMatchFinishesAtTwoSetsWin() {
        for (int i = 0; i < 6; i++) {
            matchScore.completeGame(Winner.PLAYER1);
        }
        for (int i = 0; i < 6; i++) {
            matchScore.completeGame(Winner.PLAYER1);
        }
        assertTrue(matchScore.isFinished(), "Матч должен закончиться после победы в двух сетах");
        assertEquals(matchScore.getPlayer1(), matchScore.getMatchWinner());
    }
}
