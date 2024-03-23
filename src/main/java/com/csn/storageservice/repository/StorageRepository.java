package com.csn.storageservice.repository;

import com.csn.storageservice.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage,Long> {
}
