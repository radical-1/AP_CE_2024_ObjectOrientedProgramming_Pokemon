package model;

public class Rowlet extends Card implements Plant {
    double hitPoint;
    double power;

    public Rowlet() {
        super("rowlet", 12, 9);
        this.hitPoint = 180;
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
        return 0.5;
    }

    @Override
    public double getWeakness(Card card) {
        if (card instanceof Fire) {
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
        return 180;
    }

    @Override
    public void action(Game game, int target) {
        Player user = game.getOnUser();
        Pokemon targetCard = user.getPokemon(target);
        double heal = this.getPower() * user.getEffectOfEnergies(this) * targetCard.getWeakness(this) * targetCard.getResistance();
        targetCard.setHitPoint(targetCard.getHitPoint() + heal);
        if (targetCard.getHitPoint() > targetCard.getMaxHitPoint())
            targetCard.setHitPoint(targetCard.getMaxHitPoint());
    }
}
