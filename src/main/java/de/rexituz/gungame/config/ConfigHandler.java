package de.rexituz.gungame.config;

import de.rexituz.gungame.GunGame;
import de.rexituz.gungame.positions.PositionHandler;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHandler {
    public static void loadConfig() {
        GunGame.getInstance().getPlugin().saveDefaultConfig();
        FileConfiguration config = GunGame.getInstance().getPlugin().getConfig();

        Location spawn = config.getLocation("positions.spawn");
        Location pos_1 = config.getLocation("positions.pos_1");
        Location pos_2 = config.getLocation("positions.pos_2");

        if(spawn == null || pos_1 == null || pos_2 == null) {
            throw new IllegalArgumentException("Config doesn't contain needed Locations.");
        }

        PositionHandler.createPosition("spawn", spawn);
        PositionHandler.createPosition("pos_1", pos_1);
        PositionHandler.createPosition("pos_2", pos_2);
    }
}
