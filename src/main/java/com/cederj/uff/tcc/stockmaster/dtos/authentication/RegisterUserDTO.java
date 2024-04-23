package com.cederj.uff.tcc.stockmaster.dtos.authentication;

public record RegisterUserDTO(
        String name,
        String userName,
        String email,
        String password
) {
}
