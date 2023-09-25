package me.zoon20x.minepacks;

import at.pcgamingfreaks.Minepacks.Bukkit.API.MinepacksPlugin;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.Locale;

public class MinepacksExtension extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "minepacks";
    }
    @Override
    public @NotNull String getAuthor() {
        return "zoon20x";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public @NotNull String getRequiredPlugin() {
        return "minepacks";
    }


    public String onPlaceholderRequest(Player p, String arg) {
        try {

            String[] args = arg.split(" ");
            Player plr = p;
            if(args.length >= 2) {
                plr = Bukkit.getPlayer(args[args.length - 1]);
            }
            if(plr == null) {
                return "Unknown player";
            }
            switch (args[0].toLowerCase(Locale.ROOT)) {
                case "backpack_contents":
                    return Arrays.toString(getMinepacks().getBackpackCachedOnly(plr).getInventory().getContents());
                case "backpack_size":
                    return String.valueOf(getMinepacks().getBackpackCachedOnly(plr).getInventory().getSize());
                case "isblocked":
                    if(args.length < 3) {
                        return "Usage: %minepacks_isblocked item amount PLAYER% | %minepacks_isblocked AIR 1 Zoon20x%";
                    }
                    String blocked = args[1];
                    String amount = args[2];
                    return getMinepacks().getItemFilter().isItemBlocked(new ItemStack(Material.getMaterial(blocked), Integer.parseInt(amount))) ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
                default:
                    return "Invalid placeholder";
            }
        } catch (NoClassDefFoundError e) {
            return "Minepacks not found!";
        }
    }

    public MinepacksPlugin getMinepacks() {
        Plugin bukkitPlugin = Bukkit.getPluginManager().getPlugin("Minepacks");
        if(!(bukkitPlugin instanceof MinepacksPlugin)) {
            // Do something if Minepacks is not available
            return null;
        }
        return (MinepacksPlugin) bukkitPlugin;
    }
}
