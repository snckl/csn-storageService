package com.csn.storageservice.service;

import com.csn.storageservice.dto.StorageDto;
import com.csn.storageservice.entity.Storage;
import com.csn.storageservice.exception.MaxImageSizeExceededException;
import com.csn.storageservice.exception.ResourceNotFoundException;
import com.csn.storageservice.repository.StorageRepository;
import com.csn.storageservice.utils.CompressionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageService {
    private final StorageRepository storageRepository;

    public void saveImage(MultipartFile image,Long id) throws IOException {
            byte[] content = Objects.requireNonNull(image.getBytes());
            byte[] compressedContent = CompressionUtils.compress(content);
            if(compressedContent.length > 15728640) {
                throw new MaxImageSizeExceededException(image.getOriginalFilename());
            }
            Storage storage = Storage.builder()
                    .postId(id)
                    .type(image.getContentType())
                    .fileName(image.getOriginalFilename())
                    .content(compressedContent).build();
            Storage createdImage = storageRepository.save(storage);
            log.info("Image created with id of {}",createdImage.getId());
    }

    public StorageDto fetchImage(Long id) throws IOException {
        Optional<Storage> storage = storageRepository.findByPostId(id);
        if(storage.isPresent()){
            byte[] content = CompressionUtils.decompress(storage.get().getContent());
            return StorageDto.builder()
                    .postId(id)
                    .filename(storage.get().getFileName())
                    .type(storage.get().getType())
                    .content(content).build();
        }
        throw new ResourceNotFoundException("Image","id",id.toString());
    }

    public void deleteImage(Long id){
        Optional<Storage> storage = storageRepository.findByPostId(id);
        if(storage.isPresent()){
            storageRepository.deleteByPostId(id);
            log.info("Image deleted with id of {}",storage.get().getId());
        } else {
            throw new ResourceNotFoundException("Image","id",id.toString());
        }
    }
}
