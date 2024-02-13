package me.noskidding_.aquariusfarm;

import com.sk89q.worldedit.regions.Region;
import lombok.Getter;
import me.noskidding_.aquariusfarm.commands.FarmCommand;
import me.noskidding_.aquariusfarm.events.MineBlocksListener;
import me.noskidding_.aquariusfarm.events.SellGuiListener;
import me.noskidding_.aquariusfarm.manager.BlocksManager;
import me.noskidding_.aquariusfarm.placeholders.RegionsPlaceholders;
import me.noskidding_.aquariusfarm.tasks.GeneralUpdateTask;
import me.noskidding_.aquariusfarm.utils.SellGui;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AquariusFarm extends JavaPlugin {
    @Getter
    private static AquariusFarm instance;
    @Getter
    private static BlocksManager blocksManager;
    @Getter
    private GeneralUpdateTask generalUpdateTask;
    public AquariusFarm() {
        instance = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        blocksManager = new BlocksManager();
        getCommand("aquariusfarm").setExecutor(new FarmCommand());
        Bukkit.getPluginManager().registerEvents(new MineBlocksListener(), this);
        Bukkit.getPluginManager().registerEvents(new SellGuiListener(),this);
        generalUpdateTask = new GeneralUpdateTask();
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new RegionsPlaceholders().register();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
