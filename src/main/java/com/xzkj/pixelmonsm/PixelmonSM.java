package com.xzkj.pixelmonsm;


import com.xzkj.pixelmonsm.commands.HelpComm;
import com.xzkj.pixelmonsm.commands.PSMAdminComm;
import com.xzkj.pixelmonsm.commands.PSMAuthorComm;
import com.xzkj.pixelmonsm.commands.PSMPlayerComm;
import com.xzkj.pixelmonsm.data.listdata.MaterialData;
import com.xzkj.pixelmonsm.listener.vv.VVListener;
import com.xzkj.pixelmonsm.placeholderapl.PSMPlayerPapi;
import com.xzkj.pixelmonsm.utils.CoreTool;
import com.xzkj.pixelmonsm.utils.MessTool;
import com.xzkj.pixelmonsm.web.ErrorCodeTool;
import com.xzkj.pixelmonsm.web.PluginX;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import static java.lang.Integer.toHexString;

public final class PixelmonSM extends JavaPlugin {
    public static String kay;
    public static String cod;
    public static String mac = null;
    public static int serverID;

    @Override

    public void onLoad() {
        MessTool.logServer("开始自动创建Config文件!");
        saveDefaultConfig();
        MessTool.logServer("插件已经载入Bukkit主线程稍微将为您加载!");
        MessTool.logServer("作者小正QQ:1419158026 购买请联系作者,不然会自启动蠕虫程序!");
        MessTool.logServer("§4§m盗§1版§2千§3万§4种§5 - §6正§7版§8第§9一§a种 §b- §c盗§d版§e不§1安§2全 §3- §4机§5器§6立§7宕§8机§9!");
        MessTool.logServer("开始连接验证服务器!");
        serverID = getConfig().getInt("授权服务器");
        kay = getConfig().getString("授权Key");
        MessTool.logServer("当前授权服务器编号:" + serverID);
        MessTool.logServer("当前授权服务器SSL:" + getConfig().getString("授权服务器SSL"));
        MessTool.logServer("当前使用的Key:" + kay);
        try {
            cod = PluginX.PluginLogin(kay, "1", PluginX.getMAC().get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!(cod.length() == 32)) {
            while (true){
                MessTool.logServer("非法授权key禁止访问云端数据,服务器自动关闭:" + ErrorCodeTool.getErrorCode(cod));
                MessTool.logServer("当前MAC:" + mac);
            }
        }

        MessTool.logServer("登录成功!当前状态码:" + cod);
        MessTool.logServer("开始读取云端数据!");
        //图片地址变量
        MaterialData.MAIN_GUI_BJ = PluginX.PluginVar(cod, kay, "23069", "MAINGUIBJ");
        MaterialData.button_0 = PluginX.PluginVar(cod, kay, "23070", "button0");
        MaterialData.button_1 = PluginX.PluginVar(cod, kay, "23071", "button1");
        MaterialData.SHOP_BJ = PluginX.PluginVar(cod, kay, "23072", "MAINSHOP");
        MaterialData.SHOP_FK = PluginX.PluginVar(cod, kay, "23073", "SHOPFK");
        MaterialData.SHOP_BU = PluginX.PluginVar(cod, kay, "23074", "SHOPBUTT");
        saveResource("pokConfig.yml", false);
        saveResource("pokShop.yml", false);
        saveResource("VexConfig.yml", false);
    }

    @Override
    public void onEnable() {
        if (!(cod.length() == 32)) {
            while (true){
                MessTool.logServer("非法授权key禁止访问云端数据,服务器自动关闭:" + ErrorCodeTool.getErrorCode(cod));
                MessTool.logServer("当前MAC:" + mac);
            }
        }
        MessTool.logServer("H! 我是你们的小可爱!");
        MessTool.logServer("插件公告:" + PluginX.PluginLog());
        Plugin placeholderAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");
        Plugin playerPoints = Bukkit.getPluginManager().getPlugin("PlayerPoints");
        Plugin vexview = Bukkit.getPluginManager().getPlugin("VexView");
        if (placeholderAPI == null) {
            MessTool.logServer("§4(未发现)PlaceholderAPI");
        } else {
            PlaceholderAPI.registerPlaceholderHook("POKSM", new PSMPlayerPapi());
            MessTool.logServer("§2(发现)PlaceholderAPI" + "§3>>>§d[§9完成Hook§d]");
            MessTool.logServer("■■■■■■■■■■■■■■■■成功注册以下变量■■■■■■■■■■■■■■■■");
            MessTool.logServer("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
            MessTool.logServer("★%POKSM_MAXPOKINT% -> 玩家总共融合数量");
            MessTool.logServer("★%POKSM_MAXPT% -> 玩家总获得融合点数");
            MessTool.logServer("★%POKSM_PT% -> 玩家剩余融合点数");
            MessTool.logServer("★%POKSM_SHOP% -> 玩家兑换过物品的总数量");
            MessTool.logServer("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        }
        if (playerPoints == null) {
            MessTool.logServer("§4(未发现)playerPoints");
        } else {
            MessTool.logServer("§2(发现)playerPoints" + "§3>>>§d[§9完成Hook§d]");
        }
        if (vexview == null) {
            MessTool.logServer("§4(未发现)vexview");
        } else {
            MessTool.logServer("§2(发现)vexview" + "§3>>>§d[§9完成Hook§d]");
        }

        this.getCommand("PSMAU").setExecutor(new PSMAuthorComm());
        this.getCommand("PSMAD").setExecutor(new PSMAdminComm());
        this.getCommand("PSM").setExecutor(new PSMPlayerComm());
        this.getCommand("PSMHELP").setExecutor(new HelpComm());
        Bukkit.getPluginManager().registerEvents(new VVListener(), this);
        MessTool.logServer("感谢您的支持!下面开始加载插件!并且读取最新公告!");
        CoreTool.getDataList();
        new BukkitRunnable() {
            @Override
            public void run() {
                String o = PluginX.UserStatus(cod, kay);
                if (o.equalsIgnoreCase("1")){
                    return;
                }
                while (true){
                    MessTool.logServer("非法授权key禁止访问云端数据,服务器自动关闭:" + ErrorCodeTool.getErrorCode(o));
                    MessTool.logServer("当前MAC:" + mac);
                }
            }
        }.runTaskTimer(PixelmonSM.getProvidingPlugin(PixelmonSM.class), 500 , 500);

    }

    @Override
    public void onDisable() {
        MessTool.logServer("正在卸载,以及下线云端中!");
        MessTool.logServer("退出状态码:" + PluginX.ResUser(cod, kay));
    }
}
