package com.xzkj.pixelmonsm.commands;

import com.xzkj.pixelmonsm.data.listdata.PokPT;
import com.xzkj.pixelmonsm.utils.MessTool;
import com.xzkj.pixelmonsm.utils.PokTool;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PSMAdminComm implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)){
            commandSender.sendMessage(">>请务必以玩家身份打开!");
            return true;
        }
        Player p = (Player) commandSender;
        MessTool.logPlayer("原始名称:" + PokTool.getPlayerPokemon0(p).getDisplayName(),p);
        return false;
    }
}
