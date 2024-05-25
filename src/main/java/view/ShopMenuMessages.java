package view;

public class ShopMenuMessages {
    public static void currentMenu() {
        System.out.println("shop menu");
    }

    public static void invalidCommand() {
        System.out.println("invalid command");
    }

    public static void cardNameIsInvalid() {
        System.out.println("card name is invalid");
    }

    public static void notEnoughCoinToBuy(String cardName) {
        System.out.println("not enough coin to buy " + cardName);
    }

    public static void cardBoughtSuccessfully(String cardName) {
        System.out.println("card " + cardName + " bought successfully");
    }

    public static void doNotHaveThisTypeOfCardForSell() {
        System.out.println("you don't have this type of card for sell");
    }

    public static void cardSoldSuccessfully(String cardName) {
        System.out.println("card " + cardName + " sold successfully");
    }
}
