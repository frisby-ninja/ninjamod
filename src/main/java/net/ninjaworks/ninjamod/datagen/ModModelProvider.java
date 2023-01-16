package net.ninjaworks.ninjamod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.ninjaworks.ninjamod.block.ModBlocks;
import net.ninjaworks.ninjamod.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    //    blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.JUMPY_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
    //    itemModelGenerator.register(ModItems.EIGHT_BALL, Models.GENERATED);
    }
}