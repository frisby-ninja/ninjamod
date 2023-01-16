package net.ninjaworks.ninjamod.block.custom;

import com.mojang.logging.LogUtils;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.CommandBlockBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.CommandBlockExecutor;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public class OperatorStationBlock extends BlockWithEntity implements OperatorBlock {
    public static final DirectionProperty FACING;
    public static final BooleanProperty CONDITIONAL;
    private final boolean auto;

    public OperatorStationBlock(AbstractBlock.Settings settings, boolean auto) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(CONDITIONAL, false));
        this.auto = auto;
    }

    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        CommandBlockBlockEntity commandBlockBlockEntity = new CommandBlockBlockEntity(pos, state);
        commandBlockBlockEntity.setAuto(this.auto);
        return commandBlockBlockEntity;
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof CommandBlockBlockEntity commandBlockBlockEntity) {
                boolean bl = world.isReceivingRedstonePower(pos);
                boolean bl2 = commandBlockBlockEntity.isPowered();
                commandBlockBlockEntity.setPowered(bl);
                if (!bl2 && !commandBlockBlockEntity.isAuto() && commandBlockBlockEntity.getCommandBlockType() != CommandBlockBlockEntity.Type.SEQUENCE) {
                    if (bl) {
                        commandBlockBlockEntity.updateConditionMet();
                        world.scheduleBlockTick(pos, this, 1);
                    }

                }
            }
        }
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof CommandBlockBlockEntity commandBlockBlockEntity) {
            CommandBlockExecutor commandBlockExecutor = commandBlockBlockEntity.getCommandExecutor();
            boolean bl = !StringHelper.isEmpty(commandBlockExecutor.getCommand());
            CommandBlockBlockEntity.Type type = commandBlockBlockEntity.getCommandBlockType();
            boolean bl2 = commandBlockBlockEntity.isConditionMet();
            if (type == CommandBlockBlockEntity.Type.AUTO) {
                commandBlockBlockEntity.updateConditionMet();
                if (bl2) {
                    this.execute(state, world, pos, commandBlockExecutor, bl);
                } else if (commandBlockBlockEntity.isConditionalCommandBlock()) {
                    commandBlockExecutor.setSuccessCount(0);
                }

                if (commandBlockBlockEntity.isPowered() || commandBlockBlockEntity.isAuto()) {
                    world.scheduleBlockTick(pos, this, 1);
                }
            } else if (type == CommandBlockBlockEntity.Type.REDSTONE) {
                if (bl2) {
                    this.execute(state, world, pos, commandBlockExecutor, bl);
                } else if (commandBlockBlockEntity.isConditionalCommandBlock()) {
                    commandBlockExecutor.setSuccessCount(0);
                }
            }

            world.updateComparators(pos, this);
        }

    }

    private void execute(BlockState state, World world, BlockPos pos, CommandBlockExecutor executor, boolean hasCommand) {
        if (hasCommand) {
            executor.execute(world);
        } else {
            executor.setSuccessCount(0);
        }

        executeCommandChain(world, pos, (Direction)state.get(FACING));
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof CommandBlockBlockEntity && player.isCreativeLevelTwoOp()) {
            player.openCommandBlockScreen((CommandBlockBlockEntity)blockEntity);
            return ActionResult.success(world.isClient);
        } else {
            return ActionResult.PASS;
        }
    }

    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity instanceof CommandBlockBlockEntity ? ((CommandBlockBlockEntity)blockEntity).getCommandExecutor().getSuccessCount() : 0;
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof CommandBlockBlockEntity commandBlockBlockEntity) {
            CommandBlockExecutor commandBlockExecutor = commandBlockBlockEntity.getCommandExecutor();
            if (itemStack.hasCustomName()) {
                commandBlockExecutor.setCustomName(itemStack.getName());
            }

            if (!world.isClient) {
                if (BlockItem.getBlockEntityNbt(itemStack) == null) {
                    commandBlockExecutor.setTrackOutput(world.getGameRules().getBoolean(GameRules.SEND_COMMAND_FEEDBACK));
                    commandBlockBlockEntity.setAuto(this.auto);
                }

                if (commandBlockBlockEntity.getCommandBlockType() == CommandBlockBlockEntity.Type.SEQUENCE) {
                    boolean bl = world.isReceivingRedstonePower(pos);
                    commandBlockBlockEntity.setPowered(bl);
                }
            }

        }
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, CONDITIONAL);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite());
    }

    private static void executeCommandChain(World var0, BlockPos var1, Direction var2) {
        // $FF: Couldn't be decompiled
    }

    static {
        FACING = FacingBlock.FACING;
        CONDITIONAL = Properties.CONDITIONAL;
    }
}