package model;

public interface Pokemon {
    public double getHitPoint();
    public double getPower();
    public void setHitPoint(double hitPoint);
    public double getResistance();
    public double getWeakness(Card card);
    public void setPower(double power);
    public void action(Game game, int target);
    public double getMaxHitPoint();
}
