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

import java.util.ArrayList;
import java.util.List;

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
        // Create a new inventory with 27 slots
        Inventory gui = Bukkit.createInventory(null, 27, "Enhanced Custom GUI");

        // Create items to add to the GUI
        ItemStack item1 = new ItemStack(Material.DIAMOND);
        ItemMeta meta1 = item1.getItemMeta();
        if (meta1 != null) {
            meta1.setDisplayName("Shiny Diamond");

            // Add lore (description) to the diamond
            List<String> lore1 = new ArrayList<>();
            lore1.add("A very shiny diamond!");
            lore1.add("It's priceless!");
            meta1.setLore(lore1);

            item1.setItemMeta(meta1);
        }

        ItemStack item2 = new ItemStack(Material.APPLE);
        ItemMeta meta2 = item2.getItemMeta();
        if (meta2 != null) {
            meta2.setDisplayName("Magic Apple");

            // Add lore to the apple
            List<String> lore2 = new ArrayList<>();
            lore2.add("A magical apple!");
            lore2.add("Click to gain health.");
            meta2.setLore(lore2);

            item2.setItemMeta(meta2);
        }

        // Add items to the GUI
        gui.setItem(11, item1); // Position the diamond at slot 11
        gui.setItem(15, item2); // Position the apple at slot 15

        // Open the GUI for the player
        player.openInventory(gui);
    }

    // Handle clicking in the custom GUI
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Enhanced Custom GUI")) {
            event.setCancelled(true); // Prevent taking the items

            if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
                Player player = (Player) event.getWhoClicked();
                ItemStack clickedItem = event.getCurrentItem();
                String displayName = clickedItem.getItemMeta().getDisplayName();

                // Perform actions based on the clicked item
                if (displayName.equals("Shiny Diamond")) {
                    player.sendMessage("You clicked on the Shiny Diamond!");
                } else if (displayName.equals("Magic Apple")) {
                    player.sendMessage("You clicked on the Magic Apple!");
                    // Give the player a health boost
                    player.setHealth(Math.min(player.getHealth() + 4.0, player.getMaxHealth()));
                }

                // Close the GUI
                player.closeInventory();
            }
        }
    }
}
