package net.danmyers.testing_java;

import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.registry.*;

import java.util.List;
import java.util.Locale;
import java.util.HashMap;
import java.util.Map;

public class ModItems {
    public static Item register(Item item, RegistryKey<Item> registryKey) {
        Item registeredItem = Registry.register(Registries.ITEM, registryKey.getValue(), item);
        return registeredItem;
    }

    public static Item getItem(RegistryKey<Item> key) {
        Item item = ModItems.register(
            new Item(new Item.Settings().registryKey(key)),
            key
        );
        return item;
    };

    public static RegistryKey<Item> generateKey(String name) {
        Identifier id = Identifier.of(TestingJava.MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        return key;
    };

    public static void handleRegister(
        List<String> itemList,
        Map<String,RegistryKey<Item>> keyMap,
        Map<String, Item> valueMap)
    {
        for (String name : itemList) {
            String keyName = name.toUpperCase(Locale.ROOT) + "_KEY";
            keyMap.put(keyName, generateKey(name));
            
            Item item = getItem(generateKey(name));
            valueMap.put(name, item);
        }
    };

    public static void makeFuel(String itemName, int burnTime) {
        // Add the suspicious substance to the registry of fuels, with a burn time of 30 seconds.
        // Remember, Minecraft deals with logical based-time using ticks.
        // 20 ticks = 1 second.
        FuelRegistryEvents.BUILD.register((builder, context) -> {
        	builder.add(Ingredients.INGREDIENT_ITEMS.get(itemName), burnTime * 20);
        });
    };

    public static void initialize() {
        Ingredients.initialize();
        makeFuel("suspicious_substance", 30);
    }
}
