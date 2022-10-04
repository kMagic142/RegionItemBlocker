package ro.kmagic.regionitemblocker.items;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import ro.kmagic.regionitemblocker.RegionItemBlocker;

import java.util.LinkedList;
import java.util.List;

public class ItemLoader {

    private final List<Item> items;

    public ItemLoader() {
        items = new LinkedList<>();
    }

    public void loadItems() {
        FileConfiguration config = RegionItemBlocker.getInstance().getConfig();
        ConfigurationSection blockedItems = config.getConfigurationSection("blocked-items");

        blockedItems.getKeys(false).forEach(key -> {
            ConfigurationSection item = blockedItems.getConfigurationSection(key);

            String name = item.getString("name");
            int id = item.getInt("id");
            List<String> lore = item.getStringList("lore");
            List<String> blockedRegions = item.getStringList("blocked-regions");
            List<String> blockedWorlds = item.getStringList("blocked-worlds");

            items.add(new Item(name, id, lore, blockedRegions, blockedWorlds));
        });
    }

    public List<Item> getItems() {
        return items;
    }
}
