package com.xzkj.pixelmonsm.data.listdata;

import com.xzkj.pixelmonsm.data.CoreData;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PokShop {
    public static List<PokShopGood> POKLIST = new ArrayList<>();

    public static List<String> POKNAME_LIST = new ArrayList<>();
    //初始化读取配置

    public static void a(){

        File pokConfig = new File(CoreData.PLUGIN_FILE.getAbsolutePath() + "\\pokShop.yml");
        YamlConfiguration yaml_pokConfig = YamlConfiguration.loadConfiguration(pokConfig);
        PokShop.POKNAME_LIST = yaml_pokConfig.getStringList("兑换列表");
        for (String strdata : PokShop.POKNAME_LIST) {
            PokShop.POKLIST.add(new PokShopGood(strdata, yaml_pokConfig.getInt(strdata + ".需要点数"),
                    yaml_pokConfig.getString(strdata + ".介绍"),
                    yaml_pokConfig.getStringList(strdata + ".购买后执行的cmd"),
                    yaml_pokConfig.getBoolean(strdata + ".兑换后是否公告")));
        }
    }
}
