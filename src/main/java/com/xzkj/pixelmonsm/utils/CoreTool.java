package com.xzkj.pixelmonsm.utils;

import com.xzkj.pixelmonsm.data.CoreData;
import com.xzkj.pixelmonsm.data.listdata.PTData;
import com.xzkj.pixelmonsm.data.listdata.PokPT;
import com.xzkj.pixelmonsm.data.listdata.PokShop;
import com.xzkj.pixelmonsm.data.listdata.PokShopGood;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class CoreTool {
    //显示缓存信息

    public static void getDataList() {
        PTData.b();
        PokShop.a();
        for (PokPT data : PTData.POK_CONFIG) {
            MessTool.logServer("精灵名称: > " + data.POK_NAME + " <");
            MessTool.logServer("精灵基本点数: > " + data.POK_PT + " <");
            MessTool.logServer("精灵IVS点数: > " + data.POK_IVS_PT + " <");
            MessTool.logServer("精灵EVS点数: > " + data.POK_EVS_PT + " <");
            MessTool.logServer("精灵等级点数: > " + data.POK_LEVEL_PT + " <");
            MessTool.logServer("精灵稀有程度点数: > " + data.POK_RARE_PT + " <");
            MessTool.logServer("===============================================");
            for (String data0 : data.POK_CMD) {
                MessTool.logServer("> " + data0 + " <");
            }
            MessTool.logServer("===============================================");
            MessTool.logServer("精灵熔炼后是否公告: > " + data.POK_MEALL + " <");
            MessTool.logServer("精灵熔炼后是否使用VV公告: > " + data.POK_MEALL_VV + " <");
            MessTool.logServer("精灵熔炼后公告内容: > " + data.POK_MEALL_TEXT + " <");
            MessTool.logServer("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        }
        for (PokShopGood data : PokShop.POKLIST) {
            MessTool.logServer("商品名称: > " + data.ShopName + " <");
            MessTool.logServer("商品点数: > " + data.ShopPrice + " <");
            MessTool.logServer("商品介绍: > " + data.ShopText + " <");
            for (String data0: data.ShopCmd){
                MessTool.logServer("商品cmd: > " + data0 + " <");
            }
            MessTool.logServer("商品兑换后是否公告: > " + data.ShowGG + " <");
            MessTool.logServer("===============================================");
        }
    }

}
