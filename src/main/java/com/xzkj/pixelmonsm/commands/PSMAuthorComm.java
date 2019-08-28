package com.xzkj.pixelmonsm.commands;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.PartyStorage;
import com.pixelmonmod.pixelmon.api.storage.PokemonStorage;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.EVStore;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.IVStore;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import com.xzkj.pixelmonsm.utils.MessTool;
import com.xzkj.pixelmonsm.utils.PokTool;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PSMAuthorComm implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (!(p.getName().equalsIgnoreCase("xzkjxzkj"))){
            MessTool.logPlayer("作者专用指令!",p);
            return false;
        }
        if (args[0].equalsIgnoreCase("list")){
            PlayerPartyStorage playerStorage = Pixelmon.storageManager.getParty(p.getUniqueId());
            Pokemon pok = playerStorage.get(1);
            MessTool.logServer(pok.toString());
            IVStore pokIVS = pok.getIVs();
            EVStore pokEVS = pok.getEVs();
            MessTool.logPlayer("玩家首位精灵原版名字为:" + pok.getDisplayName(),p);
            //原版
            MessTool.logPlayer("玩家首位精灵当前名字为:" + pok.getNickname(),p);
            //玩家自定义
            MessTool.logPlayer("玩家首位精灵名字为:" + pok.getAbilityName(),p);
            MessTool.logPlayer("玩家首位精灵IVS[attack]为:" + pokIVS.attack,p);
            MessTool.logPlayer("玩家首位精灵IVS[defence]为:" + pokIVS.defence,p);
            MessTool.logPlayer("玩家首位精灵IVS[hp]为:" + pokIVS.hp,p);
            MessTool.logPlayer("玩家首位精灵IVS[specialAttack]为:" + pokIVS.specialAttack,p);
            MessTool.logPlayer("玩家首位精灵IVS[specialDefence]为:" + pokIVS.specialDefence,p);
            MessTool.logPlayer("玩家首位精灵IVS[speed]为:" + pokIVS.speed,p);
            MessTool.logPlayer("玩家首位精灵EVS[attack]为:" + pokEVS.attack,p);
            MessTool.logPlayer("玩家首位精灵EVS[defence]为:" + pokEVS.defence,p);
            MessTool.logPlayer("玩家首位精灵EVS[hp]为:" + pokEVS.hp,p);
            MessTool.logPlayer("玩家首位精灵EVS[specialAttack]为:" + pokEVS.specialAttack,p);
            MessTool.logPlayer("玩家首位精灵EVS[specialDefence]为:" + pokEVS.specialDefence,p);
            MessTool.logPlayer("玩家首位精灵EVS[speed]为:" + pokEVS.speed,p);
            MessTool.logPlayer("玩家首位精灵等级为:" + pok.getLevel(),p);
        }else if(args[0].equalsIgnoreCase("pok")){

        }
        return false;
    }
}
