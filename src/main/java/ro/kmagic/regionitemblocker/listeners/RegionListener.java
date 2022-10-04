package ro.kmagic.regionitemblocker.listeners;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import ro.kmagic.regionitemblocker.RegionItemBlocker;
import ro.kmagic.regionitemblocker.items.Item;
import ro.kmagic.regionitemblocker.utils.Util;

import java.util.List;

public class RegionListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onItemUse(PlayerInteractEvent event) {
        if(!event.hasItem()) return;

        ItemStack item = event.getItem();
        Player player = event.getPlayer();

        if(!item.hasItemMeta()) return;

        RegionManager regions = WGBukkit.getRegionManager(player.getWorld());
        ApplicableRegionSet set = regions.getApplicableRegions(player.getLocation());

        for(Item items : RegionItemBlocker.getInstance().getItemLoader().getItems()) {
            if(item.getData().getItemType().getId() != items.getId()) return;
            if(item.getItemMeta().getDisplayName() != null) {
                String displayName = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', item.getItemMeta().getDisplayName()));
                String itemName = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', items.getName()));
                if(displayName.equalsIgnoreCase(itemName) && items.getBlockedWorlds().contains(player.getWorld().getName())) {
                    player.sendMessage(Util.color(RegionItemBlocker.getInstance().getConfig().getString("message")));
                    event.setUseItemInHand(Event.Result.DENY);
                    event.setCancelled(true);
                    return;
                }

                for(String region : items.getBlockedRegions()) {
                    if(displayName.equalsIgnoreCase(itemName) && set.getRegions().stream().anyMatch(rg -> rg.getId().equalsIgnoreCase(region))) {
                        player.sendMessage(Util.color(RegionItemBlocker.getInstance().getConfig().getString("message")));
                        event.setUseItemInHand(Event.Result.DENY);
                        event.setCancelled(true);
                        return;
                    }
                }
            }

            if(item.getItemMeta().getLore() != null) {
                List<String> lore = item.getItemMeta().getLore();

                if (lore.stream().anyMatch(element -> items.getLore().contains(element))) {

                    if (items.getBlockedWorlds().contains(player.getWorld().getName())) {
                        player.sendMessage(Util.color(RegionItemBlocker.getInstance().getConfig().getString("message")));
                        event.setUseItemInHand(Event.Result.DENY);
                        event.setCancelled(true);
                        return;
                    }

                    for (String region : items.getBlockedRegions()) {
                        if (set.getRegions().stream().anyMatch(rg -> rg.getId().equalsIgnoreCase(region))) {
                            player.sendMessage(Util.color(RegionItemBlocker.getInstance().getConfig().getString("message")));
                            event.setUseItemInHand(Event.Result.DENY);
                            event.setCancelled(true);
                            return;
                        }
                    }
                }
            }
        }

    }

}
