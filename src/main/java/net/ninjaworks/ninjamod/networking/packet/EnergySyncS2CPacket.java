package net.ninjaworks.ninjamod.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.ninjaworks.ninjamod.block.entity.OperatorStationBlockEntity;
import net.ninjaworks.ninjamod.screen.OperatorStationScreenHandler;

public class EnergySyncS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        long energy = buf.readLong();
        BlockPos position = buf.readBlockPos();

        if(client.world.getBlockEntity(position) instanceof OperatorStationBlockEntity blockEntity) {
            blockEntity.setEnergyLevel(energy);

            if(client.player.currentScreenHandler instanceof OperatorStationScreenHandler screenHandler &&
                    screenHandler.blockEntity.getPos().equals(position)) {
                blockEntity.setEnergyLevel(energy);
            }
        }
    }
}