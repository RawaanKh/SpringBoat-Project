package com.example.project3.Pojo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotEmpty(message = "Id cannot be empty")
    @Size(min = 3, message = "ID should be 3 character or longer ")
    private String id;
    //--------------------------
    @NotEmpty(message = "product id cannot be empty")
    @Size(min = 3, message = "product id should be 3 character or longer")
    private String productId;
    //--------------------------
    @NotEmpty(message = "merchant id cannot be empty")
    @Size(min = 3, message = "merchant id should be 3 character or longer")
    private String merchantId;
    //--------------------------
    @NotNull(message = "stock cannot be empty")
    @Min(value = 10, message = "stock have to be more than 10 at the start")
    private int stock;





}
