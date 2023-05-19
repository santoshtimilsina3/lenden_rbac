package com.lenden.rbac.repository;

import com.lenden.rbac.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
