package com.example.marketproject.dto.response;

import com.example.marketproject.domain.entity.PostStatus;
import com.example.marketproject.domain.entity.Wishlist;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class WishlistResponse {

    private Long postId;
    private String title;
    private Integer price;
    private String location;
    private PostStatus status;
    private Integer viewCount;
    private String writerNickname;
    private LocalDateTime likedAt;  // 찜한 시간

    public static WishlistResponse from(Wishlist wishlist) {
        return WishlistResponse.builder()
                .postId(wishlist.getPost().getId())
                .title(wishlist.getPost().getTitle())
                .price(wishlist.getPost().getPrice())
                .location(wishlist.getPost().getLocation())
                .status(wishlist.getPost().getStatus())
                .viewCount(wishlist.getPost().getViewCount())
                .writerNickname(wishlist.getPost().getUser().getNickname())
                .likedAt(wishlist.getCreatedAt())
                .build();
    }
}
