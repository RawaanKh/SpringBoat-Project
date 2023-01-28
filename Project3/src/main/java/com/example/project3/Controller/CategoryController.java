package com.example.project3.Controller;

import com.example.project3.Pojo.Category;
import com.example.project3.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/E-Commerce/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/get")
    public ResponseEntity getCategory(){
        ArrayList<Category> category=categoryService.getCategories();
        return ResponseEntity.status(200).body(category);

    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors){
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body("Category Added!");
    }
    public CategoryService getCategoryService() {
        return categoryService;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable String id, @Valid@RequestBody Category category, Errors errors){
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated= categoryService.updateCategory(id,category);
        if (isUpdated){
            return ResponseEntity.status(200).body("Category Updated!");
        }
        return ResponseEntity.status(400).body("Wrong id");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id){
        boolean idDeleted= categoryService.deleteCategory(id);
        if(idDeleted){
            return ResponseEntity.status(200).body("Category Deleted!");
        }
        return ResponseEntity.status(400).body("Wrong id!");
    }


}
