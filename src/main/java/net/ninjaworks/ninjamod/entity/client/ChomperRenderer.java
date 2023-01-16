package net.ninjaworks.ninjamod.entity.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.ninjaworks.ninjamod.NinjaMod;
import net.ninjaworks.ninjamod.entity.custom.ChomperEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ChomperRenderer extends GeoEntityRenderer<ChomperEntity> {
    public ChomperRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new ChomperModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureLocation(ChomperEntity instance) {
        return new Identifier(NinjaMod.MODID, "textures/entity/chomper_texture.png");
    }


    @Override
    public RenderLayer getRenderType(ChomperEntity animatable, Identifier texture, @Nullable VertexConsumerProvider bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }
}