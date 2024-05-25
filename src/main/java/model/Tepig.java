package model;

import java.util.ArrayList;

public class Tepig extends Card implements Fire {
    private double hitPoint;
    private double power;

    public Tepig() {
        super("tepig", 13, 10);
        this.hitPoint = 140;
        this.power = 25;
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
        return 0.8;
    }

    @Override
    public double getWeakness(Card card) {
        if (card instanceof Water) {
            return 2;
        } else if (card instanceof Plant) {
            return 1.3;
        } else
            return 1;
    }

    @Override
    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public double getMaxHitPoint() {
        return 140;
    }

    @Override
    public void action(Game game, int target) {
        Player user = game.getOnUser();
        Player enemy = game.getEnemy();
        if (enemy.getPokemon(0) != null) {
            double damage = -1 * this.getPower() * enemy.getPokemon(0).getWeakness(this) *
                    enemy.getPokemon(0).getResistance() * user.getEffectOfEnergies(this) + ((Card) enemy.getPokemon(0)).shield();

            if (damage < 0)
                enemy.getPokemon(0).setHitPoint(enemy.getPokemon(0).getHitPoint() + damage);
        }

        for (int i = 1; i < 4; i++) {
            if (enemy.getPokemon(i) == null) continue;
            double benchDamage = -0.2 * this.getPower() * enemy.getPokemon(i).getWeakness(this) *
                    enemy.getPokemon(i).getResistance() * user.getEffectOfEnergies(this) + ((Card) enemy.getPokemon(i)).shield();

            if (benchDamage < 0)
                enemy.getPokemon(i).setHitPoint(enemy.getPokemon(i).getHitPoint() + benchDamage);

        }
        for (int i = 0; i < 4; i++) {
            if (game.isPokemonDead(enemy.getPokemon(i))) {
                game.killPokemon(enemy, i);
            }
        }
    }

    @Override
    public void setBurn(ArrayList<OnGamePokemon> pokemon) {
        for(int i = 0; i < 4; i++) {
            pokemon.get(i).setBurning();
        }
    }
}
