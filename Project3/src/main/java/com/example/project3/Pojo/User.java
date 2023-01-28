package com.example.project3.Pojo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = "id cannot be empty")
    @Size(min = 3, message = "id should be 3 characters or longer")
    private String id;

    @NotEmpty(message = "username cannot be empty")
    @Size(min = 5, message = "username should be 5 characters or longer")
    private String username;

    @NotEmpty(message = "password cannot be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$", message = "password have to be at least 6 characters long, including digit and character")
    private String password;

    @NotEmpty(message = "email cannot be empty")
    @Email(message = "email is not a valid structure.")
    private String email;

    @NotEmpty(message = "id cannot be empty")
    @Size(min = 3, message = "id has to be at least 3 Characters")
    @Pattern(regexp = "^(Admin||Customer)$", message = "Role should be admin or customer")
    private String role;

    @NotNull(message = "balance cannot be empty")
    @PositiveOrZero(message = "balance should be zero or greater")
    private int balance;

}
