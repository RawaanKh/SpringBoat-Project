package com.example.project3.Controller;


import com.example.project3.Pojo.User;
import com.example.project3.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/E-Commerce/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity getUser(){
        ArrayList<User> user=userService.getUsers();
        return ResponseEntity.status(200).body(user);
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("User Added!");
    }
    public UserService getUserService() {
        return userService;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @Valid@RequestBody User user, Errors errors){
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated= userService.updateUser(id,user);
        if (isUpdated){
            return ResponseEntity.status(200).body("User Updated!");
        }
        return ResponseEntity.status(400).body("Wrong id");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        boolean idDeleted= userService.deleteUser(id);
        if(idDeleted){
            return ResponseEntity.status(200).body("User Deleted!");
        }
        return ResponseEntity.status(400).body("Wrong id!");
    }
    @PutMapping("/stock/add/{merchantId}/{productId}/{stock}")
    public ResponseEntity addProductToStuck(@PathVariable String merchantId, @PathVariable String productId, @PathVariable int stock){
        boolean isAdded=userService.addProductToStuck(productId,merchantId,stock);
        if (isAdded){
            return ResponseEntity.status(200).body("product added to the stock !");
        }
        return ResponseEntity.status(400).body("cannot found stock!");
    }

@PutMapping("/buy/{userId}/{merchantId}/{productId}")
    public ResponseEntity buyProduct(@PathVariable String userId,@PathVariable String productId,@PathVariable String merchantId){
        boolean isPurchased=userService.buyProduct(userId,productId,merchantId);
        if (isPurchased) {
            return ResponseEntity.status(200).body("Purchased done successfully !");
        }
        return ResponseEntity.status(400).body("Id not exist or balance less than price!");
}

}
