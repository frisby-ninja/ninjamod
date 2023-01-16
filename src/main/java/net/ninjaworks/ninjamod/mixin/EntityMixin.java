package net.ninjaworks.ninjamod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.ninjaworks.ninjamod.item.custom.EnergyCrystalItem;
import net.ninjaworks.ninjamod.util.mixin.IEntityTargetDuck;
import net.ninjaworks.ninjamod.util.IEntityDataSaver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements IEntityDataSaver,
        IEntityTargetDuck {

    private NbtCompound persistentData;
    private float energy;
    private boolean isBeingEnergised;
    private boolean isEnergised;

    @Override
    public NbtCompound getPersistentNinjaModData() {
        if(this.persistentData == null) {
            this.persistentData = new NbtCompound();
        }
        return persistentData;
    }

    public float getEnergy() {
        return this.energy;
    }

    @Override
    @SuppressWarnings("all")
    public boolean isBeingNinjaModEnergised() {
        return ((Entity)(Object)this).equals(EnergyCrystalItem
                .getEntityBeingEnergised());
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    protected void injectEntityNinjaModMethod(EntityType<?> type,
                                             World world, CallbackInfo ci) {
        this.energy = 0f;
        this.isBeingEnergised = false;
        this.isEnergised = false;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    @SuppressWarnings("all")
    protected void injectNinjaModTickMethod(CallbackInfo ci) {
        assert EnergyCrystalItem.getEntityBeingEnergised() != null;
        isBeingEnergised = EnergyCrystalItem.getEntityBeingEnergised()
                .equals(this);
        if(isBeingEnergised) {
            this.energy = energy + 0.1f;
            this.isEnergised = true;
        } else {
            this.isEnergised = energy >= 0;
        }
    }

    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectNinjaModWriteMethod(NbtCompound nbt,
                                     CallbackInfoReturnable<NbtCompound> cir) {
        if(persistentData != null) {
            nbt.put("ninjamod.ninja_data", persistentData);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void injectNinjaModReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("ninjamod.ninja_data", 10)) {
            persistentData = nbt.getCompound("ninjamod.ninja_data");
        }
    }
}