package com.csn.storageservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name="Post",
        description =  "Storage schema which holds the image/video information of the user"
)
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
