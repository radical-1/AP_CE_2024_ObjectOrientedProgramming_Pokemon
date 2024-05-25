package model;

public class Yellow extends Card implements Energy {
    public Yellow() {
        super("yellow", 5, 3);
    }

    @Override
    public double effectOnFire() {
        return 1;
    }

    @Override
    public double effectOnPlant() {
        return 1.2;
    }

    @Override
    public double effectOnWater() {
        return 1.2;
    }
}
