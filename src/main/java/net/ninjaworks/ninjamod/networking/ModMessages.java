package net.ninjaworks.ninjamod.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;
import net.ninjaworks.ninjamod.NinjaMod;
import net.ninjaworks.ninjamod.networking.packet.EnergySyncS2CPacket;
import net.ninjaworks.ninjamod.networking.packet.FluidSyncS2CPacket;
import net.ninjaworks.ninjamod.networking.packet.ItemStackSyncS2CPacket;

public class ModMessages {

    public static final Identifier ENERGY_SYNC = new Identifier(NinjaMod.MODID, "energy_sync");
    public static final Identifier FLUID_SYNC = new Identifier(NinjaMod.MODID, "fluid_sync");
    public static final Identifier ITEM_SYNC = new Identifier(NinjaMod.MODID, "item_sync");

    public static void registerC2SPackets() {

    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(ENERGY_SYNC, EnergySyncS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(FLUID_SYNC, FluidSyncS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(ITEM_SYNC, ItemStackSyncS2CPacket::receive);
    }
}