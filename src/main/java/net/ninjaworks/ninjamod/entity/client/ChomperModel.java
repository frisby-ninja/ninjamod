package net.ninjaworks.ninjamod.entity.client;

import net.minecraft.util.Identifier;
import net.ninjaworks.ninjamod.NinjaMod;
import net.ninjaworks.ninjamod.entity.custom.ChomperEntity;
import software.bernie.geckolib.model.GeoModel;

public class ChomperModel extends GeoModel<ChomperEntity> {
    @Override
    public Identifier getModelResource(ChomperEntity object) {
        return new Identifier(NinjaMod.MODID, "geo/chomper.geo.json");
    }

    @Override
    public Identifier getTextureResource(ChomperEntity object) {
        return new Identifier(NinjaMod.MODID, "textures/entity/chomper_texture.png");
    }

    @Override
    public Identifier getAnimationResource(ChomperEntity animatable) {
        return new Identifier(NinjaMod.MODID, "animations/chomper.animation.json");
    }
}