package net.ninjaworks.ninjamod.recipe;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ninjaworks.ninjamod.NinjaMod;

public class ModRecipes {

    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(NinjaMod.MODID, BoundaryBlockRecipe.Serializer.ID),
                BoundaryBlockRecipe.Serializer.INSTANCE);
    }
}