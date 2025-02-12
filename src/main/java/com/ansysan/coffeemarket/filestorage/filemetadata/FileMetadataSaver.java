package com.ansysan.coffeemarket.filestorage.filemetadata;

import com.ansysan.coffeemarket.filestorage.converter.FileMetadataDtoConverter;
import com.ansysan.coffeemarket.filestorage.dto.FileMetadataDto;
import com.ansysan.coffeemarket.filestorage.entity.FileMetadata;
import com.ansysan.coffeemarket.filestorage.repository.FileMetadataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileMetadataSaver {

    private final FileMetadataRepository fileMetadataRepository;
    private final FileMetadataDtoConverter fileMetadataDtoConverter;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public FileMetadataDto save(final FileMetadataDto fileMetadataDto) {
        FileMetadata fileMetadata = fileMetadataDtoConverter.toEntity(fileMetadataDto);
        FileMetadata savedFileMetadata = fileMetadataRepository.save(fileMetadata);
        return fileMetadataDtoConverter.toDto(savedFileMetadata);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void saveAll(final List<FileMetadataDto> fileMetadataDtos) {
        List<FileMetadata> fileMetadataList = fileMetadataDtoConverter.toEntityList(fileMetadataDtos);
        fileMetadataRepository.saveAll(fileMetadataList);
    }
}