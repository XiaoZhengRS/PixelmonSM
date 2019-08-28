package com.xzkj.pixelmonsm.data;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class FileData {
    public File PlayerPTData = new File(CoreData.PLUGIN_FILE.getAbsolutePath() + "\\PlayerDataPT.yml");;
    public YamlConfiguration PlayerPTDataYaml = YamlConfiguration.loadConfiguration(PlayerPTData);
}
