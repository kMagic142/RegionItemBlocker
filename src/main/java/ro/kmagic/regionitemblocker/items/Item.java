package ro.kmagic.regionitemblocker.items;

import java.util.List;

public class Item {

    private final String name;
    private final int id;
    private final List<String> lore;
    private final List<String> blockedRegions;
    private final List<String> blockedWorlds;

    public Item(String name, int id, List<String> lore, List<String> blockedRegions, List<String> blockedWorlds) {
        this.name = name;
        this.id = id;
        this.lore = lore;
        this.blockedRegions = blockedRegions;
        this.blockedWorlds = blockedWorlds;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<String> getLore() {
        return lore;
    }

    public List<String> getBlockedRegions() {
        return blockedRegions;
    }

    public List<String> getBlockedWorlds() {
        return blockedWorlds;
    }
}
