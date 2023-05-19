package com.lenden.rbac.dtos;

import java.util.List;

import javax.management.relation.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    private String jwt;
    private Integer id;
    private String email;

}
