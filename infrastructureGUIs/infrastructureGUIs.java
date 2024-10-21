// This is merely a concept plugin and this is not intended to be utilized by the infrastructure team.
// WARNING: Use of this script in the production server is a VIOLATION of copyright and intellectual property!!!! MAKE YOUR OWN AND USE THIS AS GUIDANCE!!!!!!
// Email the administrators or send a message to #SALARY-EMP-HELP if you have questions!!!

package com.inpuzah;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class infrastructureGUIs extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Register the plugin to listen for events
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("infrastructure dependency code -0H04 has been enabled!");
        getLogger().info("infrastructure plugins not working? Report it in the #First-Response channel on slack or email the administrator!");
    }

    @Override
    public void onDisable() {
        getLogger().info("infrastructureGUIs has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("opengui")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                openCustomGUI(player);
            }
            return true;
        }
        return false;
    }

    // Method to create and open the custom GUI
    public void openCustomGUI(Player player) {
        // Create a new inventory with a size of 9 slots and a custom title
        Inventory gui = Bukkit.createInventory(null, 9, "Custom GUI");

        // Create items to add to the GUI
        ItemStack item1 = new ItemStack(Material.DIAMOND);
        ItemMeta meta1 = item1.getItemMeta();
        if (meta1 != null) {
            meta1.setDisplayName("Shiny Diamond");
            item1.setItemMeta(meta1);
        }

        ItemStack item2 = new ItemStack(Material.APPLE);
        ItemMeta meta2 = item2.getItemMeta();
        if (meta2 != null) {
            meta2.setDisplayName("Magic Apple");
            item2.setItemMeta(meta2);
        }

        // Add items to the GUI
        gui.setItem(3, item1); // Position the diamond at slot 3
        gui.setItem(5, item2); // Position the apple at slot 5

        // Open the GUI for the player
        player.openInventory(gui);
    }

    // Handle clicking in the custom GUI
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Custom GUI")) {
            event.setCancelled(true); // Prevent taking the items

            if (event.getCurrentItem() != null) {
                Player player = (Player) event.getWhoClicked();
                ItemStack clickedItem = event.getCurrentItem();

                if (clickedItem.getType() == Material.DIAMOND) {
                    player.sendMessage("You clicked on the Shiny Diamond!");
                } else if (clickedItem.getType() == Material.APPLE) {
                    player.sendMessage("You clicked on the Magic Apple!");
                }

                // Close the GUI
                player.closeInventory();
            }
        }
    }
}
