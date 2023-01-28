package com.example.project3.Service;
import com.example.project3.Pojo.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UserService {
    ArrayList<User> users = new ArrayList<>();
    private final UserService userService;
    private final MerchantStockService merchantStockService;
    private final ProductService productService;
    private final MerchantService merchantService;


    public ArrayList<User>getUsers(){
        return users;
    }
    public void addUser(User user){
        users.add(user);
    }
    public boolean updateUser(String id,User user){
        for (int i = 0; i <users.size() ; i++) {
            if(users.get(i).getId()==id)
                users.set(i,user);
            return true;
        }
        return false;
    }
    public boolean deleteUser(String id){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId()==id) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean addProductToStuck(String productId,String merchantId,int stock){

        if (stock>=1) {    // to check if there is existing stock
            for (int i = 0; i < merchantStockService.merchantStocks.size(); i++) {
                if (merchantStockService.merchantStocks.get(i).getProductId().equals(productId) &&
                        merchantStockService.merchantStocks.get(i).getMerchantId().equals(merchantId)) {
                    int existStock = merchantStockService.merchantStocks.get(i).getStock();
                    merchantStockService.merchantStocks.get(i).setStock(stock + existStock);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean buyProduct(String userId,String productId,String merchantId){
        //1 + 2
        //check user,product and merchant ID & if Stock greater than 1
       for(User user:users) if (user.getId().equals(userId)) {
           for (int i = 0; i < merchantStockService.merchantStocks.size(); i++) {
               if (merchantStockService.merchantStocks.get(i).getProductId().equals(productId)&&
                       merchantStockService.merchantStocks.get(i).getMerchantId().equals(merchantId)&&
                       merchantStockService.merchantStocks.get(i).getStock()>=1)
                       {
                          int price= productService.products.get(i).getPrice();
                           int balance=userService.users.get(i).getBalance();
                           //4 if user have a balance
                           if (userService.users.get(i).getBalance()>=productService.products.get(i).getPrice())
                              balance -= price;
                           //3 reduce the stock
                           int allStock= merchantStockService.merchantStocks.get(i).getStock();
                           allStock-=1;

               }
           }
           return true;
       }
        return false;
    }

}
