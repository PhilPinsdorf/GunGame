package de.rexituz.gungame.equipment;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;

public class Equipments {
    private static final LevelEquipment level0 = new LevelEquipment(null, null, null, null, Material.WOODEN_SWORD);
    private static final LevelEquipment level1 = new LevelEquipment(Material.LEATHER_HELMET, null, null, null, Material.WOODEN_SWORD);
    private static final LevelEquipment level2 = new LevelEquipment(Material.LEATHER_HELMET, null, null, Material.LEATHER_BOOTS, Material.WOODEN_SWORD);
    private static final LevelEquipment level3 = new LevelEquipment(Material.LEATHER_HELMET, null, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.WOODEN_SWORD);
    private static final LevelEquipment level4 = new LevelEquipment(Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.WOODEN_SWORD);
    private static final LevelEquipment level5 = new LevelEquipment(Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.GOLDEN_SWORD);
    private static final LevelEquipment level6 = new LevelEquipment(Material.GOLDEN_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.GOLDEN_SWORD);
    private static final LevelEquipment level7 = new LevelEquipment(Material.GOLDEN_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.GOLDEN_BOOTS, Material.GOLDEN_SWORD);
    private static final LevelEquipment level8 = new LevelEquipment(Material.GOLDEN_HELMET, Material.LEATHER_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS, Material.GOLDEN_SWORD);
    private static final LevelEquipment level9 = new LevelEquipment(Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS, Material.GOLDEN_SWORD);
    private static final LevelEquipment level10 = new LevelEquipment(Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS, Material.STONE_SWORD);
    private static final LevelEquipment level11 = new LevelEquipment(Material.CHAINMAIL_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS, Material.STONE_SWORD);
    private static final LevelEquipment level12 = new LevelEquipment(Material.CHAINMAIL_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.CHAINMAIL_BOOTS, Material.STONE_SWORD);
    private static final LevelEquipment level13 = new LevelEquipment(Material.CHAINMAIL_HELMET, Material.GOLDEN_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS, Material.STONE_SWORD);
    private static final LevelEquipment level14 = new LevelEquipment(Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS, Material.STONE_SWORD);
    private static final LevelEquipment level15 = new LevelEquipment(Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS, Material.IRON_SWORD);
    private static final LevelEquipment level16 = new LevelEquipment(Material.IRON_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS, Material.IRON_SWORD);
    private static final LevelEquipment level17 = new LevelEquipment(Material.IRON_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD);
    private static final LevelEquipment level18 = new LevelEquipment(Material.IRON_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD);
    private static final LevelEquipment level19 = new LevelEquipment(Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.IRON_SWORD);
    private static final LevelEquipment level20 = new LevelEquipment(Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.DIAMOND_SWORD);
    private static final LevelEquipment level21 = new LevelEquipment(Material.DIAMOND_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.DIAMOND_SWORD);
    private static final LevelEquipment level22 = new LevelEquipment(Material.DIAMOND_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.DIAMOND_BOOTS, Material.DIAMOND_SWORD);
    private static final LevelEquipment level23 = new LevelEquipment(Material.DIAMOND_HELMET, Material.IRON_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.DIAMOND_SWORD);
    private static final LevelEquipment level24 = new LevelEquipment(Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.DIAMOND_SWORD);

    private static final ArrayList<LevelEquipment> equipments = new ArrayList<LevelEquipment>(Arrays.asList(level0, level1, level2, level3, level4, level5, level6, level7, level8, level9, level10, level11, level12, level13, level14, level15, level16, level17, level18, level19, level20, level21, level22, level23, level24));

    public static ArrayList<LevelEquipment> getEquipments() {
        return equipments;
    }
}
