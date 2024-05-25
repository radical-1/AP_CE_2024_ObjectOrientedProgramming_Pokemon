package model;

import java.util.ArrayList;
import java.util.Objects;

public class Card {
    private final String name;
    private final int buyValue;
    private final int sellValue;

    public Card(String name, int buyValue, int sellValue) {
        this.name = name;
        this.buyValue = buyValue;
        this.sellValue = sellValue;
    }


    public int getBuyValue() {
        return buyValue;
    }

    public String getName() {
        return name;
    }

    public int getSellValue() {
        return sellValue;
    }

    public static Card getCardByName(String name) {
        switch (name) {
            case "dragonite":
                return new Dragonite();
            case "tepig":
                return new Tepig();
            case "ducklett":
                return new Ducklett();
            case "lugia":
                return new Lugia();
            case "pineco":
                return new Pineco();
            case "rowlet":
                return new Rowlet();
            case "pink":
                return new Pink();
            case "yellow":
                return new Yellow();
            default:
                return null;
        }
    }

    public String getType() {
        if (this instanceof Fire) {
            return "fire";
        } else if (this instanceof Water) {
            return "water";
        } else if (this instanceof Plant) {
            return "plant";
        } else if (this instanceof Energy) {
            return "energy";
        } else
            return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return buyValue == card.buyValue && sellValue == card.sellValue && Objects.equals(name, card.name);
    }
    public double shield() {
        if(this instanceof Plant) {
            return 15;
        } else return 0;
    }
}
