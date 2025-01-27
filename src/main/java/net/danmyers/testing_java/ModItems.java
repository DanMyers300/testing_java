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

public class ModItems {


    private static final List<String> ingredients = List.of(
        "suspicious_substance"
    );

    public static Item register(Item item, RegistryKey<Item> registryKey) {
        Item registeredItem = Registry.register(Registries.ITEM, registryKey.getValue(), item);
        return registeredItem;
    }
    
    public static final Map<String, RegistryKey<Item>> INGREDIENT_KEYS = new HashMap<>();
    public static final Map<String, Item> INGREDIENT_ITEMS = new HashMap<>();
    
    static {
        for (String ingredient : ingredients) {
            // Generate registry key
            String keyName = ingredient.toUpperCase(Locale.ROOT) + "_KEY";
            Identifier id = Identifier.of(TestingJava.MOD_ID, ingredient);
            RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
            INGREDIENT_KEYS.put(keyName, key);
            
            Item item = register(
                new Item(new Item.Settings().registryKey(key)),
                key
            );
            INGREDIENT_ITEMS.put(ingredient, item);
        }
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
            .register(entries -> {
                for (Item item : INGREDIENT_ITEMS.values()) {
                    entries.add(item);
                }
            });
    }
}
