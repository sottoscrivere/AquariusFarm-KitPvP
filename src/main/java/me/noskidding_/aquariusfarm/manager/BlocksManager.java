package me.noskidding_.aquariusfarm.manager;

import lombok.Getter;
import me.noskidding_.aquariusfarm.MinesBlock;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BlocksManager {
    @Getter
    private List<MinesBlock> brokenBlocks;
    public BlocksManager() {
        brokenBlocks = new CopyOnWriteArrayList<>();
    }

    public void addBrokenBlock(Block block, Material oldBlockType, boolean isVipMine, long breakTime) {
        brokenBlocks.add(new MinesBlock(block, oldBlockType, isVipMine, breakTime));
    }

}
