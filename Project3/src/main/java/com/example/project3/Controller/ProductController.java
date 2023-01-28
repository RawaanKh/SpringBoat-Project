package com.example.project3.Controller;

import com.example.project3.Pojo.Product;
import com.example.project3.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/E-Commerce/product")
@RequiredArgsConstructor
public class ProductController {
private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getProduct(){
        ArrayList<Product> product=productService.getProducts();
        return ResponseEntity.status(200).body(product);

    }
    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors){
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        productService.addProduct(product);
        return ResponseEntity.status(200).body("Product Added!");
    }
    public ProductService getProductService() {
        return productService;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable String id, @Valid@RequestBody Product product, Errors errors){
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated= productService.updateProduct(id,product);
        if (isUpdated){
            return ResponseEntity.status(200).body("Product Updated!");

        }
        return ResponseEntity.status(400).body("Wrong id");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBlog(@PathVariable String id){
        boolean idDeleted= productService.deleteProduct(id);
        if(idDeleted){
            return ResponseEntity.status(200).body("Product Deleted!");
        }
        return ResponseEntity.status(400).body("Wrong id!");
    }

}
