package com.csn.storageservice.service;

import com.csn.storageservice.dto.StorageDto;
import com.csn.storageservice.entity.Storage;
import com.csn.storageservice.exception.ResourceNotFoundException;
import com.csn.storageservice.repository.StorageRepository;
import com.csn.storageservice.utils.CompressionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final StorageRepository storageRepository;

    public void saveImage(MultipartFile image,String type,String fileName) throws IOException {
            byte[] content = Objects.requireNonNull(image.getOriginalFilename()).getBytes();
            byte[] compressedContent = CompressionUtils.compress(content);

            Storage storage = Storage.builder()
                    .type(type)
                    .fileName(fileName)
                    .content(compressedContent).build();

            storageRepository.save(storage);
    }

    public StorageDto fetchImage(Long id) throws IOException {
        Optional<Storage> storage = storageRepository.findById(id);
        if(storage.isPresent()){
            byte[] content = CompressionUtils.decompress(storage.get().getContent());

            return StorageDto.builder()
                    .filename(storage.get().getFileName())
                    .type(storage.get().getType())
                    .content(content).build();
        }
        throw new ResourceNotFoundException("Image","id",id.toString());
    }

    public void deleteImage(Long id){
        Optional<Storage> storage = storageRepository.findById(id);
        if(storage.isPresent()){
            storageRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Image","id",id.toString());
        }
    }
}
