package me.noskidding_.aquariusfarm.commands;

import me.noskidding_.aquariusfarm.AquariusFarm;
import me.noskidding_.aquariusfarm.config.Config;
import me.noskidding_.aquariusfarm.utils.CustomTools;
import me.noskidding_.aquariusfarm.utils.SellGui;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FarmCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("aquariusfarm")) {
            if (args.length == 2) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    if (p.hasPermission("aquariusfarm.admin")) {
                        if (args[0].equalsIgnoreCase("give")) {
                            if (Config.valueOf(args[1].toUpperCase()) != null && Material.valueOf(args[1].toUpperCase()) != null) {
                                if (Material.valueOf(args[1].toUpperCase()) != null) {
                                    p.getInventory().addItem(CustomTools.getTool(Material.valueOf(args[1].toUpperCase())));
                                    p.sendMessage("Item givvato con succeso brother");
                                }else {
                                    p.sendMessage("§cItem non trovato.");
                                }

                            }
                        }
                    }

                }else {
                    sender.sendMessage("Questo comando può essere eseguito solo dai giocatori. Prova ad utilizzare: \"/aquariusfarm give <Material> <Player>\"");
                }

            }else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("sell")) {
                    if (sender instanceof Player) {
                        Player p = (Player) sender;
                        SellGui.openGui(p);
                    }else {
                        sender.sendMessage("Questo comando può essere eseguito solo dai giocatori.");
                    }
                }else if (args[0].equalsIgnoreCase("reload")) {
                    AquariusFarm.getInstance().reloadConfig();
                }

            }else if (args.length == 3) {
                if (sender instanceof Player && !sender.hasPermission("aquariusfarm.admin")) return false;
                if (args[0].equalsIgnoreCase("give")) {
                    if (Config.valueOf(args[1].toUpperCase()) != null && Material.valueOf(args[1].toUpperCase()) != null) {
                        if (Material.valueOf(args[1].toUpperCase()) != null) {
                            if (Bukkit.getPlayerExact(args[2]) != null) {
                                Player p = Bukkit.getPlayerExact(args[2]);
                                p.getInventory().addItem(CustomTools.getTool(Material.valueOf(args[1].toUpperCase())));
                            }else {
                                sender.sendMessage(Config.PLAYER_NOT_FOUND.getFormattedString());
                            }

                        }else {
                            sender.sendMessage(Config.ITEM_NOT_FOUND.getFormattedString());
                        }

                    }

                }

            }
        }
        return false;
    }
}
