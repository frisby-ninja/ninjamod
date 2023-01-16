package net.ninjaworks.ninjamod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ninjaworks.ninjamod.NinjaMod;
import net.ninjaworks.ninjamod.block.custom.OperatorStationBlock;
import net.ninjaworks.ninjamod.item.ModItemGroups;

public class ModBlocks {

    public static final Block OPERATOR_STATION = registerBlock("operator_station.json",
            new CommandBlock(AbstractBlock.Settings.copy(
                    new OperatorStationBlock(FabricBlockSettings.copy(Blocks.COMMAND_BLOCK),
                            false)), false), ModItemGroups.OPERATORS);

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(NinjaMod.MODID, name), block);
    }

    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registries.BLOCK, new Identifier(NinjaMod.MODID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        Item item = Registry.register(Registries.ITEM, new Identifier(NinjaMod.MODID, name),
                new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> entries.add(item));
        return item;
    }

    public static void registerModBlocks() {
        NinjaMod.LOGGER.debug("Registering ModBlocks for " + NinjaMod.MODID);
    }
}