package net.ninjaworks.api;

import net.minecraft.item.Item;

public class EnergyItem extends Item {

    private final int energy;

    public EnergyItem(Settings normalSettings) {
        super(normalSettings);
        this.energy = 1;
    }

    public EnergyItem(Settings normalSettings, int energy) {
        super(normalSettings);
        this.energy = energy;
    }

    public int getEnergy() {
        return this.energy;
    }
}