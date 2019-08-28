package com.xzkj.pixelmonsm.web;

import com.xzkj.pixelmonsm.PixelmonSM;
import com.xzkj.pixelmonsm.web.tool.HttpTool;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.net.URLEncoder;

public class PluginX {
    public static String C_url = ServerTool.getServer(PixelmonSM.serverID);
    public static String C_login = "32f66000290b9356";
    public static String C_Core = "67be9d1c47673422";
    public static String C_Var = "dc0bf121c3b34198";
    public static String C_OL = "3421ca137853018d";
    public static String C_User = "60ee53a590d9cc4e";
    public static String C_log = "7e2e8056e29e682c";
    public static String C_RES = "f321930db337a4a6";
    public static String C_X = "e2deb58a216320db";

    //获取MAC

    public static List<String> getMAC() throws Exception{
        java.util.Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
        StringBuilder sb = new StringBuilder();
        ArrayList<String> tmpMacList=new ArrayList<>();
        while(en.hasMoreElements()){
            NetworkInterface iface = en.nextElement();
            List<InterfaceAddress> addrs = iface.getInterfaceAddresses();
            for(InterfaceAddress addr : addrs) {
                InetAddress ip = addr.getAddress();
                NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                if(network==null){continue;}
                byte[] mac = network.getHardwareAddress();
                if(mac==null){continue;}
                sb.delete( 0, sb.length() );
                for (int i = 0; i < mac.length; i++) {sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));}
                tmpMacList.add(sb.toString());
            }        }
        if(tmpMacList.size()<=0){return tmpMacList;}
        /***去重，别忘了同一个网卡的ipv4,ipv6得到的mac都是一样的，肯定有重复，下面这段代码是。。流式处理***/
        List<String> unique = tmpMacList.stream().distinct().collect(Collectors.toList());
        return unique;
    }

    //登录密码卡
    public static String PluginLogin(String key, String version, String MAC) {
        String Status_Code = HttpTool.sendPost
                (C_url + "/" + C_login, "A=" + key + "&B=" + version + "&C=" + MAC);
        return Status_Code;
    }

    //获取核心数据

    public static String PluginCore(String Status_Code, String key) {
        String Core = HttpTool.sendPost
                (C_url + "/" + C_Core, "A=" + Status_Code + "&B=" + key);
        return Core;
    }

    //获取变量数据

    public static String PluginVar(String Status_Code, String key, String VarID, String VarName) {
        String Var = HttpTool.sendPost
                (C_url + "/" + C_Var, "A=" + Status_Code + "&B=" + key + "&C=" + VarID + "&D=" + VarName);
        return Var;
    }
    //获取在线人数

    public static String PluginOL(String Status_Code, String UserVar) {
        String OL = HttpTool.sendPost
                (C_url + "/" + C_OL, "A=" + Status_Code + "&B=" + UserVar);
        return OL;
    }
    //获取用户到期时间

    public static String PluginUser(String Key) {
        String date = HttpTool.sendPost
                (C_url + "/" + C_User, "A=" + Key);
        return date;
    }

    //用户退出

    public static String ResUser(String Status_Code, String Key) {
        String date = HttpTool.sendPost
                (C_url + "/" + C_RES, "A=" + Status_Code + "B=" + Key);
        return date;
    }

    //用户状态

    public static String UserStatus(String Status_Code, String Key) {
        String date = HttpTool.sendPost
                (C_url + "/" + C_X, "StatusCode=" + Status_Code + "&UserName=" + Key);
        return date;
    }
    //获取公告

    public static String PluginLog() {
        String date = HttpTool.sendPost
                (C_url + "/" + C_log, "");
        String utf8 = null;
        String gbk = null;
        String unicode = null;
        try {
            unicode = new String(date.getBytes(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            gbk = new String(unicode.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return gbk;
    }

    //测试

    public static void main(String[] args) {
        String key = "";
        C_url = ServerTool.getServer(1);
        C_Core = "";
        C_login = "";
        C_OL = "";
        C_User = "";
        C_Var = "";
        C_log = "";
        String MAC = null;
        try {
            MAC = getMAC().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //登录
        String Status_code = PluginLogin(key, "1", MAC);
        System.out.println("登录返回状态码:" + Status_code.length());
        System.out.println("云端核心数据:" + PluginCore(Status_code, key));
        System.out.println("云端公告:" + PluginLog());
        System.out.println("云端变量数据:" + PluginVar(Status_code, key, "22860", "1"));
        System.out.println("插件在线人数:" + PluginOL(Status_code, key));
        System.out.println("用户到期时间:" + PluginUser(key));
    }
}
