package com.dop.soulsiphon.Commands;

import com.dop.soulsiphon.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SetReviveSpawnCMD implements CommandExecutor {

    private final Main main;
    public SetReviveSpawnCMD(Main main) {

        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;
            if (player.hasPermission("soulsiphon.setrespawnpoint")) {
                Location location = player.getLocation();

                main.config.set("ReviveSpawn.x", location.getX());
                main.config.set("ReviveSpawn.y", location.getY());
                main.config.set("ReviveSpawn.z", location.getZ());
                main.config.set("ReviveSpawn.world", location.getWorld().getName());
                                                    try {
                                        main.config.save(new File(main.getDataFolder(), "configuration.yml"));
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                player.sendMessage(main.prefix + " " + ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', main.lang.getString("SpawnSet1")) + " " + location.getX() + " " + location.getY() + " " + location.getZ() + " " + ChatColor.translateAlternateColorCodes('&', main.lang.getString("SpawnSet2")) + " " + location.getWorld().getName()));
            }
        } else {

            System.out.println(main.prefix + " " + ChatColor.translateAlternateColorCodes('&', main.lang.getString("CantBeRunHere")));

        }


        return false;
    }
}

