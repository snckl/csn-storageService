package com.csn.storageservice.controller;

import com.csn.storageservice.dto.ResponseDto;
import com.csn.storageservice.dto.StorageDto;
import com.csn.storageservice.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/storage",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class StorageController {
    private final StorageService storageService;

    @PostMapping
    public ResponseEntity<ResponseDto> saveImage(@RequestParam("content")MultipartFile image,
                                                   @RequestParam("type") String type,
                                                   @RequestParam("fileName") String fileName) throws IOException {

        storageService.saveImage(image,type,fileName);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201","Image saved successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StorageDto> fetchImage(@PathVariable("id") Long id) throws IOException {
        StorageDto storageDto = storageService.fetchImage(id);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(storageDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteImage(@PathVariable("id") Long id){
        storageService.deleteImage(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200","Image deleted successfully"));
    }
}
