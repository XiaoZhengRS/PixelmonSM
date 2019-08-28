package com.xzkj.pixelmonsm.commands;

import com.xzkj.pixelmonsm.vv.Vex;
import lk.vexview.api.VexViewAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PSMPlayerComm implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)){
            commandSender.sendMessage(">>请务必以玩家身份打开!");
            return true;
        }
        Player p = (Player) commandSender;
        VexViewAPI.openGui(p,new Vex().gitVexPlayerMain(p));
        return false;
    }
}
