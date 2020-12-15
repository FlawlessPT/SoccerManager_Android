package pt.flawless.soccermanager.UI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pt.flawless.soccermanager.Game.Game;
import pt.flawless.soccermanager.R;
import pt.flawless.soccermanager.Team.Team;
import pt.flawless.soccermanager.Team.TeamHelperCRUD;
import pt.flawless.soccermanager.Utils.Alert.AlertType;
import pt.flawless.soccermanager.Utils.AlertHelper;

public class MainActivity extends AppCompatActivity {

    TextView tvTitleScore;
    TextView tvTeam1Name;
    TextView tvTeam2Name;
    TextView tvTeam1Score;
    TextView tvTeam2Score;
    Button playAgainButton;
    Button playButton;
    Spinner spinnerTeam1;
    Spinner spinnerTeam2;

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerTeam1 = (Spinner) findViewById(R.id.spinnerTeam1);
        spinnerTeam2 = (Spinner) findViewById(R.id.spinnerTeam2);

        tvTitleScore = (TextView) findViewById(R.id.title4);
        tvTeam1Name = (TextView) findViewById(R.id.title5);
        tvTeam2Name = (TextView) findViewById(R.id.title6);
        tvTeam1Score = (TextView) findViewById(R.id.title7);
        tvTeam2Score = (TextView) findViewById(R.id.title8);

        playAgainButton = (Button) findViewById(R.id.buttonPlayAgain);
        playButton = (Button) findViewById(R.id.buttonPlay);

        loadSpinners();
        hideScore();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu_main, m);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem it) {
        int id = it.getItemId();

        if (id == R.id.menu_team_manage) {
            openTeamManage();
        }
        return true;
    }

    public void startGame(View v) {
        String team1Name = spinnerTeam1.getSelectedItem().toString();
        String team2Name = spinnerTeam2.getSelectedItem().toString();

        //TODO: FAZER SISTEMA DE GERAR RESULTADO RANDOM
        Team team1 = new Team();
        team1.setTeamName(team1Name);

        Team team2 = new Team();
        team2.setTeamName(team2Name);

        game = new Game(team1, team2);
        game.setContext(this);

        showScore();
        game.start();

        int parentesis1 = game.getTeam1().getTeamName().indexOf("(");
        int parentesis2 = game.getTeam2().getTeamName().indexOf("(");
        tvTeam1Name.setText(game.getTeam1().getTeamName().substring(0, parentesis1));
        tvTeam2Name.setText(game.getTeam2().getTeamName().substring(0, parentesis2));
        tvTeam1Score.setText(String.valueOf(game.getScoreTeam1()));
        tvTeam2Score.setText(String.valueOf(game.getScoreTeam2()));

        game.finish();
    }

    public void playAgain(View v) {
        hideScore();
    }

    private List<Team> getTeamsList() {
        TeamHelperCRUD exercicioHelperCRUD = new TeamHelperCRUD(this);
        return exercicioHelperCRUD.getTeams();
    }

    public void loadSpinners() {
        ArrayAdapter<Team> adapter = new ArrayAdapter<Team>(this, android.R.layout.simple_spinner_item, getTeamsList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTeam1.setAdapter(adapter);
        spinnerTeam2.setAdapter(adapter);
    }

    private void openTeamManage() {
        Intent teamManage = new Intent(this, TeamActivity.class);
        startActivity(teamManage);
    }

    private void showScore() {
        tvTitleScore.setVisibility(View.VISIBLE);
        tvTeam1Name.setVisibility(View.VISIBLE);
        tvTeam2Name.setVisibility(View.VISIBLE);
        tvTeam1Score.setVisibility(View.VISIBLE);
        tvTeam2Score.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.VISIBLE);
        playButton.setEnabled(false);
        playButton.setClickable(false);
    }

    private void hideScore() {
        tvTitleScore.setVisibility(View.INVISIBLE);
        tvTeam1Name.setVisibility(View.INVISIBLE);
        tvTeam2Name.setVisibility(View.INVISIBLE);
        tvTeam1Score.setVisibility(View.INVISIBLE);
        tvTeam2Score.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        playButton.setEnabled(true);
        playButton.setClickable(true);
    }
}