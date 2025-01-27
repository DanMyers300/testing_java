package net.danmyers.testing_java;

import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import java.util.List;
import java.util.Locale;
import java.util.HashMap;
import java.util.Map;

public class ModItems {
    public static Item register(Item item, RegistryKey<Item> registryKey) {
        Item registeredItem = Registry.register(Registries.ITEM, registryKey.getValue(), item);
        return registeredItem;
    }

    public static void handleRegister(
        List<String> itemList,
        Map<String,RegistryKey<Item>> keyMap,
        Map<String, Item> valueMap)
    {
        for (String name : itemList) {
            // Generate registry key
            String keyName = name.toUpperCase(Locale.ROOT) + "_KEY";
            Identifier id = Identifier.of(TestingJava.MOD_ID, name);
            RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
            keyMap.put(keyName, key);
            
            Item item = ModItems.register(
                new Item(new Item.Settings().registryKey(key)),
                key
            );
            valueMap.put(name, item);
        }
    }

    public static void initialize() {
        Ingredients.initialize();
    }
}
