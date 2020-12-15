package pt.flawless.soccermanager.UI.CRUD;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pt.flawless.soccermanager.R;
import pt.flawless.soccermanager.Team.Team;
import pt.flawless.soccermanager.Team.TeamHelperCRUD;
import pt.flawless.soccermanager.UI.TeamActivity;

public class TeamCRUD extends AppCompatActivity {

    EditText editTextName;
    EditText editTextSigla;
    Team team;
    Button buttonCreate;

    static Boolean editMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_c_r_u_d);

        Intent opAExecutar = getIntent();
        String msg = opAExecutar.getStringExtra(TeamActivity.OP_INSERIR);
        team = (Team) opAExecutar.getSerializableExtra(TeamActivity.OP_ATUALIZAR);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextSigla = (EditText) findViewById(R.id.editTextSigla);
        buttonCreate = (Button) findViewById(R.id.buttonCreate);

        if (msg != null && msg.equals("0")) {
            editTextName.setEnabled(true);
            editTextSigla.setEnabled(true);
            buttonCreate.setEnabled(true);
            editMode = false;
        }
        if (team != null) {
            editTextName.setText(team.getTeamName());
            editTextSigla.setText(team.getSigla());
            buttonCreate.setText("Update");
            editMode = true;
        }
    }

    public void back(View v) {
        this.finish();
    }

    public void createTeam(View v) {
        boolean resultado = false;

        String teamName = editTextName.getText().toString();
        String teamSigla = editTextSigla.getText().toString();

        if (teamName.length() > 0 && teamSigla.length() > 0) {
            if (team == null) {
                Team newTeam = new Team();
                newTeam.setTeamName(teamName);
                newTeam.setSigla(teamSigla);
                TeamHelperCRUD teamHelperCRUD = new TeamHelperCRUD(this);
                resultado = teamHelperCRUD.add(newTeam);
                if (resultado) {
//                    AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
//                    ad.setTitle("Sucesso");
//                    ad.setMessage("Plano de Treino adicionado!");
//                    ad.setIcon(R.drawable.certo);
//                    ad.show();
                    Toast.makeText(this, "Team created successfuly", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "SQL Error :(", Toast.LENGTH_SHORT).show();
                }
                this.finish();
            } else {
                team.setTeamName(teamName);
                team.setSigla(teamSigla);
                TeamHelperCRUD teamHelperCRUD = new TeamHelperCRUD(this);
                resultado = teamHelperCRUD.update(team);
                if (resultado) {
                    Toast.makeText(this, "Team updated successfuly", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "SQL Error :(", Toast.LENGTH_SHORT).show();
                }
                this.finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_crud, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu m) {
        invalidateOptionsMenu();
        m.findItem(R.id.menu_update).setEnabled(editMode);
        m.findItem(R.id.menu_delete).setEnabled(editMode);
        return super.onPrepareOptionsMenu(m);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_update:
                editTextName.setEnabled(true);
                editTextSigla.setEnabled(true);
                buttonCreate.setEnabled(true);
                return true;
            case R.id.menu_delete:

                AlertDialog.Builder ad = new AlertDialog.Builder(TeamCRUD.this);
                ad.setTitle("Eliminar");
                ad.setMessage("Tem a certeza que deseja eliminar este registo?");
                ad.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TeamHelperCRUD teamHelperCRUD = new TeamHelperCRUD(TeamCRUD.this);
                        boolean resultado = teamHelperCRUD.delete(team);
                        if (resultado) {
                            Toast.makeText(TeamCRUD.this, "Team deleted successfuly!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(TeamCRUD.this, "SQL Error :(", Toast.LENGTH_SHORT).show();
                        }
                        dialogInterface.dismiss();
                        TeamCRUD.this.finish();
                    }
                });
                ad.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                ad.show();



                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}