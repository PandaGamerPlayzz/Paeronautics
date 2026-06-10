package dev.pandagamerplayzz.paeronautics.content.utils;

import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import static dev.pandagamerplayzz.paeronautics.Paeronautics.MOD_ID;
import static net.minecraft.core.registries.Registries.CREATIVE_MODE_TAB;

public record PaeronauticsRegistry(String modID) {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(CREATIVE_MODE_TAB, MOD_ID);

    public DeferredRegister<CreativeModeTab> creativeTab() {
        return CREATIVE_MODE_TABS;
    }

    public void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }
}
