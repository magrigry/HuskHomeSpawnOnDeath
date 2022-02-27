package fr.historya.huskhomespawnondeath;

import me.william278.huskhomes2.HuskHomes;
import me.william278.huskhomes2.api.HuskHomesAPI;
import me.william278.huskhomes2.data.DataManager;
import me.william278.huskhomes2.teleport.TeleportManager;
import me.william278.huskhomes2.teleport.points.TeleportationPoint;
import me.william278.huskhomes2.teleport.points.Warp;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

public class HandleDeathRespawn implements Listener {

    private final HuskHomesAPI huskHomesAPI;

    public HandleDeathRespawn(HuskHomesAPI huskHomesAPI) {
        this.huskHomesAPI = huskHomesAPI;
    }

    @EventHandler
    public void onDeath(PlayerRespawnEvent event) {

        if (! HuskHomes.getSettings().doCrossServerSpawn()) {
            return;
        }

        String spawnWarpName = HuskHomes.getSettings().getSpawnWarpName();

        try (Connection connection = HuskHomes.getConnection()) {
            if (! DataManager.warpExists(spawnWarpName, connection)) {
                return;
            }
        } catch (SQLException e) {
            Bukkit.getLogger().log(Level.SEVERE, "An SQL exception occurred teleporting a player.", e);
        }

        Warp warp = this.huskHomesAPI.getWarp(spawnWarpName);

        if (warp == null) {
            return;
        }

        TeleportationPoint teleportationPoint = new TeleportationPoint(warp.getLocation(), warp.getServer());

        try (Connection connection = HuskHomes.getConnection()) {
            DataManager.setPlayerDestinationLocation(event.getPlayer(), teleportationPoint, connection);
            TeleportManager.teleportPlayer(event.getPlayer());
        } catch (SQLException e) {
            Bukkit.getLogger().log(Level.SEVERE, "An SQL exception occurred teleporting a player.", e);
        }

    }

}
