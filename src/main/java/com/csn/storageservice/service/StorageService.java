package com.csn.storageservice.service;

import com.csn.storageservice.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final StorageRepository storageRepository;
}
