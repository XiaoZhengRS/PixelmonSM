package com.xzkj.pixelmonsm.utils.player;

import com.xzkj.pixelmonsm.data.FileData;
import org.bukkit.entity.Player;

import java.io.IOException;


public class PlayerTool {
    //设置玩家剩余融合点数

    public static void setPlayerPT(Player p, int PT) {
        FileData data = new FileData();
        data.PlayerPTDataYaml.set(p.getName() + ".PT", PT);
        try {
            data.PlayerPTDataYaml.save(data.PlayerPTData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //增减玩家剩余融合点数

    public static void givePlayerPT(Player p, int PT) {
        FileData data = new FileData();
        int data_PT = data.PlayerPTDataYaml.getInt(p.getName() + ".PT");
        data.PlayerPTDataYaml.set(p.getName() + ".PT", PT + data_PT);
        try {
            data.PlayerPTDataYaml.save(data.PlayerPTData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取玩家剩余融合点数

    public static int givePlayerPT(Player p) {
        FileData data = new FileData();
        return data.PlayerPTDataYaml.getInt(p.getName() + ".PT");
    }


}
