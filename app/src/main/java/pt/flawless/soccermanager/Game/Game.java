package pt.flawless.soccermanager.Game;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import pt.flawless.soccermanager.Team.Team;
import pt.flawless.soccermanager.Utils.Alert.AlertType;
import pt.flawless.soccermanager.Utils.AlertHelper;

public class Game {
    private Context context;
    private GameState state;
    private Team team1;
    private Team team2;

    private int scoreTeam1;
    private int scoreTeam2;

    private Team winner;

    public Game(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public void start() {
        generateScore();
        this.setState(GameState.RUNNING);
    }

    public void finish() {
        checkWinner();
        this.setState(GameState.FINISHED);
    }

    public void generateScore() {
        this.scoreTeam1 = randomValue();
        this.scoreTeam2 = randomValue();
    }

    private int randomValue() {
        Random rand = new Random();

        int randValue = rand.nextInt(5);

        return randValue;
    }

    private void checkWinner() {
        if (scoreTeam1 > scoreTeam2) {
            winner = team1;
        } else if (scoreTeam1 == scoreTeam2) {
            winner = null;
        }
        else {
            winner = team2;
        }
        showWinner();
    }

    private void showWinner() {
        AlertHelper winnerWarning = new AlertHelper(context);
        winnerWarning.setAlertType(AlertType.OK);
        if (winner == null) {
            winnerWarning.setMessage("Jogo Terminado!\nO jogo terminou empatado!");
        }
        else {
            winnerWarning.setMessage("Jogo Terminado!\nA equipa vencedora foi: " + winner.getTeamName());
        }

        winnerWarning.show();
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getScoreTeam1() {
        return this.scoreTeam1;
    }

    public int getScoreTeam2() {
        return this.scoreTeam2;
    }
}
