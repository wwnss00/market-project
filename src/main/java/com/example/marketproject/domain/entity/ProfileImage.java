package com.example.marketproject.domain.entity;

import com.example.marketproject.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profile_images")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private String originalFilename;  // 원본

    @Column(nullable = false)
    private String storedFilename;    // 저장명

    @Column(nullable = false)
    private String filePath;          // 경로

    private Long fileSize;

    @Builder
    public ProfileImage(User user, String originalFilename,
                        String storedFilename, String filePath, Long fileSize) {
        this.user = user;
        this.originalFilename = originalFilename;
        this.storedFilename = storedFilename;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public String getImageUrl() {
        return filePath + storedFilename;
    }
}
