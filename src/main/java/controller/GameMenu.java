package controller;

import model.*;
import view.Command;
import view.GameMenuMessages;

import java.util.Scanner;

public class GameMenu {
    public static void run(Scanner scanner) {
        while (true) {
            if (Game.getInProgressGame().getIsEnded()) {
                GameMenuMessages.showWinningMessage(Game.getInProgressGame().getWinner());
                Game.getInProgressGame().endGame();
                break;
            }
            String input = scanner.nextLine().trim();
            if (Command.SHOW_CURRENT_MENU.matches(input)) {
                GameMenuMessages.currentMenu();
            } else if (Command.SHOW_TABLE.matches(input)) {
                showTable();
            } else if (Command.SHOW_MY_INFO.matches(input)) {
                showInfo(Command.SHOW_MY_INFO.getGroup(input, "placeNumber"), Game.getInProgressGame().getOnUser());
            } else if (Command.SHOW_ENEMY_INFO.matches(input)) {
                showInfo(Command.SHOW_ENEMY_INFO.getGroup(input, "placeNumber"), Game.getInProgressGame().getEnemy());
            } else if (Command.PUT_CARD.matches(input)) {
                String cardName = Command.PUT_CARD.getGroup(input, "cardName");
                String place = Command.PUT_CARD.getGroup(input, "placeNumber");
                putCard(cardName, place);
            } else if (Command.SUBSTITUTE_ACTIVE_CARD.matches(input)) {
                String bench = Command.SUBSTITUTE_ACTIVE_CARD.getGroup(input, "benchNumber");
                substituteActiveCard(bench);
            } else if (Command.EXECUTE_ACTION_WITH_TARGET.matches(input)) {
                String target = Command.EXECUTE_ACTION_WITH_TARGET.getGroup(input, "target");
                executeAction(target);
            } else if (Command.EXECUTE_ACTION_WITHOUT_TARGET.matches(input)) {
                executeAction(null);
            } else if (Command.END_TURN.matches(input)) {
                Game.getInProgressGame().nextRound(Game.getInProgressGame().getOnUser(), Game.getInProgressGame().getEnemy());
                if (Game.getInProgressGame().getIsEnded()) {
                    continue;
                }
                GameMenuMessages.turn(Game.getInProgressGame().getOnUser().getPlayer().getUsername());
            } else
                GameMenuMessages.invalidCommand();
        }
    }

    static void showTable() {
        GameMenuMessages.showTable();
    }

    static void showInfo(String place, Player player) {
        if (!Command.VALID_PLACE.matches(place)) {
            GameMenuMessages.invalidPlaceNumber();
            return;
        }
        int placeNumber = Integer.parseInt(place);
        Card pokemon = (Card) player.getPokemon(placeNumber);
        if (pokemon == null) {
            GameMenuMessages.noPokemonInSelectedPlace();
            return;
        }
        GameMenuMessages.showInfo(player, player.getPokemon(placeNumber), placeNumber);
    }

    static void putCard(String cardName, String place) {
        Game game = Game.getInProgressGame();
        Player player = game.getOnUser();
        if (Card.getCardByName(cardName) == null) {
            GameMenuMessages.invalidCardName();
            return;
        }
        if (player.getCardInDeckByName(cardName) == null) {
            GameMenuMessages.doNotHaveSelectedCard();
            return;
        }
        if (!Command.VALID_PLACE.matches(place)) {
            GameMenuMessages.invalidPlaceNumber();
            return;
        }
        int placeNumber = Integer.parseInt(place);
        if (player.getCardInDeckByName(cardName) instanceof Pokemon && player.getPokemon(placeNumber) != null) {
            GameMenuMessages.pokemonExistThere();
            return;
        }
        if (player.getCardInDeckByName(cardName) instanceof Energy && player.getPokemon(placeNumber) == null) {
            GameMenuMessages.noPokemonInSelectedPlace();
            return;
        }
        if (player.getCardInDeckByName(cardName) instanceof Energy && player.getEnergy(placeNumber, 1) != null &&
                player.getEnergy(placeNumber, 2) != null) {
            GameMenuMessages.pokemonHasTwoEnergy();
            return;
        }
        if (player.getCardInDeckByName(cardName) instanceof Energy && !player.canPlayEnergyCard()) {
            GameMenuMessages.cantPlayEnergyCard();
            return;
        }
        if (player.getCardInDeckByName(cardName) instanceof Pokemon) {
            player.putPokemon((Pokemon) player.getCardInDeckByName(cardName), placeNumber);
        }
        if (player.getCardInDeckByName(cardName) instanceof Energy) {
            player.setCanPlayEnergyCard(false);
            player.addEnergy((Energy) player.getCardInDeckByName(cardName), placeNumber);
        }
        player.getPlayer().removeCardFromShow(player.getCardInDeckByName(cardName));
        player.getPlayer().removeCardFromDeck(player.getCardInDeckByName(cardName));
        GameMenuMessages.cardPutSuccessful();
    }

    static void substituteActiveCard(String bench) {
        if (!Command.VALID_BENCH.matches(bench)) {
            GameMenuMessages.invalidBenchNumber();
            return;
        }
        int benchNumber = Integer.parseInt(bench);
        if (Game.getInProgressGame().getOnUser().getPokemon(benchNumber) == null && Game.getInProgressGame().getOnUser().getPokemon(0) != null) {
            GameMenuMessages.noPokemonInSelectedPlace();
            return;
        }
        if (Game.getInProgressGame().getOnUser().getOnGamePokemon(0).isSleep()) {
            GameMenuMessages.activePokemonSleeping();
            return;
        }
        Game.getInProgressGame().getOnUser().substitute(benchNumber);
        GameMenuMessages.successfulSubstitution();
    }

    static void executeAction(String target) {
        Game game = Game.getInProgressGame();
        Player player = game.getOnUser();
        Player enemy = game.getEnemy();
        Pokemon card = player.getPokemon(0);
        if (card == null) {
            GameMenuMessages.noActivePokemon();
            return;
        }
        if (card instanceof Ducklett && target == null) {
            GameMenuMessages.invalidAction();
            return;
        }
        if (card instanceof Rowlet && target == null) {
            GameMenuMessages.invalidAction();
            return;
        }
        if (!(card instanceof Ducklett) && !(card instanceof Rowlet) && target != null) {
            GameMenuMessages.invalidAction();
            return;
        }
        if (card instanceof Fire || card instanceof Lugia) {
            if (enemy.getOnGamePokemon(0).getPokemon() == null) {
                GameMenuMessages.noPokemonInSelectedPlace();
                return;
            }
        }
        if (target != null) {
            if (card instanceof Ducklett && !Command.VALID_PLACE.matches(target)) {
                GameMenuMessages.invalidTargetNumber();
                return;
            }
            if (card instanceof Rowlet && !Command.VALID_BENCH.matches(target)) {
                GameMenuMessages.invalidTargetNumber();
                return;
            }
            int targetNumber = Integer.parseInt(target);

            if (card instanceof Ducklett) {
                if (enemy.getPokemon(targetNumber) == null) {
                    GameMenuMessages.noPokemonInSelectedPlace();
                    return;
                }
            }
            if (card instanceof Rowlet) {
                if (player.getPokemon(targetNumber) == null) {
                    GameMenuMessages.noPokemonInSelectedPlace();
                    return;
                }
            }
        }
        if (player.getOnGamePokemon(0).isSleep()) {
            GameMenuMessages.activePokemonSleeping();
            return;
        }
        player.getPokemon(0).action(game, target == null ? -1 : Integer.parseInt(target));
        game.removeEnergy(player, enemy);
        game.nextRound(player, enemy);
        if (player.getPokemon(0) instanceof Fire) {
            if (enemy.getPokemon(0) != null)
                ((Fire) player.getPokemon(0)).setBurn(enemy.getOnGamePokemon());
        }
        if (player.getPokemon(0) instanceof Lugia) {
            if (enemy.getPokemon(0) != null)
                ((Lugia) player.getPokemon(0)).setSleep(enemy.getOnGamePokemon(0));
        }
        if (game.getIsEnded()) {
            return;
        }
        GameMenuMessages.actionSuccessful();
        GameMenuMessages.turn(game.getOnUser().getPlayer().getUsername());
    }
}
