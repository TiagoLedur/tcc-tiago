package com.example.stock_control_api.dto.login;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String senha;
}
