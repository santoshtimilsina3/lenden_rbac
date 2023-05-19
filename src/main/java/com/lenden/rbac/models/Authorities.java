package com.lenden.rbac.models;


import com.lenden.rbac.enums.Permission;
import com.lenden.rbac.enums.RoleName;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Enumerated(EnumType.STRING)
    Permission permission ;

}
