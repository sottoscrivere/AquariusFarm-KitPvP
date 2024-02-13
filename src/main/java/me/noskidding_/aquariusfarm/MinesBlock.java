package me.noskidding_.aquariusfarm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.noskidding_.aquariusfarm.config.Config;
import org.bukkit.Material;
import org.bukkit.block.Block;

@AllArgsConstructor
public class MinesBlock {
    @Getter
    private Block block;
    @Getter
    private Material oldBlockType;
    @Getter
    private boolean isVipMine;
    @Getter
    private long breakTime;
    public boolean hasRegenTimePassed() {
        switch (oldBlockType) {
            case DIAMOND_ORE:
            case EMERALD_ORE:
            case GOLD_ORE:
            case LAPIS_ORE:
            case REDSTONE_ORE:
                if (!isVipMine) {
                    if ( (System.currentTimeMillis() - breakTime) >= Config.PICKAXE_MINES_REG_TIME.getLong()) {
                        return true;
                    }
                }else {
                    if ( (System.currentTimeMillis() - breakTime) >= Config.PICKAXE_VIP_REG_TIME.getLong()) {
                        return true;
                    }
                }

                break;
            case LOG:
                if (!isVipMine){
                    if ( (System.currentTimeMillis() - breakTime) >= Config.AXE_MINES_REG_TIME.getLong()) {
                        return true;
                    }
                }else {
                    if ( (System.currentTimeMillis() - breakTime) >= Config.AXE_VIP_REG_TIME.getLong()) {
                        return true;
                    }
                }

                break;
            case SOUL_SAND:
                if (!isVipMine) {
                    if ( (System.currentTimeMillis() - breakTime) >= Config.SHOVEL_MINES_REG_TIME.getLong()) {
                        return true;
                    }
                }else {
                    if ( (System.currentTimeMillis() - breakTime) >= Config.SHOVEL_VIP_REG_TIME.getLong()) {
                        return true;
                    }
                }

                break;
            case CROPS:
                if (!isVipMine) {
                    if ( (System.currentTimeMillis() - breakTime) >= Config.HOE_MINES_REG_TIME.getLong()) {
                        return true;
                    }
                }else {
                    if ( (System.currentTimeMillis() - breakTime) >= Config.HOE_VIP_REG_TIME.getLong()) {
                        return true;
                    }
                }
                break;
            default:
                break;
        }
        return false;
    }
}
