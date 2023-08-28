package me.zoon20x.minepacks;

import at.pcgamingfreaks.Minepacks.Bukkit.API.MinepacksPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class MinepacksWrapper {
    public static MinepacksPlugin getMinepacks() {
        Plugin bukkitPlugin = Bukkit.getPluginManager().getPlugin("Minepacks");
        if(!(bukkitPlugin instanceof MinepacksPlugin)) {
            return null;
        }
        return (MinepacksPlugin) bukkitPlugin;
    }
}
