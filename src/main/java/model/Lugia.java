package model;

public class Lugia extends Card implements Water {
    double hitPoint;
    double power;
    public Lugia() {
        super("lugia", 11, 9);
        this.hitPoint = 90;
        this.power = 20;
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
        if(card instanceof Fire) {
            return 1.3;
        } else
            return 1;
    }

    @Override
    public double getMaxHitPoint() {
        return 90;
    }
    @Override
    public void setPower(double power) {
        this.power = power;
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
    public void setSleep(OnGamePokemon pokemon) {
        pokemon.setSleep();
    }
}
