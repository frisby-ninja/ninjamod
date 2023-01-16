package net.ninjaworks.ninjamod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import net.ninjaworks.ninjamod.block.ModBlocks;
import net.ninjaworks.ninjamod.block.entity.ModBlockEntities;
import net.ninjaworks.ninjamod.block.entity.client.OperatorStationBlockEntityRenderer;
//import net.ninjaworks.ninjamod.entity.ModEntities;
//import net.ninjaworks.ninjamod.entity.client.ChomperRenderer;
import net.ninjaworks.ninjamod.event.KeyInputHandler;
import net.ninjaworks.ninjamod.fluid.ModFluids;
import net.ninjaworks.ninjamod.networking.ModMessages;
import net.ninjaworks.ninjamod.screen.ModScreenHandlers;
import net.ninjaworks.ninjamod.screen.OperatorStationScreen;

public class NinjaModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        KeyInputHandler.register();
        ModMessages.registerS2CPackets();

        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_SOAP_WATER, ModFluids.FLOWING_SOAP_WATER,
                new SimpleFluidRenderHandler(
                        new Identifier("minecraft:block/water_still"),
                        new Identifier("minecraft:block/water_flow"),
                        0xA1E038D0
                ));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                ModFluids.STILL_SOAP_WATER, ModFluids.FLOWING_SOAP_WATER);

        HandledScreens.register(ModScreenHandlers.OPERATOR_STATION_SCREEN_HANDLER, OperatorStationScreen::new);

        BlockEntityRendererRegistry.register(ModBlockEntities.OPERATOR_STATION, OperatorStationBlockEntityRenderer::new);

        //EntityRendererRegistry.register(ModEntities.CHOMPER, ChomperRenderer::new);
    }
}