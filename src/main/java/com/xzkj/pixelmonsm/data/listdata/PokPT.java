package com.xzkj.pixelmonsm.data.listdata;

import java.util.List;

public class PokPT {
    public String POK_NAME;
    public String POK_PT;
    public String POK_IVS_PT;
    public String POK_EVS_PT;
    public String POK_LEVEL_PT;
    public String POK_RARE_PT;
    public List<String> POK_CMD;
    public Boolean POK_MEALL;
    public Boolean POK_MEALL_VV;
    public String POK_MEALL_TEXT;
    public PokPT(String POK_NAME, String POK_PT, String POK_IVS_PT, String POK_EVS_PT, String POK_LEVEL_PT,
                 String POK_RARE_PT, List<String> POK_CMD, Boolean POK_MEALL, Boolean POK_MEALL_VV,
                 String POK_MEALL_TEXT){
        this.POK_NAME = POK_NAME;
        this.POK_PT = POK_PT;
        this.POK_IVS_PT = POK_IVS_PT;
        this.POK_EVS_PT = POK_EVS_PT;
        this.POK_LEVEL_PT = POK_LEVEL_PT;
        this.POK_RARE_PT = POK_RARE_PT;
        this.POK_CMD = POK_CMD;
        this.POK_MEALL = POK_MEALL;
        this.POK_MEALL_VV = POK_MEALL_VV;
        this.POK_MEALL_TEXT = POK_MEALL_TEXT;
    }
}
