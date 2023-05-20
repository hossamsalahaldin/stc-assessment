package com.stc.demo.repositories;

import com.stc.demo.entities.PermissionGroup;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

@Entity(name = "Permission_groups")
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup,Integer> {
}
