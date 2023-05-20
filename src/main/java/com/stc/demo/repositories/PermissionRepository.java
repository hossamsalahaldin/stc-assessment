package com.stc.demo.repositories;

import com.stc.demo.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Integer> {
    Permission findByPermissionLevel(String permissionLevel);
}
