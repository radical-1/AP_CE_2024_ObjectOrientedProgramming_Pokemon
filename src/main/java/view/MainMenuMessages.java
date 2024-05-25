package view;

import model.User;

public class MainMenuMessages {
    public static void currentMenu() {
        System.out.println("main menu");
    }
    public static void invalidCommand() {
        System.out.println("invalid command");
    }
    public static void incorrectUsername() {
        System.out.println("username is incorrect");
    }
    public static void deckIsNotFull(String username) {
        System.out.println(username + " has no 12 cards in deck");
    }
}
