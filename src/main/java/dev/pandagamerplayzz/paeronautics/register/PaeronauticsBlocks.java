package dev.pandagamerplayzz.paeronautics.register;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.foundation.block.DyedBlockList;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import dev.pandagamerplayzz.paeronautics.Paeronautics;
import dev.pandagamerplayzz.paeronautics.content.blocks.logistics.bore_block.BoreBlock;
import dev.pandagamerplayzz.paeronautics.content.blocks.logistics.bore_block.BoreBlockMovementBehavior;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.common.util.DeferredSoundType;

import static com.simibubi.create.api.behaviour.movement.MovementBehaviour.movementBehaviour;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static com.tterrag.registrate.providers.RegistrateRecipeProvider.has;
import static dev.pandagamerplayzz.paeronautics.Paeronautics.REG;

@SuppressWarnings("unused")
public class PaeronauticsBlocks {
    public static final TagKey<Item> BORE_BLOCKS = PaeronauticsTags.modItemTag("bore_blocks");

    public static final BlockEntry<BoreBlock> BORE_BLOCK = REG.block("bore_block", BoreBlock::new)
            .initialProperties(SharedProperties::netheriteMetal)
            .properties(p -> p.mapColor(MapColor.STONE).sound(new DeferredSoundType(0.9f, 1.25f,
                    () -> SoundEvents.NETHERITE_BLOCK_BREAK,
                    () -> SoundEvents.NETHERITE_BLOCK_STEP,
                    () -> SoundEvents.NETHERITE_BLOCK_PLACE,
                    () -> SoundEvents.NETHERITE_BLOCK_HIT,
                    () -> SoundEvents.NETHERITE_BLOCK_FALL)))
            .onRegister(movementBehaviour(new BoreBlockMovementBehavior()))
            .transform(pickaxeOnly())
            .recipe((c, p) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, c.get(), 4)
                    .pattern("AIA")
                    .pattern("ICI")
                    .pattern("AIA")
                    .define('A', AllItems.ANDESITE_ALLOY.get())
                    .define('C', AllBlocks.ANDESITE_ALLOY_BLOCK.get())
                    .define('I', Items.IRON_INGOT)
                    .unlockedBy("has_" + c.getName(), has(c.get()))
                    .save(p, Paeronautics.loc("crafting/" + c.getName())))
            .blockstate((c, p) -> {
                var model = p.cubeAll(c.get());
                p.simpleBlockWithItem(c.get(), model);
            })
            .item()
            .tag(BORE_BLOCKS)
            .build()
            .register();

    public static final DyedBlockList<BoreBlock> DYED_BORE_BLOCK = new DyedBlockList<>(color -> {
        String colorName = color.getSerializedName();
        return REG.block(colorName + "_bore_block", BoreBlock::new)
                .initialProperties(SharedProperties::netheriteMetal)
                .properties(p -> p.mapColor(color.getMapColor()).sound(new DeferredSoundType(0.9f, 1.25f,
                        () -> SoundEvents.NETHERITE_BLOCK_BREAK,
                        () -> SoundEvents.NETHERITE_BLOCK_STEP,
                        () -> SoundEvents.NETHERITE_BLOCK_PLACE,
                        () -> SoundEvents.NETHERITE_BLOCK_HIT,
                        () -> SoundEvents.NETHERITE_BLOCK_FALL)))
                .onRegister(movementBehaviour(new BoreBlockMovementBehavior()))
                .transform(pickaxeOnly())
                .recipe((c, p) -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, c.get(), 8)
                        .pattern("AAA")
                        .pattern("ABA")
                        .pattern("AAA")
                        .define('A', BORE_BLOCKS)
                        .define('B', color.getTag())
                        .unlockedBy("has_" + c.getName(), has(c.get()))
                        .save(p, Paeronautics.loc("crafting/" + c.getName())))
                .tag(PaeronauticsTags.BlockTags.DYED_BLOCKS.tag)
                .blockstate((c, p) -> {
                    var model = p.models().withExistingParent(colorName + "_bore_block", p.modLoc("block/bore_block"))
                            .texture("all", p.modLoc("block/bore_block/" + colorName));
                    p.simpleBlockWithItem(c.get(), model);
                })
                .item()
                .tag(PaeronauticsTags.ItemTags.DYED_BLOCKS.tag, BORE_BLOCKS)
                .build()
                .register();
    });

    public static void register() {
    }
}
