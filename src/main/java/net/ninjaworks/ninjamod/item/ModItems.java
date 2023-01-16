package net.ninjaworks.ninjamod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.ninjaworks.api.IndexableMap;
import net.ninjaworks.ninjamod.NinjaMod;
//import net.ninjaworks.ninjamod.entity.ModEntities;
import net.ninjaworks.ninjamod.entity.ModEntities;
import net.ninjaworks.ninjamod.item.custom.EnergyCrystalItem;

public class ModItems {

    public static IndexableMap<Item, ItemGroup> itemGroupMap = new IndexableMap<>();

    //public static final Item RAW_TANZANITE = registerItem("raw_tanzanite",
    //        new Item(new FabricItemSettings()));
    //public static final Item TANZANITE = registerItem("tanzanite",
    //        new Item(new FabricItemSettings()));
    public static final Item ENERGY_CRYSTAL = registerItem("energy_crystal",
            new EnergyCrystalItem(new FabricItemSettings().maxCount(16).fireproof()
                    .rarity(Rarity.RARE)), ModItemGroups.TOOLS);

    //public static final Item EIGHT_BALL = registerItem("eight_ball",
    //        new EightBallItem(new FabricItemSettings().maxCount(1)));
    //public static final Item EGGPLANT_SEEDS = registerItem("eggplant_seeds",
    //        new AliasedBlockItem(ModBlocks.EGGPLANT_CROP,
    //                new FabricItemSettings()));
    //public static final Item EGGPLANT = registerItem("eggplant",
    //        new Item(new FabricItemSettings()
    //                .food(new FoodComponent.Builder().hunger(4).saturationModifier(4f).build())));

    //public static final Item KAUPENSWORD = registerItem("kaupensword",
    //        new SwordItem(ToolMaterials.DIAMOND, 10, 5f,
    //                new FabricItemSettings().maxCount(1)));

    public static final Item CHOMPER_SPAWN_EGG = registerItem("chomper_spawn_egg",
            new SpawnEggItem(ModEntities.CHOMPER,0x22b341, 0x19732e,
                    new FabricItemSettings()));

    //public static final Item TANZANITE_PICKAXE = registerItem("tanzanite_pickaxe",
    //        new PickaxeItem(ModToolMaterial.TANZANITE, 4, 2f,
    //                new FabricItemSettings().maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(NinjaMod.MODID, name), item);
    }

    private static Item registerItem(String name,Item item, ItemGroup group) {
        Item registeredItem =
                Registry.register(Registries.ITEM, new Identifier(
                        NinjaMod.MODID, name), item);
        itemGroupMap.put(item, group);
        return registeredItem;
    }

    public static void addItemsToItemGroups() {
        for (int i = 0; i < itemGroupMap.size(); i++) {
            addToItemGroup(itemGroupMap.getValueAt(i), itemGroupMap.getKeyAt(i));
        }
    }

    public static void addToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    public static void registerModItems() {
        NinjaMod.LOGGER.debug("Registering Mod Items for " + NinjaMod.MODID);
        addItemsToItemGroups();
    }
}