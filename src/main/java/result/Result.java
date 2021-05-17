package result;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

/**
 * Represents the result of a match on board game.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Result {
    private String player1;
    private String player2;
    private String winner;
    private String date;

    /**
     * No args constructor.
     */
    public Result() {
    }

    /**
     * Parametrized constructor.
     *
     * @param player1 the name of player1
     * @param player2 the name of player2
     * @param winner the name of the winner
     */
    public Result(String player1, String player2, String winner,String date) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Result{" +
                "player1='" + player1 + '\'' +
                ", player2='" + player2 + '\'' +
                ", winner='" + winner + '\'' +
                '}';
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public String getWinner() {
        return winner;
    }

    public String getDate() {
        return date;
    }
}
