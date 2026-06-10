package dev.pandagamerplayzz.paeronautics;

import com.mojang.logging.LogUtils;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import dev.pandagamerplayzz.paeronautics.content.utils.PaeronauticsRegistry;
import dev.pandagamerplayzz.paeronautics.register.PaeronauticsBlocks;
import dev.pandagamerplayzz.paeronautics.register.PaeronauticsCreativeTabs;
import dev.pandagamerplayzz.paeronautics.register.PaeronauticsTags;
import net.createmod.catnip.lang.FontHelper;
import net.createmod.catnip.lang.LangBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import static dev.pandagamerplayzz.paeronautics.register.PaeronauticsCreativeTabs.BASE_TAB;

@SuppressWarnings("unused")
@Mod(Paeronautics.MOD_ID)
public class Paeronautics {
    public static final String NAME = "Paeronautics";
    public static final String MOD_ID = "paeronautics";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static PaeronauticsRegistry REGISTER = new PaeronauticsRegistry(MOD_ID);
    public static CreateRegistrate REG = CreateRegistrate.create(MOD_ID);

    static {
        REG.setTooltipModifierFactory(item -> new ItemDescription.Modifier(item, FontHelper.Palette.STANDARD_CREATE)
                .andThen(TooltipModifier.mapNull(create(item))));
    }

    public Paeronautics(IEventBus modEventBus, ModContainer modContainer) {
        REGISTER.register(modEventBus);
        PaeronauticsCreativeTabs.register();

        REG.registerEventListeners(modEventBus);
        REG.defaultCreativeTab(BASE_TAB, "base_tab");

        PaeronauticsTags.init();
        PaeronauticsBlocks.register();

        modEventBus.addListener(EventPriority.HIGHEST, PaeronauticsDatagen::gatherDataHighPriority);
        modEventBus.addListener(EventPriority.LOWEST, PaeronauticsDatagen::gatherData);
    }

    public static LangBuilder lang() {
        return new LangBuilder(MOD_ID);
    }

    public static ResourceLocation loc(String loc) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, loc);
    }

    public static ResourceLocation emptyLoc() {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, "empty");
    }

    @Nullable
    public static KineticStats create(Item item) {
        if (item instanceof BlockItem blockItem && blockItem.getBlock() instanceof Block block && block instanceof IRotate) {
            return new KineticStats(block);
        }
        return null;
    }
}
