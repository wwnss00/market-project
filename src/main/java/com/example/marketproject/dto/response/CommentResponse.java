package com.example.marketproject.dto.response;

import com.example.marketproject.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class CommentResponse {

    private Long id;
    private String content;
    private WriterInfo writer;
    private boolean deleted;
    private List<CommentResponse> children;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Getter
    @Builder
    public static class WriterInfo {
        private Long id;
        private String nickname;
    }

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.isDeleted() ? "삭제된 댓글입니다." : comment.getContent())
                .writer(comment.isDeleted() ? null : WriterInfo.builder()
                        .id(comment.getUser().getId())
                        .nickname(comment.getUser().getNickname())
                        .build())
                .deleted(comment.isDeleted())
                .children(comment.getChildren().stream()
                        .map(CommentResponse::from)
                        .collect(Collectors.toList()))
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }

}
