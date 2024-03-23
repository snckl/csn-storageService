package com.csn.storageservice.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StorageDto {
    @NotBlank(message = "File name cannot be blank")
    private String filename;
    @NotBlank(message = "File type cannot be blank")
    private String type;
    @Lob
    @NotBlank(message = "Content must be provided")
    private byte[] content;
}
