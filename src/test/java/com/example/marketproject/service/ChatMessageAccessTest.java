package com.example.marketproject.service;

import com.example.marketproject.domain.entity.ChatRoom;
import com.example.marketproject.domain.entity.Post;
import com.example.marketproject.domain.entity.Role;
import com.example.marketproject.domain.entity.User;
import com.example.marketproject.exception.UnauthorizedException;
import com.example.marketproject.repository.ChatRoomRepository;
import com.example.marketproject.repository.PostRepository;
import com.example.marketproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@Transactional
@ActiveProfiles("local")
@TestPropertySource(properties = "spring.sql.init.mode=never")
public class ChatMessageAccessTest {
    @Autowired
    private ChatMessageService chatMessageService;
    @Autowired private ChatRoomRepository chatRoomRepository;
    @Autowired private PostRepository postRepository;
    @Autowired private UserRepository userRepository;

    private User seller;
    private User buyer;
    private User other;
    private ChatRoom room;

    @BeforeEach
    void setUp() {
        seller = saveUser("chatseller");
        buyer = saveUser("chatbuyer");
        other = saveUser("chatother");
        Post post = postRepository.save(Post.builder()
                .user(seller).title("제목").content("내용").price(1000).location("인천").build());
        room = chatRoomRepository.save(ChatRoom.create(post, buyer, seller));
    }

    private User saveUser(String id) {
        return userRepository.save(User.builder()
                .name("테스트").email(id + "@test.com").loginId(id)
                .password("pw").nickname(id).phone("01012345678")
                .role(Role.USER).build());
    }

    @Test
    @DisplayName("참여자가 아니면 채팅 메시지를 조회할 수 없다")
    void 비참여자_채팅_조회_불가() {
        assertThatThrownBy(() -> chatMessageService.getMessages(room.getId(), other.getId()))
                .isInstanceOf(UnauthorizedException.class);
    }

    @Test
    @DisplayName("참여자는 채팅 메시지를 조회할 수 있다")
    void 참여자_채팅_조회_가능() {
        assertThatCode(() -> chatMessageService.getMessages(room.getId(), buyer.getId()))
                .doesNotThrowAnyException();
    }
}
