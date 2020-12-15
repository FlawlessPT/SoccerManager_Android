package pt.flawless.soccermanager.Database;

import android.database.sqlite.SQLiteDatabase;

public class DbWrapperSetup {
    public static final String BD_Nome = "SoccerManager.db3";
    public static final int BD_Versao = 1;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(TeamDbProperties.Q_TBL_TEAM_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TeamDbProperties.Q_TBL_TEAM_CREATE);
        onCreate(db);
    }
}
