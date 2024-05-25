package model;

import java.util.ArrayList;

public class Game {
    private static Game inProgressGame;
    private final ArrayList<Player> players;
    private int round;
    private int onUser;
    private boolean isEnded;

    public Game(User loggedUser, User enemy) {
        this.players = new ArrayList<>();

        this.players.add(new Player(loggedUser, this));
        this.players.add(new Player(enemy, this));

    }

    public void startGame() {
        this.round = 0;
        this.onUser = 0;
        this.isEnded = false;
        inProgressGame = this;
    }

    public void endGame() {
        players.get(0).endGame(players.get(1));
        players.get(1).endGame(players.get(0));

        inProgressGame = null;
    }

    public static Game getInProgressGame() {
        return inProgressGame;
    }

    public int getRound() {
        return round;
    }

    public void nextRound(Player player, Player enemy) {
        checkForDeadPokemon();
        checkIsEnded();
        if (isEnded) {
            return;
        }
        updateHitPoints(player, enemy);
        setNewRound();

    }

    public void setNewRound() {

        onUser = (onUser == 0) ? 1 : 0;
        round++;
        players.get(0).setCanPlayEnergyCard(true);
        players.get(1).setCanPlayEnergyCard(true);
    }

    public Player getOnUser() {
        return players.get(onUser);
    }

    public Player getEnemy() {
        return players.get((onUser == 0) ? 1 : 0);
    }

    public boolean getIsEnded() {
        return this.isEnded;
    }

    public String getWinner() {
        return getEnemy().getPlayer().getUsername();
    }

    public void checkIsEnded() {
        if (getOnUser().isThisPlayerLost()) {
            isEnded = true;
        }
    }
    public void killPokemon(Player enemy, int index) {
        enemy.killPokemon(index , getOnUser());
    }
    public void checkForDeadPokemon() {
        for(int i = 0; i < 4; i++) {
            if(getOnUser().getOnGamePokemon(i).isDead())  {
                killPokemon(getOnUser(), i);
            }
            if(getEnemy().getOnGamePokemon(i).isDead()) {
                killPokemon(getEnemy(), i);
            }
        }
    }
    public boolean isPokemonDead(Pokemon card) {
        if (card == null) {
            return false;
        }
        return card.getHitPoint() <= 0;
    }
    public void updateHitPoints(Player player, Player enemy) {
        enemy.updateHitPoints();
        player.updateHitPoints();
        for(int i = 0; i < 4; i++) {
            player.getOnGamePokemon(i).Off();
        }
    }
    public void paralyze(int index, Player player) {
        Player enemy = (players.get(0) == player) ? players.get(1) : players.get(0);
        if(enemy.getOnGamePokemon(index) == null) {
            return;
        }
        for(int i = 0; i < player.getOnGamePokemon(index).getEnergySize(); i++) {
            enemy.increaseKill();
        }
        player.getOnGamePokemon(index).paralyze();
    }
    public void  removeEnergy(Player player, Player enemy) {
        if(player.getOnGamePokemon(0).getEnergySize() == 0) return;
        player.getOnGamePokemon(0).removeFirstEnergy();
        enemy.increaseKill();
    }
}
