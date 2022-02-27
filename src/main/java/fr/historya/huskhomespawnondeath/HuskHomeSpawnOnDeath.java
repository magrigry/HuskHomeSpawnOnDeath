package fr.historya.huskhomespawnondeath;

import me.william278.huskhomes2.HuskHomes;
import me.william278.huskhomes2.api.HuskHomesAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class HuskHomeSpawnOnDeath extends JavaPlugin {

    @Override
    public void onEnable() {
        HuskHomes huskHomes = (HuskHomes) Bukkit.getPluginManager().getPlugin("HuskHomes");

        if (huskHomes != null) {
            if (!huskHomes.isEnabled()) {
                return;
            }
            HuskHomesAPI huskHomesAPI = HuskHomesAPI.getInstance();
            getServer().getPluginManager().registerEvents(new HandleDeathRespawn(huskHomesAPI), this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
