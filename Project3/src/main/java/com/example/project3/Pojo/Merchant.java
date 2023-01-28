package com.example.project3.Pojo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Merchant {

    @NotNull(message = "ID can not be empty!")
    @Size(min = 3,max = 10 , message = "ID should be 3 character or longer ")
    private String id;
    //---------------------------------
    @NotNull(message = "Name can not be empty!")
    @Size(min = 3,max = 10 , message = "Name should be 3 character or longer ")
    private String name;
}
