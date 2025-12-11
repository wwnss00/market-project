package com.example.marketproject.domain.entity;

import com.example.marketproject.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profile_images")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProfileImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(length = 255, nullable = false)
    private String url;
}
