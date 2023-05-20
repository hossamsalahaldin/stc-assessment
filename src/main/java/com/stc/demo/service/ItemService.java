package com.stc.demo.service;

import com.stc.demo.entities.File;
import com.stc.demo.entities.Item;
import com.stc.demo.entities.Permission;
import com.stc.demo.entities.PermissionGroup;
import com.stc.demo.repositories.FileRepository;
import com.stc.demo.repositories.ItemRepository;
import com.stc.demo.repositories.PermissionGroupRepository;
import com.stc.demo.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PermissionGroupRepository permissionGroupRepository;
    @Autowired
    private FileRepository fileRepository;

    public void addSpace(){
        PermissionGroup permissionGroup = new PermissionGroup();
        permissionGroup.setGroupName("SpaceAdminGroup");
        permissionGroup = permissionGroupRepository.save(permissionGroup);
        Item item = saveItem("stc-assessments","Space",permissionGroup.getId(),null);
        saveFile(null,item.getId());
        savePermission("admin_user_edite@test.com","EDITE",permissionGroup.getId());
        savePermission("admin_user_view@test.com","VIEW",permissionGroup.getId());
    }

    public void addFolder(){
        Permission permission = permissionRepository.findByPermissionLevel("EDITE");
        Item parentItem = itemRepository.findByName("stc-assessments");
        if(permission != null){
            Item item =  saveItem("backend","Folder",permission.getPermissionGroupId(),parentItem.getId());
            saveFile(null,item.getId());
        }
    }

    public void addFile(byte[] binary){
        Permission permission = permissionRepository.findByPermissionLevel("EDITE");
        Item parentItem = itemRepository.findByName("backend");
        if(permission != null){
            Item item = saveItem("assessment.pdf","File",permission.getPermissionGroupId(),parentItem.getId());
            saveFile(binary,item.getId());
        }
    }

    public Item getItemMetadata(String fileName){
       return itemRepository.findByName(fileName);
    }

    public File getFileByName(String fileName){
        Item item = itemRepository.findByName(fileName);
        return fileRepository.findByItemId(item.getId());
    }

    private Item saveItem(String name,String type,int permissionGroupId,Integer itemParentId){
        Item item = new Item();
        item.setName(name);
        item.setType(type);
        item.setPermissionGroupId(permissionGroupId);
        item.setItemParentId(itemParentId);
        return itemRepository.save(item);
    }

    private File saveFile(byte[] binary, int itemId){
        File file = new File();
        file.setItemId(itemId);
        file.setBinary(binary);
        return fileRepository.save(file);
    }

    private Permission savePermission(String userEmail, String permissionLevel, int permissionGroupId){
        Permission permission = new Permission();
        permission.setPermissionGroupId(permissionGroupId);
        permission.setPermissionLevel(permissionLevel);
        permission.setUserEmail(userEmail);
        return permissionRepository.save(permission);
    }
}
