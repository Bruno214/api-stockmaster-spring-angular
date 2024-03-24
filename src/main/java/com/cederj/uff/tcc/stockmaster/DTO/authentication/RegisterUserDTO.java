package com.cederj.uff.tcc.stockmaster.DTO.authentication;

public record RegisterUserDTO(
        String name,
        String userName,
        String email,
        String password
) {
}
