package me.noskidding_.aquariusfarm.utils;

import me.noskidding_.aquariusfarm.config.Config;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CustomTools {

    public static ItemStack getTool(Material material) {

        /*ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        Map<String, String> itemInfo = new HashMap<>();
        itemInfo = Config.valueOf(material.name()).getItemInfo();
        itemMeta.setDisplayName(itemInfo.get("name"));
        itemMeta.setLore(Arrays.asList(itemInfo.get("lore")));
        itemStack.setItemMeta(itemMeta);*/
        return Config.valueOf(material.name()).getItem().getItemStack();
    }
    public static boolean isPluginTool(ItemStack itemStack) {
        if (itemStack.getType().name().contains("_PICKAXE") || itemStack.getType().name().contains("_AXE") || itemStack.getType().name().contains("_SHOVEL") || itemStack.getType().name().contains("_HOE")) {
            ItemStack i = getTool(itemStack.getType());
            if (itemStack.getItemMeta().hasDisplayName() && itemStack.getItemMeta().hasLore() && itemStack.getItemMeta().getLore().equals(i.getItemMeta().getLore()) && itemStack.getItemMeta().getDisplayName().equals(i.getItemMeta().getDisplayName())) {
                return true;
            }
        }

        return false;
    }





}
