package net.ninjaworks.ninjamod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ninjaworks.ninjamod.NinjaMod;
import net.ninjaworks.ninjamod.block.ModBlocks;
import team.reborn.energy.api.EnergyStorage;

public class ModBlockEntities {
    public static BlockEntityType<OperatorStationBlockEntity> OPERATOR_STATION;

    public static void registerBlockEntities() {
        OPERATOR_STATION = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(NinjaMod.MODID, "operator_station.json"),
                FabricBlockEntityTypeBuilder.create(OperatorStationBlockEntity::new,
                        ModBlocks.OPERATOR_STATION).build(null));

    EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.energyStorage, OPERATOR_STATION);
        FluidStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.fluidStorage, OPERATOR_STATION);
    }
}