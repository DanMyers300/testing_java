package net.danmyers.testing_java;

import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import java.util.List;
import java.util.Locale;
import java.util.HashMap;
import java.util.Map;

public class Ingredients {

    private static final List<String> ingredients = List.of(
        "suspicious_substance"
    );
    
    public static final Map<String, RegistryKey<Item>> INGREDIENT_KEYS = new HashMap<>();
    public static final Map<String, Item> INGREDIENT_ITEMS = new HashMap<>();

    public static void initialize() {

        ModItems.handleRegister(ingredients, INGREDIENT_KEYS, INGREDIENT_ITEMS);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
            .register(entries -> {
                for (Item item : INGREDIENT_ITEMS.values()) {
                    entries.add(item);
                }
            });
    }

}
