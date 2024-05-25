package controller;

import model.Card;
import model.User;
import view.Command;
import view.ShopMenuMessages;

import java.util.Scanner;

public class ShopMenu {
    public static void run(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (Command.BACK.matches(input)) {
                break;
            } else if (Command.SHOW_CURRENT_MENU.matches(input)) {
                ShopMenuMessages.currentMenu();
            } else if (Command.BUY_CARD.matches(input)) {
                String cardName = Command.BUY_CARD.getGroup(input, "cardName");
                buyCard(cardName);
            } else if (Command.SELL_CARD.matches(input)) {
                String cardName = Command.SELL_CARD.getGroup(input, "cardName");
                sellCard(cardName);
            } else {
                ShopMenuMessages.invalidCommand();
            }
        }
    }

    static void buyCard(String cardName) {
        Card card = Card.getCardByName(cardName);
        if (card == null) {
            ShopMenuMessages.cardNameIsInvalid();
            return;
        }
        if (!User.getLoggedInUser().canBuyCard(card)) {
            ShopMenuMessages.notEnoughCoinToBuy(cardName);
            return;
        }
        User.getLoggedInUser().buyCard(card);
        ShopMenuMessages.cardBoughtSuccessfully(cardName);
    }

    static void sellCard(String cardName) {
        Card card = Card.getCardByName(cardName);
        if(card == null) {
            ShopMenuMessages.cardNameIsInvalid();
            return;
        }
        if(!User.getLoggedInUser().isCardInStorage(card)) {
            ShopMenuMessages.doNotHaveThisTypeOfCardForSell();
            return;
        }
        User.getLoggedInUser().sellCard(card);
        ShopMenuMessages.cardSoldSuccessfully(cardName);
    }
}

