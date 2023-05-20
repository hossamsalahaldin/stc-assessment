package com.stc.demo.entities;

import jakarta.persistence.*;

@Entity(name = "Item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column
    private String type;
    @Column
    private String name;

    @Column(name = "group_id")
    private int permissionGroupId;

    @Column(name = "parent_id")
    private Integer itemParentId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPermissionGroupId() {
        return permissionGroupId;
    }

    public void setPermissionGroupId(int permissionGroupId) {
        this.permissionGroupId = permissionGroupId;
    }

    public Integer getItemParentId() {
        return itemParentId;
    }

    public void setItemParentId(Integer itemParentId) {
        this.itemParentId = itemParentId;
    }
}
