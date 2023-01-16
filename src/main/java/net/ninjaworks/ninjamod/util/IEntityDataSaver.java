package net.ninjaworks.ninjamod.util;

import net.minecraft.nbt.NbtCompound;

public interface IEntityDataSaver {
    NbtCompound getPersistentNinjaModData();
}