package baguchan.earthmobsmod.client;

import baguchan.earthmobsmod.EarthMobsMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static ModelLayerLocation CLUCK_SHROOM = register("cluck_shroom");
    public static ModelLayerLocation CLUCK_SHROOM_BABY = register("cluck_shroom_baby");
    public static ModelLayerLocation FANCY_CHICKEN = register("fancy_chicken");
    public static ModelLayerLocation FANCY_CHICKEN_BABY = register("fancy_chicken_baby");
    public static ModelLayerLocation HORNED_SHEEP = register("horned_sheep");
    public static ModelLayerLocation HORNED_SHEEP_FUR = register("horned_sheep_fur");
    public static ModelLayerLocation HYPER_RABBIT = register("hyper_rabbit");
    public static ModelLayerLocation JUMBO_RABBIT = register("jumbo_rabbit");

    public static ModelLayerLocation TEACUP_PIG = register("teacup_pig");
    public static ModelLayerLocation MUDDY_PIG = register("muddy_pig");
    public static ModelLayerLocation MUDDY_PIG_BABY = register("muddy_pig");
    public static ModelLayerLocation JOLLY_LLAMA = register("jolly_llama");


    public static ModelLayerLocation BONE_SPIDER = register("bone_spider");
    public static ModelLayerLocation STRAY_BONE_SPIDER = register("stray_bone_spider");
    public static ModelLayerLocation VILER_WITCH = register("viler_witch");


    public static ModelLayerLocation BOULDERING_ZOMBIE = register("bouldering_zombie");
    public static ModelLayerLocation LOBBER_ZOMBIE = register("lobber_zombie");
    public static ModelLayerLocation BOULDERING_ZOMBIE_BABY = register("bouldering_zombie_baby");
    public static ModelLayerLocation LOBBER_ZOMBIE_BABY = register("lobber_zombie_baby");

    public static ModelLayerLocation BOULDERING_DROWNED = register("bouldering_drowned");
    public static ModelLayerLocation LOBBER_DROWNED = register("lobber_drowned");
    public static ModelLayerLocation BOULDERING_DROWNED_BABY = register("bouldering_drowned_baby");
    public static ModelLayerLocation LOBBER_DROWNED_BABY = register("lobber_drowned_baby");

    public static ModelLayerLocation BOULDERING_DROWNED_OUTER = register("bouldering_drowned_outer");
    public static ModelLayerLocation LOBBER_DROWNED_OUTER = register("lobber_drowned_outer");
    public static ModelLayerLocation BOULDERING_DROWNED_OUTER_BABY = register("bouldering_drowned_outer_baby");
    public static ModelLayerLocation LOBBER_DROWNED_OUTER_BABY = register("lobber_drowned_outer_baby");
    public static ModelLayerLocation MAGMA_COW = register("magma_cow");
    public static ModelLayerLocation MAGMA_COW_GLOW = register("magma_cow_glow");
    public static ModelLayerLocation FURNACE_GOLEM = register("furnace_golem");


    private static ModelLayerLocation register(String p_171294_) {
        return register(p_171294_, "main");
    }

    private static ModelLayerLocation register(String p_171301_, String p_171302_) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EarthMobsMod.MODID, p_171301_), p_171302_);
    }
}
