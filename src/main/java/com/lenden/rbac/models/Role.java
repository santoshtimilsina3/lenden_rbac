package com.lenden.rbac.models;

import com.lenden.rbac.enums.RoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.lenden.rbac.enums.Permission;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Role {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id ;
        @Enumerated(EnumType.STRING)
        RoleName roleName ;


        @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
        List<Authorities> authorities;

        public Role (RoleName roleName) {this.roleName = roleName;}
        public String getRoleName() {
                return roleName.toString();
        }

}
