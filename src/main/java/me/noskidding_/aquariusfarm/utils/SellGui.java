package me.noskidding_.aquariusfarm.utils;

import me.noskidding_.aquariusfarm.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SellGui {

    public static void openGui(Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, Config.SELL_GUI_NAME.getFormattedString());
        ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemStack.setDurability((short) 7);
        for (int i = 0; i<=17; i++) {
            inv.setItem(i, itemStack);
        }
        itemStack.setDurability((short) 15);
        for (int i = 45; i<54; i++) {
            inv.setItem(i, itemStack);
        }
        itemStack = new ItemStack(Material.COMPASS);
        itemMeta.setDisplayName(Config.SELL_GUI_COMPASS_NAME.getFormattedString());
        itemMeta.setLore(Config.SELL_GUI_COMPASS_LORE.getFormattedStringList());
        itemStack.setItemMeta(itemMeta);
        inv.setItem(4, itemStack);
        itemStack = new ItemStack(Material.EMERALD);
        itemMeta.setDisplayName(Config.SELL_GUI_EMERALD_NAME.getFormattedString());
        itemMeta.setLore(Config.SELL_GUI_EMERALD_LORE.getFormattedStringList());
        itemStack.setItemMeta(itemMeta);
        inv.setItem(49, itemStack);

        p.openInventory(inv);

    }

}
