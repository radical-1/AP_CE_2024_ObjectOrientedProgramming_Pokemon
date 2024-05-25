package model;

import java.util.ArrayList;

public class OnGamePokemon {
    private Pokemon pokemon;
    private ArrayList<Energy> energies;

    private boolean isBurning;
    private boolean isSleep;


    public OnGamePokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
        this.energies = new ArrayList<>();
        this.isBurning = false;
        this.isSleep = false;

    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setEnergies(Energy energy) {
        energies.add(energy);
    }

    public boolean canAddEnergy() {
        return energies.size() < 2;
    }

    public boolean isBurning() {
        return isBurning;
    }

    public void setBurning() {
        if(pokemon == null) {
            return;
        }
        if(pokemon instanceof Water) {
            return;
        }
        isBurning = true;
    }

    public boolean isSleep() {
        return isSleep;
    }

    public void setSleep() {
        isSleep = true;
    }

    public boolean isDead() {
        if(pokemon == null) {
            return false;
        }
        return pokemon.getHitPoint() <= 0;
    }

    public void paralyze() {
        energies.clear();
    }

    public void updateHitPoints() {
        if(this.pokemon == null) {
            return;
        }
        if (isBurning && pokemon instanceof Fire) {
            pokemon.setHitPoint(pokemon.getHitPoint() - 10);
        }
        if (pokemon.getHitPoint() > pokemon.getMaxHitPoint()) {
            pokemon.setHitPoint(pokemon.getMaxHitPoint());
        }
    }

    public Energy getEnergy(int index) {
        if (index >= energies.size()) {
            return null;
        }
        return energies.get(index);
    }

    public double getEffectOfEnergies(Pokemon pokemon) {
        double effect = 1;
        for (Energy energy : energies) {
            if (pokemon instanceof Fire) {
                effect *= energy.effectOnFire();
            } else if (pokemon instanceof Water) {
                effect *= energy.effectOnWater();
            } else if (pokemon instanceof Plant) {
                effect *= energy.effectOnPlant();
            }
        }
        return effect;
    }

    public String nameOfEnergy(int index) {
        if (index > energies.size()) {
            return "";
        }
        return ((Card) energies.get(index - 1)).getName();
    }

    public void kill() {
        pokemon = null;
        paralyze();
        Off();
    }
    public int getEnergySize() {
        return energies.size();
    }
    public void Off() {
        isBurning = false;
        isSleep = false;
    }
    public void removeFirstEnergy() {
        energies.remove(energies.get(0));
    }
}
