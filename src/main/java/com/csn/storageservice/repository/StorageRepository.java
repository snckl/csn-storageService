package com.csn.storageservice.repository;

import com.csn.storageservice.entity.Storage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<Storage,Long> {
    Optional<Storage> findByPostId(Long id);
    @Transactional
    void deleteByPostId(Long id);
}
