package net.ninjaworks.ninjamod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ninjaworks.ninjamod.NinjaMod;
import net.ninjaworks.ninjamod.entity.custom.ChomperEntity;

public class ModEntities {
    public static final EntityType<ChomperEntity> CHOMPER = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(NinjaMod.MODID, "chomper"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ChomperEntity::new)
                    .dimensions(EntityDimensions.fixed(0.4f, 1.5f)).build());

}