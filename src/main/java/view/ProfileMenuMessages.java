package view;

import model.Card;
import model.User;

import java.util.ArrayList;

public class ProfileMenuMessages {
    public static void currentMenu() {
        System.out.println("profile menu");
    }

    public static void invalidCommand() {
        System.out.println("invalid command");
    }
    public static void numberOfCoins(int coins) {
        System.out.println("number of coins:" + coins);
    }
    public static void showExperience(int experience) {
        System.out.println("experience:" + experience);
    }
    public static void showStorage(ArrayList<Card> storage) {
        for (int i = 0; i < storage.size(); i++) {
            System.out.println((i + 1) + "." + storage.get(i).getType() + " " + storage.get(i).getName() + " " + storage.get(i).getBuyValue());
        }
    }
    public static void invalidCardName() {
        System.out.println("card name is invalid");
    }
    public static void doNotHaveThisTypeOfCard() {
        System.out.println("you don't have this type of card");
    }
    public static void deckIsFull() {
        System.out.println("your deck is already full");
    }
    public static void haveThisPokemonInDeck() {
        System.out.println("you have already added this type of pokemon to your deck");
    }
    public static void equipCardToDeck(String cardName) {
        System.out.println("card " + cardName + " equipped to your deck successfully");
    }
    public static void doNotHaveThisTypeOfCardInDeck() {
        System.out.println("you don't have this type of card in your deck");
    }
    public static void unEquipCardFromDeck(String cardName) {
        System.out.println("card " + cardName + " unequipped from your deck successfully");
    }
    public static void showDeck(ArrayList<Card> deck) {
        for (int i = 0; i < deck.size(); i++) {
            System.out.println((i + 1) + "." + deck.get(i).getName());
        }
    }
    public static void showRanking(ArrayList<User> rankedUsers) {
        for (int i = 0; i < rankedUsers.size(); i++) {
            System.out.println((i + 1) + ".username:" + rankedUsers.get(i).getUsername() + " experience:" + rankedUsers.get(i).getExperience());
        }
    }
}
