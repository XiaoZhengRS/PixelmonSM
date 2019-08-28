package com.xzkj.pixelmonsm.vv;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import com.xzkj.pixelmonsm.data.CoreData;
import com.xzkj.pixelmonsm.data.listdata.MaterialData;
import com.xzkj.pixelmonsm.data.listdata.PTData;
import com.xzkj.pixelmonsm.data.listdata.PokPT;
import com.xzkj.pixelmonsm.utils.MessTool;
import com.xzkj.pixelmonsm.utils.PokTool;
import com.xzkj.pixelmonsm.utils.player.PlayerTool;
import com.xzkj.pixelmonsm.vv.view.ShopVex;
import lk.vexview.api.VexViewAPI;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.ButtonFunction;
import lk.vexview.gui.components.VexButton;
import lk.vexview.gui.components.VexText;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vex {
    File Config = new File(CoreData.PLUGIN_FILE.getAbsolutePath() + "\\VexConfig.yml");
    YamlConfiguration yaml_Config = YamlConfiguration.loadConfiguration(Config);
    //主界面

    public VexGui gitVexPlayerMain(Player p) {
        VexGui vexgui = new VexGui(MaterialData.MAIN_GUI_BJ, 50, 20, 800, 600, 800, 600);
        if (PTData.isPlayeRH(PokTool.getPlayerPokemon0(p).getDisplayName())) {
            vexgui.addComponent(new VexButton(0, "融合精灵", MaterialData.button_0, MaterialData.button_1,
                    yaml_Config.getInt("VexViewMain.butt0.x"), yaml_Config.getInt("VexViewMain.butt0.y"),
                    yaml_Config.getInt("VexViewMain.butt0.w"), yaml_Config.getInt("VexViewMain.butt0.h"), new ButtonFunction() {
                @Override
                public void run(Player player) {
                    int dataPT = PokTool.getPokmonPT(p, PokTool.getPlayerPokemon0(p).getDisplayName());
                    PlayerPartyStorage playerStorage = Pixelmon.storageManager.getParty(p.getUniqueId());
                    for (PokPT datad: PTData.POK_CONFIG){
                        if (datad.POK_NAME.equalsIgnoreCase(PokTool.getPlayerPokemon0(p).getDisplayName())) {
                            if(datad.POK_MEALL){
                                String datadaset = datad.POK_MEALL_TEXT;
                                datadaset.replace("{player}",p.getName());
                                datadaset.replace("{pokname}",PokTool.getPlayerPokemon0(p).getDisplayName());
                                datadaset.replace("{pokpt}", String.valueOf(dataPT));
                                MessTool.logVV(datadaset,false);
                                if (datad.POK_MEALL_VV){
                                    MessTool.logVV(datadaset,true);
                                }
                            }
                        }
                    }
                    playerStorage.set(0, null);
                    PlayerTool.givePlayerPT(p, dataPT);
                    MessTool.logPlayer("已经完成融炼", p);
                    p.closeInventory();
                }
            }));
            //玩家精灵总点数
            int dataPT = PokTool.getPokmonPT(p, PokTool.getPlayerPokemon0(p).getDisplayName());
            List<String> dataPTT = new ArrayList<>();
            dataPTT.add(String.valueOf(dataPT));
            vexgui.addComponent(new VexText(
                    yaml_Config.getInt("VexViewMain.text0.x"),
                    yaml_Config.getInt("VexViewMain.text0.y"),
                    dataPTT,
                    Double.valueOf(yaml_Config.getInt("VexViewMain.text0.s"))));
        } else {
            vexgui.addComponent(new VexButton(0, "不可以融合",
                    MaterialData.button_0, MaterialData.button_1,
                    yaml_Config.getInt("VexViewMain.butt0.x"),
                    yaml_Config.getInt("VexViewMain.butt0.y"),
                    yaml_Config.getInt("VexViewMain.butt0.w"),
                    yaml_Config.getInt("VexViewMain.butt0.h")));
            List<String> dataPTT = new ArrayList<>();
            dataPTT.add("无法融合此精灵");
            vexgui.addComponent(new VexText(
                    yaml_Config.getInt("VexViewMain.text0.x"),
                    yaml_Config.getInt("VexViewMain.text0.y"),
                    dataPTT,
                    Double.valueOf(yaml_Config.getInt("VexViewMain.text0.s"))));
        }
        vexgui.addComponent(new VexButton(
                1,
                "打开兑换界面",
                MaterialData.button_0,
                MaterialData.button_1,
                yaml_Config.getInt("VexViewMain.butt1.x"),
                yaml_Config.getInt("VexViewMain.butt1.y"),
                yaml_Config.getInt("VexViewMain.butt1.w"),
                yaml_Config.getInt("VexViewMain.butt1.h"),
                new ButtonFunction() {
            @Override
            public void run(Player player) {
                p.closeInventory();
                VexViewAPI.openGui(p, new ShopVex().getShopGui(p));
            }
        }
        ));
        return vexgui;
    }

}
