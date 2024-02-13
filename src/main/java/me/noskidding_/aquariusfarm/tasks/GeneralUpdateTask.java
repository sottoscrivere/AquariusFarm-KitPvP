package me.noskidding_.aquariusfarm.tasks;

import lombok.Getter;
import me.noskidding_.aquariusfarm.AquariusFarm;
import me.noskidding_.aquariusfarm.MinesBlock;
import me.noskidding_.aquariusfarm.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class GeneralUpdateTask {
    @Getter
    private BukkitTask task;
    public GeneralUpdateTask() {
        task = new BukkitRunnable() {

            @Override
            public void run() {
                AquariusFarm.getBlocksManager().getBrokenBlocks().stream().filter(minesBlock -> minesBlock.hasRegenTimePassed()).forEach(minesBlock -> new BukkitRunnable() {
                    @Override
                    public void run() {
                        Block block = minesBlock.getBlock();
                        block.getWorld().getBlockAt(block.getLocation()).setType(minesBlock.getOldBlockType());
                        if (minesBlock.getOldBlockType().equals(Material.CROPS)) {
                            block.getWorld().getBlockAt(block.getLocation()).setData((byte) 7);
                        }
                        AquariusFarm.getBlocksManager().getBrokenBlocks().remove(minesBlock);
                    }
                }.runTask(AquariusFarm.getInstance()));
            }
        }.runTaskTimerAsynchronously(AquariusFarm.getInstance(), 0L, 0L);
    }
}
