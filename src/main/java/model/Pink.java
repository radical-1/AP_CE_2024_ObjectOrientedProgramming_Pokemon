package model;

public class Pink extends Card implements Energy {
    public Pink() {
        super("pink", 5, 3);
    }

    @Override
    public double effectOnFire() {
        return 1;
    }

    @Override
    public double effectOnPlant() {
        return 1.15 ;
    }

    @Override
    public double effectOnWater() {
        return 1.05 ;
    }
}
