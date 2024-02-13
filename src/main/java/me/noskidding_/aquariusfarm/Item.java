package me.noskidding_.aquariusfarm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@AllArgsConstructor
public class Item {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private List<String> lore;

    @Getter
    private Material material;

    public void giveItemToPlayer(Player p) {
        p.getInventory().addItem(getItemStack());
    }
    public ItemStack getItemStack() {
        ItemStack stack = new ItemStack(material);
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        stack.setItemMeta(itemMeta);
        return stack;
    }



}
