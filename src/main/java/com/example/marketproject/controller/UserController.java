package com.example.marketproject.controller;

import com.example.marketproject.security.CustomUserDetails;
import com.example.marketproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PatchMapping("/me/profile-image")
    public ResponseEntity<Void> updateProfileImage(
            @RequestParam MultipartFile image,
            @AuthenticationPrincipal CustomUserDetails userDetails) throws IOException {

        userService.updateProfileImage(userDetails.getUserId(), image);
        return ResponseEntity.noContent().build();
    }
}
