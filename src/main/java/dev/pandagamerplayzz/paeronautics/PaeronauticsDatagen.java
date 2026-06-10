package dev.pandagamerplayzz.paeronautics;

import net.neoforged.neoforge.data.event.GatherDataEvent;

import static dev.pandagamerplayzz.paeronautics.Paeronautics.MOD_ID;

public class PaeronauticsDatagen {
    public static void gatherDataHighPriority(GatherDataEvent event) {
        if (!event.getMods().contains(MOD_ID)) return;
    }

    public static void gatherData(GatherDataEvent event) {
        if (!event.getMods().contains(MOD_ID)) return;
    }
}
