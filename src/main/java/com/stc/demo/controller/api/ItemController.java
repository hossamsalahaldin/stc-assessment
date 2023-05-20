package com.stc.demo.controller.api;

import com.stc.demo.dto.ItemDto;
import com.stc.demo.entities.File;
import com.stc.demo.mapper.ItemMapper;
import com.stc.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v1/item/")
public class ItemController implements ItemApi{
    @Autowired
    private ItemService itemService;

    private ItemMapper itemMapper;
    @Override
    @PostMapping("/add-space")
    public ResponseEntity<HttpStatus> addSpace() {
        itemService.addSpace();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PostMapping("/add-folder")
    public ResponseEntity<HttpStatus> addFolder() {
        itemService.addFolder();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PostMapping("/add-file")
    public ResponseEntity<HttpStatus> addFile() {
        itemService.addFolder();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @GetMapping("/get-file-metadata/{file-name}")
    public ItemDto getFileMetadata(@RequestParam("file-name") String fileName) {
       return itemMapper.fromItemToItemDto(itemService.getItemMetadata(fileName));
    }

    @Override
    public ResponseEntity<ByteArrayResource> downloadFile(String fileName) {
        File file = itemService.getFileByName(fileName);
        ByteArrayResource resource = new ByteArrayResource(file.getBinary());
        return ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}

