package net.ninjaworks.ninjamod.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.ninjaworks.ninjamod.NinjaMod;

public class ModScreenHandlers {
    public static ScreenHandlerType<OperatorStationScreenHandler> OPERATOR_STATION_SCREEN_HANDLER =
            new ExtendedScreenHandlerType<>(OperatorStationScreenHandler::new);

    public static void registerAllScreenHandlers() {
        Registry.register(Registries.SCREEN_HANDLER, new Identifier(NinjaMod.MODID, "operator_station.json"),
            OPERATOR_STATION_SCREEN_HANDLER);
    }
}