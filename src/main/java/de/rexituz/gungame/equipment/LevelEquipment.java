package de.rexituz.gungame.equipment;

import org.bukkit.Material;

public class LevelEquipment {
    Material helmet;
    Material chestplate;
    Material leggings;
    Material boots;
    Material hand;

    public LevelEquipment(Material helmet, Material chestplate, Material leggings, Material boots, Material hand) {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.hand = hand;
    }

    public Material getHelmet() {
        return helmet;
    }

    public Material getChestplate() {
        return chestplate;
    }

    public Material getLeggings() {
        return leggings;
    }

    public Material getBoots() {
        return boots;
    }

    public Material getHand() {
        return hand;
    }
}
