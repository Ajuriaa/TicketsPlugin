package org.wasalona.tickets;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BuyTickets {
    public boolean buyTickets(int option, Player player) {
        boolean hasMoney = hasAmount(player, option);

        if(hasMoney) {
            return giveTicket(player, option);
        }

        player.sendMessage(ChatColor.RED + "You don't have enough money to buy this ticket.");

        return false;
    }

    private void executeCommand(String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    private boolean giveTicket(Player player, int option) {
        switch (option) {
            case 1:
                executeCommand("clear " + player.getName() + " lightmanscurrency:coin_gold 4");
                executeCommand("scoreboard players add " + player.getName() + " mtr_balance 1");
                return true;
            case 2:
                executeCommand("clear " + player.getName() + " lightmanscurrency:coin_gold 18");
                executeCommand("scoreboard players add " + player.getName() + " mtr_balance 5");
                return true;
            case 3:
                executeCommand("clear " + player.getName() + " lightmanscurrency:coin_gold 32");
                executeCommand("scoreboard players add " + player.getName() + " mtr_balance 10");
                return true;
            case 4:
                executeCommand("clear " + player.getName() + " lightmanscurrency:coin_gold 45");
                executeCommand("scoreboard players add " + player.getName() + " mtr_balance 15");
                return true;
            case 5:
                executeCommand("clear " + player.getName() + " lightmanscurrency:coin_diamond 1");
                executeCommand("scoreboard players add " + player.getName() + " mtr_balance 30");
                return true;
            default:
                player.sendMessage(ChatColor.RED + "Invalid option.");
                return false;
        }
    }

    private boolean hasAmount(Player player, int option) {
        ItemStack[] items = player.getInventory().getContents();
        boolean success = false;

        switch (option) {
            case 1:
                for (ItemStack item : items) {
                    if(item == null || item.getItemMeta() == null) {
                        continue;
                    }
                    if (item.getType() == Material.valueOf("LIGHTMANSCURRENCY_COIN_GOLD") && item.getAmount() >= 4) {
                        success = true;
                    }
                }
            case 2:
                for (ItemStack item : items) {
                    if(item == null || item.getItemMeta() == null) {
                        continue;
                    }
                    if (item.getType() == Material.valueOf("LIGHTMANSCURRENCY_COIN_GOLD") && item.getAmount() >= 18) {
                        success = true;
                    }
                }
            case 3:
                for (ItemStack item : items) {
                    if(item == null || item.getItemMeta() == null) {
                        continue;
                    }
                    if (item.getType() == Material.valueOf("LIGHTMANSCURRENCY_COIN_GOLD") && item.getAmount() >= 32) {
                        success = true;
                    }
                }
            case 4:
                for (ItemStack item : items) {
                    if(item == null || item.getItemMeta() == null) {
                        continue;
                    }
                    if (item.getType() == Material.valueOf("LIGHTMANSCURRENCY_COIN_GOLD") && item.getAmount() >= 45) {
                        success = true;
                    }
                }
            case 5:
                for (ItemStack item : items) {
                    if(item == null || item.getItemMeta() == null) {
                        continue;
                    }
                    if (item.getType() == Material.valueOf("LIGHTMANSCURRENCY_COIN_DIAMOND") && item.getAmount() >= 1) {
                        success = true;
                    }
                }
            default:
                return success;
        }
    }
}
