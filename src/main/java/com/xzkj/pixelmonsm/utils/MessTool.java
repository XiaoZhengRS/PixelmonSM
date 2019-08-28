package com.xzkj.pixelmonsm.utils;

import lk.vexview.api.VexViewAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;

public class MessTool {
    public static int ddvv = 0;
    public static void logServer(String str){
        Bukkit.getLogger().info("§d[§5PixelMonSM§d]§7>>>§b " + str);
    }
    public static void logPlayer(String str, Player p){
        p.sendMessage("§d[§5PixelMonSM§d]§7>>>§b " + str);
    }

    public static void logVV(String str, Boolean vv){
        Collection<? extends Player> OnlinePlayer = Bukkit.getOnlinePlayers();
        if(ddvv > 80){
            ddvv = 0;
        }
        if(vv){
            OnlinePlayer.forEach(player -> VexViewAPI.sendFlowView(player, str, ddvv));
        }
        OnlinePlayer.forEach(player -> player.sendMessage(str));
    }
}
