package com.example.marketproject.service;


import com.example.marketproject.domain.entity.User;
import com.example.marketproject.exception.UserNotFoundException;
import com.example.marketproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;

    @Transactional
    public void updateProfileImage(Long userId, MultipartFile image) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        String storedFilename = fileStorageService.storeFile(image);
        user.updateProfileImage("/uploads/images/" + storedFilename);
        // @Transactional + dirty checking → save() 불필요
    }
}
