package com.xzkj.pixelmonsm.vv.view;

import com.xzkj.pixelmonsm.data.CoreData;
import com.xzkj.pixelmonsm.data.listdata.MaterialData;
import com.xzkj.pixelmonsm.data.listdata.PokShop;
import com.xzkj.pixelmonsm.data.listdata.PokShopGood;
import com.xzkj.pixelmonsm.utils.MessTool;
import com.xzkj.pixelmonsm.utils.player.PlayerTool;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShopVex {
    File Config = new File(CoreData.PLUGIN_FILE.getAbsolutePath() + "\\VexConfig.yml");
    YamlConfiguration yaml_Config = YamlConfiguration.loadConfiguration(Config);
    public VexGui getShopGui(Player p) {
        VexGui vexGui = new VexGui(MaterialData.SHOP_BJ, 50, 20, 577, 594, 480, 550);
        VexScrollingList vexlist = new VexScrollingList(
                yaml_Config.getInt("VexShop.x"),
                yaml_Config.getInt("VexShop.y"),
                yaml_Config.getInt("VexShop.w"),
                yaml_Config.getInt("VexShop.h"),
                yaml_Config.getInt("VexShop.f"));

        int py = 0;
        int butt = 0;
        int buttx = yaml_Config.getInt("VexShop.butt.x");
        int butty = yaml_Config.getInt("VexShop.butt.y");
        for (PokShopGood data : PokShop.POKLIST) {
            vexlist.addComponent(new VexImage(
                    MaterialData.SHOP_FK,
                    yaml_Config.getInt("VexShop.img.x"),
                    yaml_Config.getInt("VexShop.img.y") + py,
                    yaml_Config.getInt("VexShop.img.xs"),
                    yaml_Config.getInt("VexShop.img.ys")));



            vexlist.addComponent(new VexButton(
                    butt,
                    "",
                    MaterialData.SHOP_BU,
                    MaterialData.SHOP_BU,
                    buttx,
                    butty + py,
                    yaml_Config.getInt("VexShop.butt.w"),
                    yaml_Config.getInt("VexShop.butt.h"), new ButtonFunction() {
                @Override
                public void run(Player player) {
                    if (PlayerTool.givePlayerPT(p) >= data.ShopPrice) {
                        //进入兑换
                        PlayerTool.givePlayerPT(p, data.ShopPrice * -1);
                        for (String dataset : data.ShopCmd) {
                            dataset = dataset.replace("{player}", p.getName());
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), dataset);
                        }
                        MessTool.logPlayer("物品发放完成!", p);
                        if (data.ShowGG){
                            MessTool.logVV("§4恭喜玩家兑换物品: " + data.ShopName + "--" + data.ShopText + "--成功!",true);
                        }
                        p.closeInventory();
                    } else {
                        //无法兑换
                        MessTool.logPlayer("当前PT点数不足无法兑换!", p);
                        p.closeInventory();
                    }
                }
            }));
            List<String> dataliststr = new ArrayList<>();
            List<String> dataliststr1 = new ArrayList<>();
            List<String> dataliststr2 = new ArrayList<>();
            dataliststr2.add(data.ShopText);
            dataliststr.add(data.ShopName);
            dataliststr1.add("所需:" + data.ShopPrice);
            vexlist.addComponent(new VexText(
                    yaml_Config.getInt("VexShop.text0.x"),
                    yaml_Config.getInt("VexShop.text0.y") + py,
                    dataliststr,new VexHoverText(dataliststr2)));
            vexlist.addComponent(new VexText(
                    yaml_Config.getInt("VexShop.text1.x"),
                    yaml_Config.getInt("VexShop.text1.y") + py,
                    dataliststr1));
            py = py + yaml_Config.getInt("VexShop.偏移");
            butt = butt + 1;
        }


        vexGui.addComponent(vexlist);
        return vexGui;
    }
}
