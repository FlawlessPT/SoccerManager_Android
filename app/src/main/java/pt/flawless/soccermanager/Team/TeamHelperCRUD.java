package pt.flawless.soccermanager.Team;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pt.flawless.soccermanager.Database.DbWrapperSetup;
import pt.flawless.soccermanager.Database.TeamDbProperties;

public class TeamHelperCRUD extends SQLiteOpenHelper {
    private SQLiteDatabase myDB;

    public TeamHelperCRUD(Context cnt) {
        super(cnt, DbWrapperSetup.BD_Nome, null, DbWrapperSetup.BD_Versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        DbWrapperSetup.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        DbWrapperSetup.onUpgrade(db, oldV, newV);
    }

    private void abrirDBLocal() {
        myDB = this.getWritableDatabase();
    }

    public List<Team> getTeams() {
        if (myDB == null) abrirDBLocal();
        List<Team> _listaADevolver = new ArrayList<Team>();
        String qLista = "SELECT * FROM " + TeamDbProperties.TBL_TEAM;
        Cursor cursor = myDB.rawQuery(qLista, null);
        if (cursor.moveToFirst()) {
            do {
                String uid = cursor.getString(cursor.getColumnIndex(TeamDbProperties.COL_ID));
                Team team = new Team(uid);
                team.setTeamName(cursor.getString(cursor.getColumnIndex(TeamDbProperties.COL_Nome)));
                team.setSigla(cursor.getString(cursor.getColumnIndex(TeamDbProperties.COL_Sigla)));
                _listaADevolver.add(team);
            } while (cursor.moveToNext());
        }
        return _listaADevolver;
    }

    public boolean add(Team team) {
        boolean retval = false;

        if (myDB == null) abrirDBLocal();

        ContentValues pacote = new ContentValues();

        pacote.put(TeamDbProperties.COL_ID, team.getId());
        pacote.put(TeamDbProperties.COL_Nome, team.getTeamName());
        pacote.put(TeamDbProperties.COL_Sigla, team.getSigla());
        pacote.put(TeamDbProperties.COL_Estado, 1);

        long ID = myDB.insert(TeamDbProperties.TBL_TEAM, null, pacote);

        if (ID > 0) retval = true;

        return retval;
    }

    public boolean update(Team team) {
        boolean retval = false;

        if (myDB == null) abrirDBLocal();

        ContentValues pacote = new ContentValues();

        pacote.put(TeamDbProperties.COL_ID, team.getId());
        pacote.put(TeamDbProperties.COL_Nome, team.getTeamName());
        pacote.put(TeamDbProperties.COL_Sigla, team.getSigla());

        int rowsAffected = myDB.update(TeamDbProperties.TBL_TEAM, pacote,
                TeamDbProperties.COL_ID + "=?",
                new String[] {String.valueOf(team.getId())});

        if (rowsAffected > 0) retval = true;

        return retval;
    }

    public boolean delete(Team team) {
        boolean retval = false;

        if (myDB == null) abrirDBLocal();

        int rowsAffected = myDB.delete(TeamDbProperties.TBL_TEAM,
                TeamDbProperties.COL_ID + "=?", new String[] {String.valueOf(team.getId())});

        if (rowsAffected > 0) retval = true;

        return retval;
    }
}
