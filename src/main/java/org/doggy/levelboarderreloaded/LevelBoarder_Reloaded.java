package org.doggy.levelboarderreloaded;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class LevelBoarder_Reloaded extends JavaPlugin {

    @Override
    public void onEnable() {
        startBorderUpdater();
    }

    private void startBorderUpdater() {
        new BukkitRunnable() {
            @Override
            public void run() {
                updateWorldBorder();
            }
        }.runTaskTimer(this, 0L, 100L); // 100L entspricht 5 Sekunden (20 Ticks pro Sekunde)
    }

    private void updateWorldBorder() {
        World world = Bukkit.getWorld("world");
        if (world == null) return;

        int totalLevels = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            totalLevels += player.getLevel();
        }

        double borderSize = totalLevels * 2.0; // Passe den Multiplikator nach Bedarf an
        Location spawnLocation = world.getSpawnLocation();

        world.getWorldBorder().setCenter(spawnLocation);
        world.getWorldBorder().setSize(borderSize);
    }
}

