package ro.kmagic.regionitemblocker;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ro.kmagic.regionitemblocker.items.ItemLoader;
import ro.kmagic.regionitemblocker.listeners.RegionListener;
import ro.kmagic.regionitemblocker.utils.Util;

public final class RegionItemBlocker extends JavaPlugin {

    private static RegionItemBlocker instance;
    private ItemLoader itemLoader;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        getConfig().options().copyDefaults(true);

        if(Bukkit.getPluginManager().getPlugin("WorldGuard") == null) {
            Util.error("WorldGuard could not be found. Disabling plugin...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        itemLoader = new ItemLoader();
        itemLoader.loadItems();

        Bukkit.getPluginManager().registerEvents(new RegionListener(), this);

        Util.info("RegionItemBlocker has been enabled successfully.");
    }

    @Override
    public void onDisable() {
        Util.info("RegionItemBlocker has been disabled successfully.");
    }


    public static RegionItemBlocker getInstance() {
        return instance;
    }

    public ItemLoader getItemLoader() {
        return itemLoader;
    }
}
