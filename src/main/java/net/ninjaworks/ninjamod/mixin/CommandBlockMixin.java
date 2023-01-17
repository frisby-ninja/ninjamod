package net.ninjaworks.ninjamod.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.CommandBlock;
import net.minecraft.block.FacingBlock;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.ninjaworks.ninjamod.block.ModBlocks;
import net.ninjaworks.ninjamod.block.custom.OperatorStationBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandBlock.class)
public abstract class CommandBlockMixin {

    @Shadow @Final public static DirectionProperty FACING;

    @Shadow public abstract BlockState getPlacementState(ItemPlacementContext ctx);

    @Inject(method = "onPlaced", at = @At("HEAD"))
    protected void injectNinjaModOnPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
        if(world.getBlockState(pos.down(1)).getBlock().equals(ModBlocks.OPERATOR_STATION)) {
             {
                world.setBlockState(pos, ModBlocks.OPERATED_COMMAND_BLOCK.getDefaultState());
                OperatorStationBlock block = (OperatorStationBlock) world.getBlockState(pos.down(1)).getBlock();
                block.sync();
            }
        }
    }
}