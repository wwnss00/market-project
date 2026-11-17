package com.example.marketproject.service;

import com.example.marketproject.domain.entity.Role;
import com.example.marketproject.domain.entity.User;
import com.example.marketproject.dto.request.CreatePostRequest;
import com.example.marketproject.repository.PostRepository;
import com.example.marketproject.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("local")
@TestPropertySource(properties = "spring.sql.init.mode=never")
public class PostCreateRollbackTest {

    @Autowired
    private PostService postService;
    @Autowired private PostRepository postRepository;
    @Autowired private UserRepository userRepository;

    @MockitoBean
    private FileStorageService fileStorageService;

    private User user;

    @BeforeEach
    void setUp() {
        user = userRepository.save(User.builder()
                .name("홍길동").email("rollback@test.com").loginId("rollbackuser")
                .password("pw").nickname("롤백테스트").phone("01012345678")
                .role(Role.USER).build());
    }

    @AfterEach
    void tearDown() {
        postRepository.deleteAll();
        userRepository.delete(user);
    }

    @Test
    @DisplayName("이미지 저장이 실패하면 게시글도 저장되지 않는다")
    void 이미지_저장_실패시_게시글_롤백() throws Exception {
        // given: 파일 저장이 항상 실패하도록 설정
        given(fileStorageService.storeFile(any())).willThrow(new IOException("디스크 오류"));

        CreatePostRequest request = CreatePostRequest.builder()
                .title("제목").content("내용").price(1000).location("인천").build();
        MockMultipartFile image = new MockMultipartFile(
                "images", "a.jpg", "image/jpeg", "data".getBytes());
        long before = postRepository.count();

        // when: 게시글 작성 시도 → 예외 발생
        assertThatThrownBy(() -> postService.createPost(request, List.of(image), user.getId()))
                .isInstanceOf(IOException.class);

        // then: 게시글 수가 그대로여야 한다
        assertThat(postRepository.count()).isEqualTo(before);
    }
}

