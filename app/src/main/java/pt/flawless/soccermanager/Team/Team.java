package pt.flawless.soccermanager.Team;

import java.io.Serializable;
import java.util.UUID;

public class Team implements Serializable  {
    private String id;
    private String teamName;
    private String sigla;

    public Team() {
        setId();
        setTeamName("");
        setSigla("");
    }

    public Team(String dbUID) {
        id = dbUID;
        setTeamName("");
        setSigla("");
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID().toString();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return getTeamName() + " (" + getSigla() + ")";
    }
}
