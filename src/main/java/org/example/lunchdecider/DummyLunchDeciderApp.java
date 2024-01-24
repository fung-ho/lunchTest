package org.example.lunchdecider;

import org.example.TeamSession;

import java.util.Scanner;
import java.util.List;

public class DummyLunchDeciderApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TeamSession session = new TeamSession();

        System.out.println("Welcome to the Lunch Decider App!");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Start a session");
            System.out.println("2. Join a session");
            System.out.println("3. Display joined teams");
            System.out.println("4. End the session");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    session.startSession();
                    break;
                case 2:
                    if (session.isSessionActive()) {
                        System.out.println("Enter the name of the team to join:");
                        String teamName = scanner.nextLine();
                        session.joinSession(teamName);
                    } else {
                        System.out.println("No active session. Start a session before inviting teams.");
                    }
                    break;
                case 3:
                    displayJoinedTeams(session);
                    break;
                case 4:
                    session.endSession();
                    break;
                case 5:
                    System.out.println("Exiting Lunch Decider App. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void displayJoinedTeams(TeamSession session) {
        if (session.isSessionActive()) {
            List<String> teams = session.getTeams();
            if (!teams.isEmpty()) {
                System.out.println("Teams joined the session:");
                for (String team : teams) {
                    System.out.println("- " + team);
                }
            } else {
                System.out.println("No teams joined yet.");
            }
        } else {
            System.out.println("No active session. Start a session to display joined teams.");
        }
    }
}
