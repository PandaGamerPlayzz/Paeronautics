package dev.pandagamerplayzz.paeronautics.register;

import dev.pandagamerplayzz.paeronautics.Paeronautics;
import net.createmod.catnip.lang.Lang;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static dev.pandagamerplayzz.paeronautics.Paeronautics.MOD_ID;

@SuppressWarnings("unused")
public class PaeronauticsTags {
    public static <T> TagKey<T> optionalTag(Registry<T> registry, ResourceLocation id) {
        return TagKey.create(registry.key(), id);
    }

    public static <T> TagKey<T> commonTag(Registry<T> registry, String path) {
        return optionalTag(registry, ResourceLocation.fromNamespaceAndPath("c", path));
    }

    public static <T> TagKey<T> modTag(Registry<T> registry, String path) {
        return optionalTag(registry, Paeronautics.loc(path));
    }

    public static TagKey<Item> commonItemTag(String path) {
        return commonTag(BuiltInRegistries.ITEM, path);
    }

    public static TagKey<Item> modItemTag(String path) {
        return modTag(BuiltInRegistries.ITEM, path);
    }

    public enum NameSpace {
        MOD(MOD_ID, false, true);

        public final String id;
        public final boolean optionalDefault;
        public final boolean alwaysDatagenDefault;

        NameSpace(String id, boolean optionalDefault, boolean alwaysDatagenDefault) {
            this.id = id;
            this.optionalDefault = optionalDefault;
            this.alwaysDatagenDefault = alwaysDatagenDefault;
        }
    }

    public enum BlockTags {
        DYED_BLOCKS;

        public final TagKey<Block> tag;
        public final boolean alwaysDatagen;

        BlockTags() {
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(NameSpace.MOD.id, Lang.asId(name()));
            tag = net.minecraft.tags.BlockTags.create(id);
            alwaysDatagen = NameSpace.MOD.alwaysDatagenDefault;
        }

        private static void init() {
        }
    }

    public enum ItemTags {
        DYED_BLOCKS;

        public final TagKey<Item> tag;
        public final boolean alwaysDatagen;

        ItemTags() {
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(NameSpace.MOD.id, Lang.asId(name()));
            tag = net.minecraft.tags.ItemTags.create(id);
            alwaysDatagen = NameSpace.MOD.alwaysDatagenDefault;
        }

        private static void init() {
        }
    }

    public static void init() {
        BlockTags.init();
        ItemTags.init();
    }
}
