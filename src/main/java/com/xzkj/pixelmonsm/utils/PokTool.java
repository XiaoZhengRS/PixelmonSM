package com.xzkj.pixelmonsm.utils;


import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import com.xzkj.pixelmonsm.data.listdata.PTData;
import com.xzkj.pixelmonsm.data.listdata.PokPT;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PokTool {

    //获取玩家首位精灵

    public static Pokemon getPlayerPokemon0(Player p) {
        PlayerPartyStorage playerStorage = Pixelmon.storageManager.getParty(p.getUniqueId());
        return playerStorage.get(0);
    }

    //获取精灵总点数

    public static int getPokmonPT(Player p, String PokName) {
        int POK_PT = 0, POK_EVS_PT = 0, POK_IVS_PT = 0, POK_LEVEL_PT = 0, POK_RARE_PT = 0;
        for (PokPT data : PTData.POK_CONFIG) {
            if (data.POK_NAME.equalsIgnoreCase(PokName)) {
                POK_PT = Integer.valueOf(data.POK_PT);
                POK_EVS_PT = Integer.valueOf(data.POK_EVS_PT);
                POK_IVS_PT = Integer.valueOf(data.POK_IVS_PT);
                POK_LEVEL_PT = Integer.valueOf(data.POK_LEVEL_PT);
                POK_RARE_PT = Integer.valueOf(data.POK_RARE_PT);
            }

        }
        Pokemon Pokemon0 = getPlayerPokemon0(p);
        POK_LEVEL_PT = Pokemon0.getLevel() * POK_LEVEL_PT;
        int Evs = Pokemon0.getEVs().attack + Pokemon0.getEVs().speed + Pokemon0.getEVs().defence +
                Pokemon0.getEVs().specialDefence + Pokemon0.getEVs().specialAttack + Pokemon0.getEVs().hp;
        POK_EVS_PT = Evs * POK_EVS_PT;
        int Ivs = Pokemon0.getIVs().attack + Pokemon0.getIVs().speed + Pokemon0.getIVs().defence +
                Pokemon0.getIVs().specialDefence + Pokemon0.getIVs().specialAttack + Pokemon0.getIVs().hp;
        POK_IVS_PT = Ivs * POK_IVS_PT;

        return POK_PT + POK_EVS_PT + POK_IVS_PT + POK_LEVEL_PT + POK_RARE_PT;

    }
}
