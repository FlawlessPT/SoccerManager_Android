package pt.flawless.soccermanager.Database;

public class TeamDbProperties {
    public static final String TBL_TEAM = "tblTeam";

    public static final String COL_AutoNumber = "id";
    public static final String COL_ID = "uidTeam";
    public static final String COL_Nome = "nome";
    public static final String COL_Sigla = "sigla";
    public static final String COL_Estado = "estado";

    public static final String Q_TBL_TEAM_CREATE =
            "CREATE TABLE " + TBL_TEAM + " (" +
                    COL_AutoNumber + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COL_ID + " TEXT," +
                    COL_Nome + " TEXT," +
                    COL_Sigla + " TEXT," +
                    COL_Estado + " INTEGER" + ");";
}
