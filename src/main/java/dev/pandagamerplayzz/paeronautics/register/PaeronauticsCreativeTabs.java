package dev.pandagamerplayzz.paeronautics.register;

import com.simibubi.create.AllCreativeModeTabs;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;

import static dev.pandagamerplayzz.paeronautics.Paeronautics.*;

public class PaeronauticsCreativeTabs {
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BASE_TAB =
            REGISTER.creativeTab().register("base_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatableWithFallback("itemGroup." + MOD_ID + ".base", NAME))
                    .withTabsBefore(AllCreativeModeTabs.PALETTES_CREATIVE_TAB.getKey())
                    .icon(PaeronauticsBlocks.BORE_BLOCK::asStack)
                    .build());

    public static void register() {
    }
}
