package com.ansysan.coffeemarket.filestorage.filemetadata;

import com.ansysan.coffeemarket.filestorage.converter.FileMetadataDtoConverter;
import com.ansysan.coffeemarket.filestorage.dto.FileMetadataDto;
import com.ansysan.coffeemarket.filestorage.repository.FileMetadataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileMetadataProvider {

    private final FileMetadataRepository fileMetadataRepository;
    private final FileMetadataDtoConverter fileMetadataDtoConverter;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
    public Optional<FileMetadataDto> getFileMetadataDto(final UUID relatedObjectId) {
        return fileMetadataRepository.findAvatarInfoByRelatedObjectId(relatedObjectId)
                .map(fileMetadataDtoConverter::toDto);
    }
}