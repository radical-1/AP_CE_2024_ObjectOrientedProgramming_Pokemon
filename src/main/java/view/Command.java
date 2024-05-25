package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Command {
    SHOW_CURRENT_MENU("show current menu"),
    BACK("back"),
    EXIT("exit"),
    LOGOUT("logout"),
    REGISTER_USER("register\\s+username\\s+(?<username>\\S+)\\s+password\\s+(?<password>\\S+)\\s+email\\s+(?<email>\\S+)"),
    VALID_USERNAME("[a-zA-Z_]+"),
    PASSWORD_LENGTH(".{6,18}"),
    PASSWORD_SPECIAL_CHARACTERS("(?=\\S*[@#$^&!])\\S*"),
    PASSWORD_STARTS_WITH_LETTER("(?=^[a-zA-Z])\\S+"),
    EMAIL_VALID_ENDING("\\S*@[a-z]+\\.com"),
    EMAIL_VALID_ADDRESS("[a-zA-Z0-9]+(\\.[a-zA-Z0-9]*)?@[a-z]+\\.com"),
    LOGIN_USER("login\\s+username\\s+(?<username>\\S+)\\s+password\\s+(?<password>\\S+)"),
    GO_TO_SHOP_MENU("go to shop menu"),
    GO_TO_PROFILE_MENU("go to profile menu"),
    START_NEW_GAME("start new game with (?<username>\\S+)"),
    SHOW_COINS("show coins"),
    SHOW_EXPERIENCE("show experience"),
    SHOW_STORAGE("show storage"),
    EQUIP_CARD_TO_MY_DECK("equip card (?<cardName>\\S+) to my deck"),
    UNEQUIP_CARD_FROM_MY_DECK("unequip card (?<cardName>\\S+) from my deck"),
    SHOW_MY_DECK("show my deck"),
    SHOW_MY_RANK("show my rank"),
    SHOW_RANKING("show ranking"),
    BUY_CARD("buy card (?<cardName>\\S+)"),
    SELL_CARD("sell card (?<cardName>\\S+)"),
    SHOW_TABLE("show table"),
    SHOW_MY_INFO("show my info (?<placeNumber>-?\\d+)"),
    SHOW_ENEMY_INFO("show enemy info (?<placeNumber>-?\\d+)"),
    VALID_PLACE("[0-3]"),
    PUT_CARD("put card (?<cardName>\\S+) to (?<placeNumber>-?\\d+)"),
    SUBSTITUTE_ACTIVE_CARD("substitute active card with bench (?<benchNumber>-?\\d+)"),
    VALID_BENCH("[1-3]"),
    END_TURN("end turn"),
    EXECUTE_ACTION_WITH_TARGET("execute action -t (?<target>-?\\d+)"),
    EXECUTE_ACTION_WITHOUT_TARGET("execute action"),

    ;
    final String regex;
    Command(String regex) {
        this.regex = regex;
    }
    private Matcher getMatcher(String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher  = pattern.matcher(input);
        matcher.matches();
        return matcher;
    }
    public boolean matches(String input) {
        return getMatcher(input).matches();
    }
    public String getGroup(String input, String group) {
        return getMatcher(input).group(group);
    }
}
