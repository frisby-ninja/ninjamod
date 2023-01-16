package net.ninjaworks.ninjamod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.ninjaworks.ninjamod.NinjaMod;
import net.ninjaworks.ninjamod.block.ModBlocks;

public class ModItemGroups {
    public static ItemGroup TOOLS;
    public static ItemGroup OPERATORS;

    public static void registerItemGroup() {
        OPERATORS = FabricItemGroup.builder(new Identifier(NinjaMod.MODID, "operators"))
                .displayName(Text.literal("Operating Item Group"))
                .icon(() -> new ItemStack(ModBlocks.OPERATOR_STATION.asItem())).build();

        TOOLS = FabricItemGroup.builder(new Identifier(NinjaMod.MODID, "tools"))
            .displayName(Text.literal("Tool Item Group"))
            .icon(() -> new ItemStack(ModItems.ENERGY_CRYSTAL)).build();
    }
}