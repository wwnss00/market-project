package com.example.marketproject.repository;

import com.example.marketproject.domain.entity.Wishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    // 중복 체크
    boolean existsByUserIdAndPostId(Long userId, Long postId);

    // 특정 유저의 특정 게시글 찜 조회
    Optional<Wishlist> findByUserIdAndPostId(Long userId, Long postId);

    // 내 찜 목록 조회 (Post, User 함께 - N+1 방지)
    @Query("SELECT w FROM Wishlist w " +
            "JOIN FETCH w.post p " +
            "JOIN FETCH p.user " +
            "WHERE w.user.id = :userId " +
            "AND p.deletedAt IS NULL " +
            "ORDER BY w.createdAt DESC")
    Page<Wishlist> findByUserIdWithPost(@Param("userId") Long userId, Pageable pageable);

}
