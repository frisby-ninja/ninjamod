package net.ninjaworks.ninjamod.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.ninjaworks.ninjamod.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

public class OperatedCommandBlock extends SkullBlock implements SkullBlock.SkullType {

    private static boolean onOperatingTable;

    public OperatedCommandBlock(Settings settings) {
        super(extendedSkullType.OPERATED_COMMAND_BLOCK, settings);
    }

    public static boolean isOnOperatingTable() {
        return onOperatingTable;
    }

    @SuppressWarnings("all")
    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        super.onBlockAdded(state, world, pos, oldState, notify);
        Block underBlock = world.getBlockState(pos.down(1)).getBlock();
        onOperatingTable = underBlock.equals(ModBlocks.OPERATOR_STATION);
        if(!onOperatingTable) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }

    enum extendedSkullType implements SkullType {
        OPERATED_COMMAND_BLOCK;
    }
}