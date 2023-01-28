package com.example.project3.Service;


import com.example.project3.Pojo.MerchantStock;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class MerchantStockService {

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();
    public ArrayList<MerchantStock>getMerchantStocks(){
        return merchantStocks;
    }
    public void addMerchantStock(MerchantStock merchantStock){
        merchantStocks.add(merchantStock);
    }
    public boolean updateMerchantStock(String id,MerchantStock merchantStock){
        for (int i = 0; i <merchantStocks.size() ; i++) {
            if(merchantStocks.get(i).getId()==id)
                merchantStocks.set(i,merchantStock);
            return true;
        }
        return false;
    }
    public boolean deleteMerchantStock(String id){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId()==id) {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }

}
