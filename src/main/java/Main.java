import java.util.Scanner;
import controller.LoginMenu;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginMenu.run(scanner);
    }
}
