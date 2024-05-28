# Terminal Pokemon Game

This is an object-oriented programming project that simulates a Pokemon game in the terminal. The game is written in Java and uses Maven for dependency management.

## Project Structure

The project follows a standard Maven project structure. The main entry point of the application is the `Main` class located in `src/main/java/Main.java`.

The game logic is primarily handled in the `GameMenu` class located in `src/main/java/controller/GameMenu.java`. This class handles the game loop, user input, and game state updates.

## How to Play

The game is played in the terminal and uses text-based commands for user input. The game continues in a loop until a win condition is met.

## Future Work

We plan to add graphics to the game in the future to enhance the user experience.

## How to Run

To run the game, you need to have Java and Maven installed on your machine. You can then compile and run the game using the following commands:

```bash
mvn clean install
java -cp target/my-app-1.0-SNAPSHOT.jar Main
```

## Contributing
We welcome contributions to this project. Please feel free to submit a pull request or open an issue on GitHub.  
## License
This project is licensed under the MIT License. See the LICENSE file for more details.
