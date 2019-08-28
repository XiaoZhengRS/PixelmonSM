package com.xzkj.pixelmonsm.data.listdata;

import java.util.List;

public class PokShopGood {
    public String ShopName;
    public Integer ShopPrice;
    public String ShopText;
    public List<String> ShopCmd;
    public Boolean ShowGG;
    public PokShopGood(String ShopName, Integer ShopPrice, String ShopText, List<String> ShopCmd, Boolean ShowGG){
        this.ShopName = ShopName;
        this.ShopPrice = ShopPrice;
        this.ShopText = ShopText;
        this.ShopCmd = ShopCmd;
        this.ShowGG = ShowGG;
    }
}
