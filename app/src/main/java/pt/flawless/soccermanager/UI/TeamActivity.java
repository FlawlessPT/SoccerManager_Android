package pt.flawless.soccermanager.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import pt.flawless.soccermanager.R;
import pt.flawless.soccermanager.Team.Team;
import pt.flawless.soccermanager.Team.TeamHelperCRUD;
import pt.flawless.soccermanager.UI.CRUD.TeamCRUD;

public class TeamActivity extends AppCompatActivity {

    public final static String OP_INSERIR = "INSERIR";
    public final static String OP_ATUALIZAR = "ATUALIZAR";

    List<Team> teamsList = new ArrayList<Team>();
    ListView listView;
    FloatingActionButton addButton;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        backButton = (Button) findViewById(R.id.backButton);

        addButton = (FloatingActionButton) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToTeamCRUD();
            }
        });
        listView = (ListView) findViewById(R.id.listaTeams);
        inicializarLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        inicializarLista();
    }

    public void goToGame(View v) {
        Intent gameActivity = new Intent(this, MainActivity.class);
        startActivity(gameActivity);
    }

    private void inicializarLista() {
        TeamHelperCRUD chcrud = new TeamHelperCRUD(this);
        teamsList.clear();
        teamsList = chcrud.getTeams();

        if (teamsList.isEmpty()) {
            listView.setVisibility(View.INVISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
            bindLista();
        }
    }

    public void bindLista() {
        ArrayAdapter<Team> aa = new ArrayAdapter<Team>(this, android.R.layout.simple_list_item_1, teamsList);
        final ListView lstViewer = (ListView) findViewById(R.id.listaTeams);
        lstViewer.setAdapter(aa);
        lstViewer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                // item selecionado
                Team team = (Team) lstViewer.getAdapter().getItem(i);
                Intent teamCRUD = new Intent(TeamActivity.this, TeamCRUD.class);
                teamCRUD.putExtra(OP_ATUALIZAR, team);
                startActivity(teamCRUD);

            }
        });
    }

    public void goToTeamCRUD() {
        Intent teamCRUDActivity = new Intent(this, TeamCRUD.class);
        teamCRUDActivity.putExtra(OP_INSERIR, "0");
        startActivity(teamCRUDActivity);
    }
}