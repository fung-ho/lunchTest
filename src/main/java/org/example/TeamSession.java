package org.example;

import java.util.ArrayList;
import java.util.List;

public class TeamSession {
    private boolean isSessionActive;
    private List<String> teams;

    public TeamSession() {
        this.isSessionActive = false;
        this.teams = new ArrayList<>();
    }

    public void startSession() {
        if (!isSessionActive) {
            isSessionActive = true;
            teams.clear();
            System.out.println("org.example.Session started. Invite teams to join.");
        } else {
            System.out.println("A session is already active. End the current session before starting a new one.");
        }
    }

    public void joinSession(String teamName) {
        if (isSessionActive) {
            teams.add(teamName);
            System.out.println("Team '" + teamName + "' joined the session.");
        } else {
            System.out.println("No active session. Start a session before inviting teams.");
        }
    }

    public void endSession() {
        if (isSessionActive) {
            this.isSessionActive = false;
            teams.clear();
            System.out.println("The current session is ended.");
        } else {
            System.out.println("No active session. Error in stopping session.");
        }
    }
    // Additional methods as needed

    // Getter for teams
    public List<String> getTeams() {
        return teams;
    }

    public boolean isSessionActive() {
        return isSessionActive;
    }
}

