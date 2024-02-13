package me.noskidding_.aquariusfarm.config;

import me.noskidding_.aquariusfarm.AquariusFarm;
import me.noskidding_.aquariusfarm.Item;
import org.bukkit.Material;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public enum Config {
    IRON_PICKAXE("Tools.IRON_PICKAXE"),
    DIAMOND_PICKAXE("Tools.DIAMOND_PICKAXE"),
    IRON_AXE("Tools.IRON_AXE"),
    DIAMOND_AXE("Tools.DIAMOND_AXE"),
    IRON_SHOVEL("Tools.IRON_SHOVEL"),
    DIAMOND_SHOVEL("Tools.DIAMOND_SHOVEL"),
    IRON_HOE("Tools.DIAMOND_HOE"),
    DIAMOND_HOE("Tools.DIAMOND_HOE"),
    MINES_WORLD("minesWorld"),
    PICKAXE_MINES_REG_TIME("Mines.pickaxeMines.regenerationTime"),
    AXE_MINES_REG_TIME("Mines.axeMines.regenerationTime"),
    SHOVEL_MINES_REG_TIME("Mines.shovelMines.regenerationTime"),
    HOE_MINES_REG_TIME("Mines.hoeMines.regenerationTime"),
    PICKAXE_VIP_REG_TIME("Mines.pickaxeVipMines.regenerationTime"),
    AXE_VIP_REG_TIME("Mines.axeVipMines.regenerationTime"),
    SHOVEL_VIP_REG_TIME("Mines.shovelVipMines.regenerationTime"),
    HOE_VIP_REG_TIME("Mines.hoeVipMines.regenerationTime"),
    SELL_GUI_NAME("Gui.Sell.name"),
    SELL_GUI_SLOTS("Gui.Sell.slots"),
    SELL_GUI_COMPASS_NAME("Gui.Sell.compass.name"),
    SELL_GUI_COMPASS_LORE("Gui.Sell.compass.lore"),
    SELL_GUI_EMERALD_NAME("Gui.Sell.emerald.name"),
    SELL_GUI_EMERALD_LORE("Gui.Sell.emerald.lore"),
    PICKAXE_REGIONS("Regions.pickaxeMines"),
    AXE_REGIONS("Regions.axeMines"),
    SHOVEL_REGIONS("Regions.shovelMines"),
    HOE_REGIONS("Regions.hoeMines"),
    VIP_REGIONS("Regions.vipMines"),
    GIVE_MONEY_COMMAND("giveMoneyCommand"),
    SOLD_MESSAGE("Messages.sold"),
    ITEM_NOT_FOUND("Messages.itemNotFound"),
    PLAYER_NOT_FOUND("Messages.playerNotFound");
    private final String path;
    private final AquariusFarm instance = AquariusFarm.getInstance();
    private Config(String path) {
        this.path = path;
    }

    public Item getItem() {
        String name = instance.getConfig().getString(this.path + ".name").replaceAll("&", "§");
        List<String> lore = new ArrayList<>();
        instance.getConfig().getStringList(this.path + ".lore").forEach(s -> {
            lore.add(s.replaceAll("&", "§"));
        });
        return new Item(name, lore, Material.valueOf(path.replace("Tools.", "")));
    }
    public String getString() {
        return instance.getConfig().getString(path);
    }
    public String getFormattedString() {
        return instance.getConfig().getString(path).replaceAll("&", "§");
    }
    public int getInt() {
        return instance.getConfig().getInt(path);
    }
    public double getDouble() {
        return instance.getConfig().getDouble(path);
    }
    public float getFloat() {
        return (float) instance.getConfig().getDouble(path);
    }
    public long getLong() {
        return instance.getConfig().getLong(path);
    }
    public boolean getBool() {
        return instance.getConfig().getBoolean(path);
    }
    public List<String> getStringList() {
        return instance.getConfig().getStringList(path);
    }
    public List<String> getFormattedStringList() {
        List<String> formattedLore = new ArrayList<>();
        instance.getConfig().getStringList(this.path).forEach(s -> formattedLore.add(s.replaceAll("&", "§")));
        return formattedLore;
    }



}
