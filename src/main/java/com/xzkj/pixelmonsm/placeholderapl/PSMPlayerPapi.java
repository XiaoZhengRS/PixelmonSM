package com.xzkj.pixelmonsm.placeholderapl;

import com.xzkj.pixelmonsm.utils.player.PlayerTool;
import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.entity.Player;

public class PSMPlayerPapi extends PlaceholderHook {
    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.equalsIgnoreCase("MAXPOKINT")){
            return "";
        }
        if (identifier.equalsIgnoreCase("MAXPT")){
            return "";
        }
        if (identifier.equalsIgnoreCase("PT")){
            return String.valueOf(PlayerTool.givePlayerPT(player));
        }
        if (identifier.equalsIgnoreCase("SHOP")){
            return "";
        }
        return "作者小正";
    }
}
