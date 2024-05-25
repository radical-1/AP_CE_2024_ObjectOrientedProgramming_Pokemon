package controller;

import model.User;
import view.Command;
import view.LoginMenuMessages;

import java.util.Scanner;

public class LoginMenu {
    public static void run(Scanner scanner) {
        while(true) {
            String input = scanner.nextLine().trim();
            if(Command.EXIT.matches(input)) {
                break;
            }
            else if(Command.SHOW_CURRENT_MENU.matches(input)) {
                LoginMenuMessages.currentMenu();
            }
            else if(Command.REGISTER_USER.matches(input)) {
                String username = Command.REGISTER_USER.getGroup(input, "username");
                String password = Command.REGISTER_USER.getGroup(input, "password");
                String email = Command.REGISTER_USER.getGroup(input, "email");
                registerUser(username, password, email);
            }
            else if(Command.LOGIN_USER.matches(input)) {
                String username = Command.LOGIN_USER.getGroup(input, "username");
                String password = Command.LOGIN_USER.getGroup(input, "password");
                loginUser(username, password, scanner);
            }
            else {
                LoginMenuMessages.invalidCommand();
            }
        }
    }
    public static void registerUser(String username, String password, String email) {
        if(!isUsernameValid(username)) {
            LoginMenuMessages.usernameIsInvalid();
            return;
        }
        if(User.getUserByUsername(username) != null) {
            LoginMenuMessages.usernameIsAlreadyExist();
            return;
        }
        if(!Command.PASSWORD_LENGTH.matches(password)) {
            LoginMenuMessages.shortPassword();
            return;
        }
        if(!Command.PASSWORD_SPECIAL_CHARACTERS.matches(password)) {
            LoginMenuMessages.passwordSpecialCharacters();
            return;
        }
        if(!Command.PASSWORD_STARTS_WITH_LETTER.matches(password)) {
            LoginMenuMessages.passwordStartsWithLetter();
            return;
        }
        if(!Command.EMAIL_VALID_ENDING.matches(email)) {
            LoginMenuMessages.emailIsInvalid();
            return;
        }
        if(!Command.EMAIL_VALID_ADDRESS.matches(email)) {
            LoginMenuMessages.emailCantUseSpecialCharacters();
            return;
        }
        User user = new User(username, password, email);
        User.addUser(user);
        LoginMenuMessages.userRegisteredSuccessfully();
    }
    public static boolean isUsernameValid(String username) {
        return Command.VALID_USERNAME.matches(username);
    }
    public static void loginUser(String username, String password, Scanner scanner) {
        User user = User.getUserByUsername(username);
        if(user == null) {
            LoginMenuMessages.usernameDoesNotExist();
            return;
        }
        if(!user.getPassword().equals(password)) {
            LoginMenuMessages.passwordIsIncorrect();
            return;
        }
        LoginMenuMessages.userLoggedInSuccessfully();
        User.setLoggedInUser(user);
        MainMenu.run(scanner);
    }
}
