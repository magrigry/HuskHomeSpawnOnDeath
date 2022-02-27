# HuskHome-SpawnOnDeath

This plugin is a simple addon for [HuskHomes](https://github.com/WiIIiam278/HuskHomes2) that allow to teleport player to spawn when they die.  

When `spawn_command.enabled` is set to `true` 
and `spawn_command.bungee_network_spawn.enabled` is set to `true`
and `spawn_command.bungee_network_spawn.warp_name` is correctly configured 

This plugin will handle `PlayerRespawnEvent` to teleport the player to the spawn warp. 
Players care use the `/back` command to go back to their death location. 

# Download

Download in the release TAB

# Notes

Tested with HuskHome 2.11. Since this plugin use internal HuskHome code (see issue [here](https://github.com/WiIIiam278/HuskHomes2/issues/155)) this plugin may break on future version. 
