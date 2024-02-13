package me.noskidding_.aquariusfarm.placeholders;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.clip.placeholderapi.PlaceholderHook;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RegionsPlaceholders extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "aquariusfarm";
    }

    @Override
    public @NotNull String getAuthor() {
        return "NoSkidding_";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }
    public String onPlaceholderRequest(Player player, String identifier) {
       if (identifier.startsWith("region") && identifier.split("_").length > 1) {
           String regionID = identifier.split("_")[1];
           WorldGuardPlugin worldGuardPlugin = WorldGuardPlugin.inst();
           int playerCount = 0;
           for (World w : Bukkit.getWorlds()) {
               if (worldGuardPlugin.getRegionManager(w).hasRegion(regionID)) {
                   for (Player p : Bukkit.getOnlinePlayers()) {
                       ProtectedRegion pr = worldGuardPlugin.getRegionManager(w).getRegion(regionID);
                       if (pr != null) {
                           Location l = p.getLocation();
                           if (pr.contains(l.getBlockX(), l.getBlockY(), l.getBlockZ())) {
                               playerCount++;
                           }
                       }

                   }
               }
           }
           return String.valueOf(playerCount);
       }


        return null;
    }

}
