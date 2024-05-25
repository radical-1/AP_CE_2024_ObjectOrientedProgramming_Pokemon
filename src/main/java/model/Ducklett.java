package model;

public class Ducklett extends Card implements Water {
    double hitPoint;
    double power;

    public Ducklett() {
        super("ducklett", 15, 11);
        this.hitPoint = 70;
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
        return 0.6;
    }

    @Override
    public double getWeakness(Card card) {
        if (card instanceof Plant) {
            return 1.5;
        } else
            return 1;
    }

    @Override
    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public double getMaxHitPoint() {
        return 70;
    }

    @Override
    public void action(Game game, int target) {
        Player player = game.getOnUser();
        Player enemy = game.getEnemy();
        Pokemon enemyPokemon = enemy.getPokemon(target);
        if(enemyPokemon == null) return;
        double damage = -1 * this.getPower() * enemyPokemon.getWeakness(this) *
                enemyPokemon.getResistance() * player.getEffectOfEnergies(this) + ((Card) enemyPokemon).shield();
        if (damage < 0)
            enemy.getPokemon(target).setHitPoint(enemy.getPokemon(target).getHitPoint() + damage);

        if (game.isPokemonDead(enemy.getPokemon(target))) {
            game.killPokemon(enemy, target);
        }
        game.paralyze(target, game.getEnemy());
    }
}
