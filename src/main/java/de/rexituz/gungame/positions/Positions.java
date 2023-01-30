package de.rexituz.gungame.positions;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum Positions {
    SPAWN(new Location(Bukkit.getWorld("world"), 0.5, 90, -0.5, -180, 0)),
    NO_HIT_POS_1(new Location(Bukkit.getWorld("world"), -6, 80, -7)),
    NO_HIT_POS_2(new Location(Bukkit.getWorld("world"), 8, 100, 6));

    private Location location;
    Positions(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
