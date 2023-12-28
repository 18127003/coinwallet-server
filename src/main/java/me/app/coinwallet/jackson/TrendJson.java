package me.app.coinwallet.jackson;

import java.util.ArrayList;
import java.util.List;

public class TrendJson {
    List<String> coins=new ArrayList<>();

   public void addCoin(String id){
       coins.add(id);
   }

    public List<String> getCoins() {
        return coins;
    }
}
