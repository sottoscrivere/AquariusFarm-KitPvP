package me.noskidding_.aquariusfarm.events;

import com.sk89q.worldedit.bukkit.adapter.BukkitImplAdapter;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.noskidding_.aquariusfarm.AquariusFarm;
import me.noskidding_.aquariusfarm.config.Config;
import me.noskidding_.aquariusfarm.utils.CustomTools;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class MineBlocksListener implements Listener {

    public void processBlock(Block b, Material type, boolean isVipMine) {
        new BukkitRunnable(){
            @Override
            public void run() {
                if (!type.equals(Material.CROPS)) {
                    b.getWorld().getBlockAt(b.getLocation()).setType(Material.STONE);
                }else {
                    b.getWorld().getBlockAt(b.getLocation()).setType(Material.CROPS);
                }

            }
        }.runTaskLater(AquariusFarm.getInstance(), 5L);
        AquariusFarm.getBlocksManager().addBrokenBlock(b,type, isVipMine,System.currentTimeMillis());
    }

    public boolean isVipMine(Location l) {
        RegionContainer container = WorldGuardPlugin.inst().getRegionContainer();
        RegionManager regions = container.get(l.getWorld());
        if (Config.VIP_REGIONS.getStringList().stream().anyMatch(s -> {
            if (regions.hasRegion(s)) {
                ProtectedRegion pr = regions.getRegion(s);
                if (pr.contains(l.getBlockX(), l.getBlockY(), l.getBlockZ())) {
                    return true;
                }
            }
            return false;
        })) {
            return true;
        }
        return false;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent e) {
        ItemStack item = e.getPlayer().getItemInHand();
        Material type = e.getBlock().getType();
        if (!e.isCancelled()) {
            if (e.getBlock().getWorld().getName().equals(Config.MINES_WORLD.getString())) {
                if (CustomTools.isPluginTool(item)) {
                    RegionContainer container = WorldGuardPlugin.inst().getRegionContainer();
                    RegionManager regions = container.get(e.getBlock().getWorld());

                    switch (type) {
                        case REDSTONE_ORE:
                        case DIAMOND_ORE:
                        case LAPIS_ORE:
                        case GOLD_ORE:
                        case EMERALD_ORE: {
                            if (Config.PICKAXE_REGIONS.getStringList().stream().anyMatch(s -> {
                                if (regions.hasRegion(s)) {
                                    ProtectedRegion pr = regions.getRegion(s);
                                    Location l = e.getBlock().getLocation();
                                    if (pr.contains(l.getBlockX(), l.getBlockY(), l.getBlockZ())) {
                                        return true;
                                    }
                                }
                                return false;
                            })) {
                                if (item.getType().equals(Material.DIAMOND_PICKAXE) || item.getType().equals(Material.IRON_PICKAXE)) {
                                   if (isVipMine(e.getBlock().getLocation())) {
                                        processBlock(e.getBlock(), type, true);
                                        break;
                                    }
                                    processBlock(e.getBlock(), type, false);

                                }
                            }else {
                                e.setCancelled(true);
                            }

                            break;
                        }
                        case LOG: {
                            if (Config.AXE_REGIONS.getStringList().stream().anyMatch(s -> {
                                if (regions.hasRegion(s)) {
                                    ProtectedRegion pr = regions.getRegion(s);
                                    Location l = e.getBlock().getLocation();
                                    if (pr.contains(l.getBlockX(), l.getBlockY(), l.getBlockZ())) {
                                        return true;
                                    }
                                }
                                return false;
                            })) {
                                if (item.getType().equals(Material.DIAMOND_AXE) || item.getType().equals(Material.IRON_AXE)) {
                                    if (isVipMine(e.getBlock().getLocation())) {
                                        processBlock(e.getBlock(), type, true);
                                        break;
                                    }
                                    processBlock(e.getBlock(), type, false);
                                }
                            }else {
                                e.setCancelled(true);
                            }
                            break;
                        }
                        case SOUL_SAND: {
                            if (Config.SHOVEL_REGIONS.getStringList().stream().anyMatch(s -> {
                                if (regions.hasRegion(s)) {
                                    ProtectedRegion pr = regions.getRegion(s);
                                    Location l = e.getBlock().getLocation();
                                    if (pr.contains(l.getBlockX(), l.getBlockY(), l.getBlockZ())) {
                                        return true;
                                    }
                                }
                                return false;
                            })) {
                                if (item.getType().equals(Material.DIAMOND_SPADE) || item.getType().equals(Material.IRON_SPADE)) {
                                    if (isVipMine(e.getBlock().getLocation())) {
                                        processBlock(e.getBlock(), type, true);
                                        break;
                                    }
                                    processBlock(e.getBlock(), type, false);
                                }
                            }else {
                                e.setCancelled(true);
                            }
                            break;
                        }
                        case CROPS: {
                            if (Config.HOE_REGIONS.getStringList().stream().anyMatch(s -> {
                                if (regions.hasRegion(s)) {
                                    ProtectedRegion pr = regions.getRegion(s);
                                    Location l = e.getBlock().getLocation();
                                    if (pr.contains(l.getBlockX(), l.getBlockY(), l.getBlockZ())) {
                                        return true;
                                    }
                                }
                                return false;
                            })) {
                                if (item.getType().equals(Material.DIAMOND_HOE) || item.getType().equals(Material.IRON_HOE)) {
                                    if (isVipMine(e.getBlock().getLocation())) {
                                        processBlock(e.getBlock(), type, true);
                                        break;
                                    }
                                    processBlock(e.getBlock(), type, false);
                                }
                            }else {
                                e.setCancelled(true);
                            }
                            break;
                        }

                        default:
                            e.setCancelled(true);
                            break;
                    }


                }





            }
        }
    }

}
