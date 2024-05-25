package model;


import java.util.ArrayList;

public class Player {
    private final Game game;
    private final User player;
    private final ArrayList<OnGamePokemon> onGamePokemon;
    private boolean canPlayEnergyCard;
   private int kill;
   private double reduce;

    public Player(User player, Game game) {
        this.player = player;
        this.game = game;
        this.onGamePokemon = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            onGamePokemon.add(new OnGamePokemon(null));
        }
        canPlayEnergyCard = true;
        kill = 0;
        reduce = 0;
    }



    public void setCanPlayEnergyCard(boolean canPlayEnergyCard) {
        this.canPlayEnergyCard = canPlayEnergyCard;
    }

    public void putPokemon(Pokemon pokemon, int index) {
        onGamePokemon.set(index ,new OnGamePokemon(pokemon));
    }


    public void addEnergy(Energy energy, int index) {
        onGamePokemon.get(index).setEnergies(energy);
    }

    public void increaseKill() {
        kill++;
    }
    public void addReduce(double reduce) {
        this.reduce += reduce;
    }

    public Game getGame() {
        return game;
    }

    public User getPlayer() {
        return player;
    }
   public OnGamePokemon getOnGamePokemon(int index) {
        return onGamePokemon.get(index);
    }

    public boolean canPlayEnergyCard() {
        return canPlayEnergyCard;
    }
    public void substitute(int index) {
        OnGamePokemon tempPokemon = onGamePokemon.get(0);
        onGamePokemon.set(0, onGamePokemon.get(index));
        onGamePokemon.set(index, tempPokemon);
    }
    public void updateHitPoints() {
        for (int i = 0; i < 4; i++) {
            if (onGamePokemon.get(i) != null) {
                onGamePokemon.get(i).updateHitPoints();
            }
        }
    }
    public void endGame(Player enemy) {
        for(int i = 0; i < 4; i++) {
            Pokemon pokemon = enemy.getOnGamePokemon(i).getPokemon();
            if(pokemon == null) continue;
            reduce += (pokemon.getMaxHitPoint() - pokemon.getHitPoint());
        }
        player.setCoins(player.getCoins() + (int)(reduce / 10));
        player.setExperience(player.getExperience() + kill * 10);
    }
    public boolean isThisPlayerLost() {
        return getOnGamePokemon(0).getPokemon() == null;
    }
    public void killPokemon(int index, Player player) {
        for(int i = 0; i < 1 + onGamePokemon.get(index).getEnergySize(); i++) {
            player.increaseKill();
        }
        player.addReduce(onGamePokemon.get(index).getPokemon().getMaxHitPoint());
        onGamePokemon.set(index, new OnGamePokemon(null));
    }
    public Card getCardInDeckByName(String name) {
         return  player.getCardInDeckByName(name);
    }
    public Pokemon getPokemon(int index) {
        return getOnGamePokemon(index).getPokemon();
    }
    public Energy getEnergy(int index, int energyIndex) {
        return getOnGamePokemon(index).getEnergy(energyIndex - 1);
    }
    public double getEffectOfEnergies(Pokemon pokemon) {
        return getOnGamePokemon(0).getEffectOfEnergies(pokemon);
    }
    public String getCondition(int index) {
        if (onGamePokemon.get(index).isBurning()) {
            return "burning";
        }
        if (onGamePokemon.get(index).isSleep()) {
            return "sleep";
        }
        return "";
    }
    public ArrayList<OnGamePokemon> getOnGamePokemon() {
        return onGamePokemon;
    }
}

