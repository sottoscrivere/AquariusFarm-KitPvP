package me.noskidding_.aquariusfarm.events;

import me.noskidding_.aquariusfarm.AquariusFarm;
import me.noskidding_.aquariusfarm.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SellGuiListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getClickedInventory() != null && e.getClickedInventory().getName() != null) {
            if (e.getClickedInventory().getName().equals(Config.SELL_GUI_NAME.getFormattedString()) && e.getClickedInventory().getSize() == Config.SELL_GUI_SLOTS.getInt()) {
                if (e.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE) || e.getCurrentItem().getType().equals(Material.COMPASS)) {
                    e.setCancelled(true);
                }
                if (e.getCurrentItem().getType().equals(Material.EMERALD)) {
                    e.setCancelled(true);
                    List<ItemStack> sellableBlocks = new ArrayList<>();
                    for (int i=18; i<=44; i++) {
                        ItemStack item = e.getClickedInventory().getItem(i);
                        if (item != null) {
                            if (item.getType().equals(Material.DIAMOND_ORE) || item.getType().equals(Material.EMERALD_ORE) || item.getType().equals(Material.GOLD_ORE) || item.getType().equals(Material.LAPIS_ORE) || item.getType().equals(Material.REDSTONE_ORE) || item.getType().equals(Material.LOG) || item.getType().equals(Material.SOUL_SAND) || item.getType().equals(Material.WHEAT)) {
                                sellableBlocks.add(item);
                            }else {
                                e.getWhoClicked().getInventory().addItem(item);
                                e.getClickedInventory().remove(item);
                            }
                        }

                    }
                    if (sellableBlocks.size() > 0) {
                        int amountToPay = 0;
                        for (ItemStack sb : sellableBlocks) {
                            amountToPay += (AquariusFarm.getInstance().getConfig().getInt("Minerals." + sb.getType().name() + ".moneyAmount") * sb.getAmount());
                        }
                        Player p = (Player) e.getWhoClicked();
                        p.closeInventory();
                        p.sendMessage(Config.SOLD_MESSAGE.getFormattedString().replaceAll("%player%", p.getName()).replaceAll("%moneyAmount%", String.valueOf(amountToPay)));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Config.GIVE_MONEY_COMMAND.getString().replaceAll("%player%", p.getName()).replaceAll("%moneyAmount%", String.valueOf(amountToPay)));

                    }

                }

            }
        }

    }
}
