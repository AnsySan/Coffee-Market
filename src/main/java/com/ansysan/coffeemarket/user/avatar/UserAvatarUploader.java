package com.ansysan.coffeemarket.user.avatar;

import com.ansysan.coffeemarket.filestorage.dto.FileMetadataDto;
import com.ansysan.coffeemarket.filestorage.file.FileUploader;
import com.ansysan.coffeemarket.filestorage.filemetadata.FileMetadataDeleter;
import com.ansysan.coffeemarket.filestorage.filemetadata.FileMetadataSaver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserAvatarUploader {

    @Value("${spring.aws.buckets.user-avatar}")
    private String bucketName;
    private static final String AVATAR_NAME_PREFIX = "user-avatar-";

    private final FileUploader fileUploader;
    private final FileMetadataSaver fileMetadataSaver;
    private final FileMetadataDeleter fileMetadataDeleter;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public FileMetadataDto uploadUserAvatar(final UUID userId, final MultipartFile file) {
        fileMetadataDeleter.deleteByRelatedObjectId(userId);
        String fileName = AVATAR_NAME_PREFIX + userId.toString();
        fileUploader.upload(file, bucketName, fileName);
        FileMetadataDto fileMetadataDto = new FileMetadataDto(userId, bucketName, fileName);
        return fileMetadataSaver.save(fileMetadataDto);
    }
}