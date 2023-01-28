package com.example.project3.Pojo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @NotNull(message = "ID can not be empty!")
    @Size(min = 3,max = 10 , message = "ID should be 3 character or longer ")
    private String id;
    //---------------------------------
    @NotNull(message = "ID can not be null!")
    @Size(min = 3,max = 10 , message = "Name should be 3 character or longer ")
    private String name;
//--------------------------------
    @Positive(message = "price must be positive number only")
    @NotNull(message = "price cannot be null!")
    private int price;
//---------------------------------
    @NotNull(message = "Category ID cannot be empty!")
    @Size(min = 3, max = 10 ,message = "Category ID should be between(3-10) character")
    private String categoryId;


}
