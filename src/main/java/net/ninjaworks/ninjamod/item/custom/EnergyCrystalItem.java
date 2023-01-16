package net.ninjaworks.ninjamod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.ninjaworks.api.EnergyItem;
import net.ninjaworks.api.IndexableMap;

import static net.minecraft.block.Material.AIR;

public class EnergyCrystalItem extends EnergyItem {

    private static final IndexableMap<Block, Item> blockToItem = new IndexableMap<>();
    private static final IndexableMap<Material, Item> materialToItem = new IndexableMap<>();

    private static Entity entityBeingEnergised;

    public EnergyCrystalItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState blockState = getBlockFromUsageContext(context);
        World world = context.getWorld();
        world.breakBlock(context.getBlockPos(), false);
        Item item2Spawn = getItemFromBlockState(blockState);

        world.spawnEntity(new ItemEntity(context.getWorld(), context.getBlockPos().getX(),
                context.getBlockPos().getY(), context.getBlockPos().getZ(), new ItemStack(item2Spawn)));
        return ActionResult.SUCCESS;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        user.damage(DamageSource.MAGIC, 1f);
        entityBeingEnergised = entity;
        return ActionResult.SUCCESS;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        entityBeingEnergised = null;
        return super.finishUsing(stack, world, user);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {

    }

    public static Entity getEntityBeingEnergised() {
        return entityBeingEnergised;
    }

    public static Item getItemFromBlockState(BlockState blockState) {
            if(blockToItem.isEmpty())
                genBlockToItem();

            if(materialToItem.isEmpty())
                genMaterialToItem();

            return blockToItem.getOrDefault(blockState.getBlock(), materialToItem.getOrDefault(
                    blockState.getMaterial(), Items.AIR));
    }

    public static BlockState getBlockFromUsageContext(ItemUsageContext context) {
        BlockPos position = context.getBlockPos();
        World world = context.getWorld();
        return world.getBlockState(position);
    }

    private static void genMaterialToItem() {
        materialToItem.put(AIR, Items.AIR);
    }

    private static void genBlockToItem() {
        blockToItem.put(Blocks.AIR, Items.AIR);
    }
}