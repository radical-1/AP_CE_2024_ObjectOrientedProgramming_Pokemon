package view;

import model.*;

public class GameMenuMessages {
    public static void currentMenu() {
        System.out.println("game menu");
    }

    public static void invalidCommand() {
        System.out.println("invalid command");
    }

    public static void invalidCardName() {
        System.out.println("card name is invalid");
    }

    public static void showWinningMessage(String winner) {
        System.out.println("Winner: " + winner);
    }

    public static void showTable() {
        Game game = Game.getInProgressGame();
        Player player = game.getOnUser();
        Player enemy = game.getEnemy();

        System.out.println("round " + (game.getRound() / 2 + 1));
        System.out.println("your active card:");
        System.out.println(printCards(player, 0));
        System.out.println();

        System.out.println("your hand:");
        int counter = 1;
        for (Card card : player.getPlayer().getDeck()) {
            System.out.println((counter++) + "." + card.getName());
        }
        System.out.println();

        System.out.println("your bench:");
        for (int i = 1; i < 4; i++) {
            System.out.println(i + "." + printCards(player, i));
        }
        System.out.println();

        System.out.println(enemy.getPlayer().getUsername() + "'s active card:");
        System.out.println(printCards(enemy, 0));
        System.out.println();
        System.out.println(enemy.getPlayer().getUsername() + "'s bench:");
        for (int i = 1; i < 4; i++) {
            System.out.println(i + "." + printCards(enemy, i));
        }
    }

    public static void invalidPlaceNumber() {
        System.out.println("invalid place number");
    }

    public static void noPokemonInSelectedPlace() {
        System.out.println("no pokemon in the selected place");
    }

    public static void showInfo(Player player, Pokemon pokemon, int placeNumber) {
        System.out.println("pokemon: " + ((Card) pokemon).getName());
        System.out.println("special condition: " + player.getCondition(placeNumber));
        System.out.printf("hitpoint: %.2f/%.2f\n", pokemon.getHitPoint(), pokemon.getMaxHitPoint());
        System.out.println("energy 1: " + player.getOnGamePokemon(placeNumber).nameOfEnergy(1));
        System.out.println("energy 2: " + player.getOnGamePokemon(placeNumber).nameOfEnergy(2));
    }

    public static void doNotHaveSelectedCard() {
        System.out.println("you don't have the selected card");
    }

    public static void pokemonExistThere() {
        System.out.println("a pokemon already exists there");
    }

    public static void pokemonHasTwoEnergy() {
        System.out.println("pokemon already has 2 energies");
    }

    public static void cantPlayEnergyCard() {
        System.out.println("you have already played an energy card in this turn");
    }

    public static void cardPutSuccessful() {
        System.out.println("card put successful");
    }

    public static void invalidBenchNumber() {
        System.out.println("invalid bench number");
    }

    public static void activePokemonSleeping() {
        System.out.println("active pokemon is sleeping");
    }

    public static void successfulSubstitution() {
        System.out.println("substitution successful");
    }

    public static void noActivePokemon() {
        System.out.println("no active pokemon");
    }

    public static void invalidAction() {
        System.out.println("invalid action");
    }

    public static void invalidTargetNumber() {
        System.out.println("invalid target number");
    }

    public static void actionSuccessful() {
        System.out.println("action executed successfully");
    }

    public static void turn(String username) {
        System.out.println(username + "'s turn");
    }

    static String printCards(Player player, int index) {

        Card pokemon = (Card) player.getPokemon(index);
        if (pokemon == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        result.append(pokemon.getName()).append("|");
        for (int i = 0; i < 2; i++) {
            Energy energy = player.getOnGamePokemon(index).getEnergy(i);
            if (energy == null) {
                result.append("|");
                continue;
            }
            Card card = (Card) energy;
            result.append(card.getName()).append("|");
        }
        return result.toString().replaceAll("\\|$", "");
    }
}
