package controller;

import model.Game;
import model.User;
import view.Command;
import view.MainMenuMessages;

import java.util.Scanner;

public class MainMenu {
    public static void run(Scanner scanner) {
        while(true) {
            String input = scanner.nextLine().trim();
            if(Command.LOGOUT.matches(input)) {
                User.setLoggedInUser(null);
                break;
            }
            else if(Command.SHOW_CURRENT_MENU.matches(input)) {
                MainMenuMessages.currentMenu();
            }
            else if(Command.GO_TO_SHOP_MENU.matches(input)) {
                ShopMenu.run(scanner);
            }
            else if(Command.GO_TO_PROFILE_MENU.matches(input)) {
                ProfileMenu.run(scanner);
            }
            else if(Command.START_NEW_GAME.matches(input)) {
                String username = Command.START_NEW_GAME.getGroup(input, "username");
                startNewGame(username, scanner);
            }
            else
                MainMenuMessages.invalidCommand();
        }
    }
    public static void startNewGame(String username, Scanner scanner) {
        User user = User.getUserByUsername(username);
        if(user == null) {
            MainMenuMessages.incorrectUsername();
            return;
        }
        if(!User.getLoggedInUser().canStartGame()) {
            MainMenuMessages.deckIsNotFull(User.getLoggedInUser().getUsername());
            return;
        }
        if(!user.canStartGame()) {
            MainMenuMessages.deckIsNotFull(user.getUsername());
            return;
        }

        Game game = new Game(User.getLoggedInUser(), user);
        game.startGame();
        GameMenu.run(scanner);
    }
}
