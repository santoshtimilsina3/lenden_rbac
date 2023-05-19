package com.lenden.rbac.repository;

import com.lenden.rbac.models.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities,Integer> {
}
