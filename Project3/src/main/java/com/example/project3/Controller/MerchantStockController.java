package com.example.project3.Controller;

import com.example.project3.Pojo.Merchant;
import com.example.project3.Pojo.MerchantStock;
import com.example.project3.Service.MerchantService;
import com.example.project3.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/E-Commerce/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity geMerchantStock(){
        ArrayList<MerchantStock> merchantStocks=merchantStockService.getMerchantStocks();
        return ResponseEntity.status(200).body(merchantStocks);
    }
    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body("Merchant Stock Added!");
    }
    public MerchantStockService getMerchantStockService() {
        return merchantStockService;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable String id, @Valid@RequestBody MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated= merchantStockService.updateMerchantStock(id,merchantStock);
        if (isUpdated){
            return ResponseEntity.status(200).body("Merchant stock Updated!");
        }
        return ResponseEntity.status(400).body("Wrong id");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id){
        boolean idDeleted= merchantStockService.deleteMerchantStock(id);
        if(idDeleted){
            return ResponseEntity.status(200).body("Merchant stock Deleted!");
        }
        return ResponseEntity.status(400).body("Wrong id!");
    }



}
