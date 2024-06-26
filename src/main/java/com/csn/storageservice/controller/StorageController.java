package com.csn.storageservice.controller;

import com.csn.storageservice.dto.ResponseDto;
import com.csn.storageservice.dto.StorageDto;
import com.csn.storageservice.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(
        name = "CRUD API for storage service of CSN",
        description = "Create fetch delete for image details"
)
@RestController
@RequestMapping(value = "/api/v1",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Slf4j
public class StorageController {
    private final StorageService storageService;

    @Operation(summary = "Create image REST API",
            description = "Creates new image with input of image,type,fileName"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/p/{id}")
    public ResponseEntity<ResponseDto> saveImage(@RequestParam("content")MultipartFile image,@PathVariable("id") Long id) throws IOException {

        storageService.saveImage(image,id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201","Image saved successfully"));
    }

    @Operation(summary = "Fetch image details REST API",
            description = "Fetches image details"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/{id}")
    public ResponseEntity<StorageDto> fetchImage(@PathVariable("id") Long id,@RequestHeader("csn-correlation-id") String correlationId) throws IOException {
        log.debug("csn-correlation-id found: {}",correlationId);
        StorageDto storageDto = storageService.fetchImage(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(storageDto);
    }

    @Operation(summary = "Delete image REST API",
            description = "Delete image with input of id"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOT FOUND"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteImage(@PathVariable("id") Long id){
        storageService.deleteImage(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200","Image deleted successfully"));
    }
}
