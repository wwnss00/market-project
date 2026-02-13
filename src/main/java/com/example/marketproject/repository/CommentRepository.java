package com.example.marketproject.repository;

import com.example.marketproject.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 게시글별 댓글 조회 (User 함께 조회 - N+1 방지)
    @Query("SELECT c FROM Comment c " +
            "JOIN FETCH c.user " +
            "WHERE c.post.id = :postId " +
            "AND c.deletedAt IS NULL " +
            "ORDER BY c.createdAt ASC")
    List<Comment> findByPostIdWithUser(@Param("postId") Long postId);
}

