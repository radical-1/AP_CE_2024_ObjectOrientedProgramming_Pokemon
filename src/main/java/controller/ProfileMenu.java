package controller;

import model.Card;
import model.User;
import view.Command;
import view.ProfileMenuMessages;

import java.util.ArrayList;
import java.util.Scanner;


public class ProfileMenu {
    public static void run(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (Command.BACK.matches(input)) {
                break;
            } else if (Command.SHOW_CURRENT_MENU.matches(input)) {
                ProfileMenuMessages.currentMenu();
            } else if (Command.SHOW_COINS.matches(input)) {
                ProfileMenuMessages.numberOfCoins(User.getLoggedInUser().getCoins());
            } else if (Command.SHOW_EXPERIENCE.matches(input)) {
                ProfileMenuMessages.showExperience(User.getLoggedInUser().getExperience());
            } else if (Command.SHOW_STORAGE.matches(input)) {
                showStorage(User.getLoggedInUser());
            } else if (Command.EQUIP_CARD_TO_MY_DECK.matches(input)) {
                String cardName = Command.EQUIP_CARD_TO_MY_DECK.getGroup(input, "cardName");
                equipCardToDeck(cardName);
            } else if (Command.UNEQUIP_CARD_FROM_MY_DECK.matches(input)) {
                String cardName = Command.UNEQUIP_CARD_FROM_MY_DECK.getGroup(input, "cardName");
                unequipCardFromDeck(cardName);
            } else if (Command.SHOW_MY_DECK.matches(input)) {
                showDeck();
            } else if (Command.SHOW_MY_RANK.matches(input)) {
                int rank = User.rankedUsers().indexOf(User.getLoggedInUser()) + 1;
                System.out.println("your rank:" + rank);
            } else if (Command.SHOW_RANKING.matches(input)) {
                showRanking();
            } else
                ProfileMenuMessages.invalidCommand();
        }
    }

    static void showStorage(User user) {
        ProfileMenuMessages.showStorage(user.getShow());
    }

    static void equipCardToDeck(String cardName) {
        Card card = Card.getCardByName(cardName);
        User user = User.getLoggedInUser();
        if (card == null) {
            ProfileMenuMessages.invalidCardName();
            return;
        }
        if (!user.isCardInStorage(card)) {
            ProfileMenuMessages.doNotHaveThisTypeOfCard();
            return;
        }
        if (user.canStartGame()) {
            ProfileMenuMessages.deckIsFull();
            return;
        } 
        if (!user.canAddThisCardToDeck(card)) {
            ProfileMenuMessages.haveThisPokemonInDeck();
            return;
        }
        ProfileMenuMessages.equipCardToDeck(card.getName());
        user.addCardToDeck(card);
        user.removeCardFromStorage(card);
    }

    static void unequipCardFromDeck(String cardName) {
        Card card = Card.getCardByName(cardName);
        User user = User.getLoggedInUser();
        if (card == null) {
            ProfileMenuMessages.invalidCardName();
            return;
        }
        if (!user.isCardInDeck(card)) {
            ProfileMenuMessages.doNotHaveThisTypeOfCardInDeck();
            return;
        }
        ProfileMenuMessages.unEquipCardFromDeck(card.getName());
        user.removeCardFromDeck(card);
        user.addCardToStorage(card);
    }

    static void showDeck() {
        ProfileMenuMessages.showDeck(User.getLoggedInUser().getDeck());
    }

    static void showRanking() {
        ProfileMenuMessages.showRanking(User.rankedUsers());
    }
}
