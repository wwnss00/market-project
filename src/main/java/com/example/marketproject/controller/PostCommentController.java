package com.example.marketproject.controller;

import com.example.marketproject.dto.request.CreateCommentRequest;
import com.example.marketproject.dto.response.CommentResponse;
import com.example.marketproject.security.CustomUserDetails;
import com.example.marketproject.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
public class PostCommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long postId,
            @Valid @RequestBody CreateCommentRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        CommentResponse response = commentService.createComment(
                request,
                userDetails.getUserId(),
                postId
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponse>> getComments(
            @PathVariable Long postId) {

        List<CommentResponse> comments = commentService.getComments(postId);
        return ResponseEntity.ok(comments);
    }
}
