package de.rexituz.gungame.positions;

import org.bukkit.Location;

import java.util.HashMap;

public class PositionHandler {
    private static final HashMap<String, Location> positions = new HashMap<>();

    public static void createPosition(String name, Location location) {
        positions.put(name, location);
    }

    public static boolean isInNoHitZone(Location location) {
        return  location.getX() > getPosition("pos_1").getX() &&
                location.getY() > getPosition("pos_1").getY() &&
                location.getX() < getPosition("pos_2").getX() &&
                location.getY() < getPosition("pos_2").getY();
    }

    public static Location getPosition(String name) {
        return positions.get(name);
    }
}
