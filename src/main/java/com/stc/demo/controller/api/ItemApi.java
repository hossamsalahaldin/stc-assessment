package com.stc.demo.controller.api;

import com.stc.demo.dto.ItemDto;
import com.stc.demo.entities.Item;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ItemApi {
    public ResponseEntity<HttpStatus> addSpace();

    public ResponseEntity<HttpStatus> addFolder();

    public ResponseEntity<HttpStatus> addFile();

    public ItemDto getFileMetadata(String fileName);

    public ResponseEntity<ByteArrayResource> downloadFile(String fileName);
}
