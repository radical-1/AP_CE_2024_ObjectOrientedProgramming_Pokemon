package model;

import java.util.ArrayList;

public class User {
    private final String username;
    private final String password;
    private final String email;
    private int coins;
    private int experience;
    private final ArrayList<Card> deck;
    private final ArrayList<Card> storage;
    private final ArrayList<Card> show;
    private static final ArrayList<User> users = new ArrayList<>();
    private static User loggedInUser;


    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.coins = 300;
        this.experience = 0;
        this.deck = new ArrayList<>();
        this.storage = new ArrayList<>();
        this.show = new ArrayList<>();
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void addCardToDeck(Card card) {
        deck.add(card);
    }

    public void removeCardFromDeck(Card card) {
        deck.remove(card);
    }

    public void addCardToStorage(Card card) {
        storage.add(card);
    }

    public void removeCardFromStorage(Card card) {
        storage.remove(card);
    }

    public void addCardToShow(Card card) {
        show.add(card);
    }
    public void removeCardFromShow(Card card) {
        show.remove(card);
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getCoins() {
        return coins;
    }

    public int getExperience() {
        return experience;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Card> getStorage() {
        return storage;
    }

    public Card getCardInDeckByName(String cardName) {
        for (Card card : deck) {
            if (card.getName().equals(cardName)) {
                return card;
            }
        }
        return null;
    }

    public boolean canBuyCard(Card card) {
        return coins >= card.getBuyValue();
    }

    public boolean isCardInDeck(Card card) {
        return deck.contains(card);
    }

    public boolean isCardInStorage(Card card) {
        for (Card c : storage) {
            if (c.getName().equals(card.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean canAddThisCardToDeck(Card card) {
        if (card instanceof Energy) {
            return true;
        }
        for (Card c : deck) {
            if (c.getName().equals(card.getName())) {
                return false;
            }
        }
        return true;
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean canStartGame() {
        return this.getDeck().size() == 12;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public void buyCard(Card card) {
        coins -= card.getBuyValue();
        addCardToStorage(card);
        addCardToShow(card);
    }

    public void sellCard(Card card) {
        coins += card.getSellValue();
        if(getCardInDeckByName(card.getName()) != null) {
            removeCardFromDeck(card);
        } else removeCardFromStorage(card);
        removeCardFromShow(card);
    }
    public ArrayList<Card> getShow() {
        return show;
    }

    public static ArrayList<User> rankedUsers() {
        ArrayList<User> users = new ArrayList<>(User.getUsers());
        users.sort((u1, u2) -> {
            int experienceComparison = Integer.compare(u2.getExperience(), u1.getExperience());
            if (experienceComparison != 0) {
                return experienceComparison;
            } else {
                return u1.getUsername().compareTo(u2.getUsername());
            }
        });
        return users;
    }
}


