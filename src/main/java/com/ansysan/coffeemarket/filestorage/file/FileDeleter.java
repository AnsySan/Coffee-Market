package com.ansysan.coffeemarket.filestorage.file;

import com.ansysan.coffeemarket.filestorage.aws.AwsObjectDeleter;
import com.ansysan.coffeemarket.filestorage.filemetadata.FileMetadataDeleter;
import com.ansysan.coffeemarket.filestorage.filemetadata.FileMetadataProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileDeleter {

    private final AwsObjectDeleter awsObjectDeleter;
    private final FileMetadataProvider fileMetadataProvider;
    private final FileMetadataDeleter fileMetadataDeleter;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void delete(final UUID relatedObjectId) {
        fileMetadataProvider.getFileMetadataDto(relatedObjectId)
                .ifPresent(fileMetadata -> {
                    awsObjectDeleter.deleteFile(fileMetadata);
                    fileMetadataDeleter.deleteByRelatedObjectId(relatedObjectId);
                    log.info("File was deleted from the file storage and file metadata was deleted from the database as well");
                });
    }
}