package com.example.marketproject.dto.response;

import com.example.marketproject.domain.entity.Post;
import com.example.marketproject.domain.entity.PostStatus;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@Builder
public class PostResponse {

    // 게시글 기본 정보
    private Long id;
    private String title;
    private String content;
    private Integer price;
    private String location;

    //게시글 상태 정보
    private PostStatus status;
    private Integer viewCount;

    private WriterInfo writer;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Getter
    @Builder
    public static class WriterInfo {
        private Long id;
        private String nickname;
    }

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .price(post.getPrice())
                .location(post.getLocation())
                .status(post.getStatus())
                .viewCount(post.getViewCount())
                .writer(WriterInfo.builder()
                        .id(post.getUser().getId())
                        .nickname(post.getUser().getNickname())
                        .build())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();

    }
}
