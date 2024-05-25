package view;

public class LoginMenuMessages {
    public static void currentMenu() {
        System.out.println("login menu");
    }

    public static void invalidCommand() {
        System.out.println("invalid command");
    }

    public static void usernameIsInvalid() {
        System.out.println("username format is invalid");
    }
    public static void shortPassword() {
        System.out.println("password length too small or short");
    }
    public  static void passwordSpecialCharacters() {
        System.out.println("your password must have at least one special character");
    }
    public static void passwordStartsWithLetter() {
        System.out.println("your password must start with english letters");
    }

    public static void usernameIsAlreadyExist() {
        System.out.println("username already exists");
    }
    public static void emailIsInvalid() {
        System.out.println("email format is invalid");
    }
    public static void emailCantUseSpecialCharacters() {
        System.out.println("you can't use special characters");
    }
    public static void userRegisteredSuccessfully() {
        System.out.println("user registered successfully");
    }

    public static void userLoggedInSuccessfully() {
        System.out.println("user logged in successfully");
    }
    public static void usernameDoesNotExist() {
        System.out.println("username doesn't exist");
    }
    public static void passwordIsIncorrect() {
        System.out.println("password is incorrect");
    }
}
