package net.ninjaworks.ninjamod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.ninjaworks.ninjamod.block.ModBlocks;
import net.ninjaworks.ninjamod.block.entity.ModBlockEntities;
//import net.ninjaworks.ninjamod.entity.ModEntities;
//import net.ninjaworks.ninjamod.entity.custom.ChomperEntity;
import net.ninjaworks.ninjamod.entity.ModEntities;
import net.ninjaworks.ninjamod.entity.custom.ChomperEntity;
import net.ninjaworks.ninjamod.event.AttackEntityHandler;
import net.ninjaworks.ninjamod.event.PlayerTickHandler;
import net.ninjaworks.ninjamod.fluid.ModFluids;
import net.ninjaworks.ninjamod.item.ModItemGroups;
import net.ninjaworks.ninjamod.item.ModItems;
import net.ninjaworks.ninjamod.networking.ModMessages;
import net.ninjaworks.ninjamod.recipe.ModRecipes;
import net.ninjaworks.ninjamod.screen.ModScreenHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public class NinjaMod implements ModInitializer {
    public static final String MODID = "ninjamod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitialize() {
        ModItemGroups.registerItemGroup();

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

        ModMessages.registerC2SPackets();

        ModFluids.register();
        ModBlockEntities.registerBlockEntities();

        ModScreenHandlers.registerAllScreenHandlers();
        ModRecipes.registerRecipes();

        GeckoLib.initialize();

        FabricDefaultAttributeRegistry.register(ModEntities.CHOMPER, ChomperEntity.setAttributes());
        // VillageAdditions.registerNewVillageStructures();

        ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());
        AttackEntityCallback.EVENT.register(new AttackEntityHandler());
    }
}