package org.wasalona.tickets;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Ticket extends JavaPlugin implements CommandExecutor, Listener {

    @Override
    public void onEnable() {
        getLogger().info("TicketsPlugin has been enabled!");

        // Register command executor for /bounty command
        Objects.requireNonNull(getCommand("ticket")).setExecutor(this);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("BountiesPlugin has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /ticket <option>");
            return true;
        }

        // Handle command info
        if (args[0].equalsIgnoreCase("info")) {
            if (args.length != 1) {
                player.sendMessage(ChatColor.RED + "Usage: /ticket info");
                return true;
            }

            if (!player.hasPermission("tickets.info")) {
                player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
                return true;
            }

            player.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD +"Train Tickets");
            player.sendMessage(ChatColor.AQUA + "1. " + ChatColor.GREEN + "1  Ticket  - " + ChatColor.GOLD + "4  Gold Coins");
            player.sendMessage(ChatColor.AQUA + "2. " + ChatColor.GREEN + "5  Tickets - " + ChatColor.GOLD + "18 Gold Coins");
            player.sendMessage(ChatColor.AQUA + "3. " + ChatColor.GREEN + "10 Tickets - " + ChatColor.GOLD + "32 Gold Coins");
            player.sendMessage(ChatColor.AQUA + "4. " + ChatColor.GREEN + "15 Tickets - " + ChatColor.GOLD + "45 Gold Coins");
            player.sendMessage(ChatColor.AQUA + "5. " + ChatColor.GREEN + "30 Tickets - " + ChatColor.AQUA + "1  Diamond Coin");
            player.sendMessage(ChatColor.GREEN + "Corre /ticket " + ChatColor.AQUA + "<option>" + ChatColor.GREEN + " para comprar esa cantidad.");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Usage: /ticket <option>");
            return true;
        }


        // Check permissions
        if (!player.hasPermission("tickets.buy")) {
            player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            return true;
        }

        int option;
        try {
            option = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            option = 0;
        }

        // Handle command buy
        if (option == 0) {
            player.sendMessage(ChatColor.RED + "Invalid option.");
            return true;
        }

        BuyTickets ticketHelper = new BuyTickets();

        boolean success = ticketHelper.buyTickets(option, player);

        if (success) {
            player.sendMessage(ChatColor.GREEN + "You have successfully bought the tickets!");
        } else {
            player.sendMessage(ChatColor.RED + "There was an error!");
        }

        return true;
    }
}
