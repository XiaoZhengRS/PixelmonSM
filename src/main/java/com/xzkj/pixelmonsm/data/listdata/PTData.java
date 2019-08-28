package com.xzkj.pixelmonsm.data.listdata;

import com.xzkj.pixelmonsm.data.CoreData;
import com.xzkj.pixelmonsm.utils.MessTool;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PTData {
    //融合合集

    public static List<PokPT> POK_CONFIG = new ArrayList<>();

    //可融合列表

    public static List<String> POK_NAME_LIST  = new ArrayList<>();

    public static void b(){
        //初始化读取配置
        File pokConfig1 = new File(CoreData.PLUGIN_FILE.getAbsolutePath() + "\\pokConfig.yml");
        YamlConfiguration yaml_pokConfig1 = YamlConfiguration.loadConfiguration(pokConfig1);
        PTData.POK_NAME_LIST = yaml_pokConfig1.getStringList("支持熔炼的精灵列表");
        for (String data : PTData.POK_NAME_LIST) {
            MessTool.logServer(data);
        }
        for (String data : PTData.POK_NAME_LIST) {
            PTData.POK_CONFIG.add(new PokPT(data, yaml_pokConfig1.getString(data + ".基础点数"),
                    yaml_pokConfig1.getString(data + ".ivs基数"),
                    yaml_pokConfig1.getString(data + ".evs基数"),
                    yaml_pokConfig1.getString(data + ".等级基数"),
                    yaml_pokConfig1.getString(data + ".稀有程度基数"),
                    yaml_pokConfig1.getStringList(data + ".熔炼后的额外指令"),
                    yaml_pokConfig1.getBoolean(data + ".熔炼后是否全服公告.开关"),
                    yaml_pokConfig1.getBoolean(data + ".熔炼后是否全服公告.启用VV滚动"),
                    yaml_pokConfig1.getString(data + ".熔炼后是否全服公告.公告内容")));
        }
    }

    //获取是否可融合

    public static Boolean isPlayeRH(String POKName) {
        for (String data : POK_NAME_LIST) {
            if (data.equalsIgnoreCase(POKName)){
                return true;
            }
        }
        return false;
    }

}
