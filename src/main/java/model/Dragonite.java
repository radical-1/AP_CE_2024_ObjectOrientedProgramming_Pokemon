package model;

import java.util.ArrayList;

public class Dragonite extends Card implements Fire {
    private double hitPoint;
    private double power;

    public Dragonite() {
        super("dragonite", 10, 8);
        this.hitPoint = 120;
        this.power = 40;
    }

    @Override
    public double getHitPoint() {
        return hitPoint;
    }

    @Override
    public double getPower() {
        return power;
    }

    @Override
    public void setHitPoint(double hitPoint) {
        this.hitPoint = hitPoint;
    }

    @Override
    public double getResistance() {
        return 0.7;
    }

    @Override
    public double getWeakness(Card card) {
        if (card instanceof Water) {
            return 1.2;
        } else
            return 1;
    }

    @Override
    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public double getMaxHitPoint() {
        return 120;
    }

    @Override
    public void action(Game game, int target) {
        Player player = game.getOnUser();
        Player enemy = game.getEnemy();
        Pokemon enemyPokemon = enemy.getPokemon(0);
        if(enemyPokemon == null) return;
        double damage = -1 * this.getPower() * enemyPokemon.getWeakness(this) *
                enemyPokemon.getResistance() * player.getEffectOfEnergies(this) + ((Card) enemyPokemon).shield();
        if (damage < 0)
            enemy.getPokemon(0).setHitPoint(enemy.getPokemon(0).getHitPoint() + damage);

        if (game.isPokemonDead(enemy.getPokemon(0))) {
            game.killPokemon(enemy, 0);
        }
    }

    @Override
    public void setBurn(ArrayList<OnGamePokemon> pokemon) {
        pokemon.get(0).setBurning();
    }
}
