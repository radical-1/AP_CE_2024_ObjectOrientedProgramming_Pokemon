package model;

public class Pineco extends Card implements Plant {
    double hitPoint;
    double power;

    public Pineco() {
        super("pineco", 9, 7);
        this.hitPoint = 110;
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
        return 0.9;
    }

    @Override
    public double getWeakness(Card card) {
        return 1;
    }

    @Override
    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public void action(Game game, int target) {
        Player player = game.getOnUser();

        double heal = this.getPower() * player.getEffectOfEnergies(this) * this.getWeakness(this) * this.getResistance();
        player.getPokemon(0).setHitPoint(player.getPokemon(0).getHitPoint() + heal);
        if (player.getPokemon(0).getHitPoint() > player.getPokemon(0).getMaxHitPoint())
            player.getPokemon(0).setHitPoint(player.getPokemon(0).getMaxHitPoint());
    }

    @Override
    public double getMaxHitPoint() {
        return 110;
    }
}


