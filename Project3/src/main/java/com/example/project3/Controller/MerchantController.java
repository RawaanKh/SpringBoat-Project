package com.example.project3.Controller;

import com.example.project3.Pojo.Merchant;
import com.example.project3.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/E-Commerce/merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getMerchant(){
        ArrayList<Merchant> merchant=merchantService.getMerchants();
        return ResponseEntity.status(200).body(merchant);
    }
    @PostMapping("/add")
    public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant, Errors errors){
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body("Merchant Added!");
    }
    public MerchantService getMerchantService() {
        return merchantService;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable String id, @Valid@RequestBody Merchant merchant, Errors errors){
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated= merchantService.updateMerchant(id,merchant);
        if (isUpdated){
            return ResponseEntity.status(200).body("Merchant Updated!");
        }
        return ResponseEntity.status(400).body("Wrong id");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id){
        boolean idDeleted= merchantService.deleteMerchant(id);
        if(idDeleted){
            return ResponseEntity.status(200).body("Merchant Deleted!");
        }
        return ResponseEntity.status(400).body("Wrong id!");
    }
}
