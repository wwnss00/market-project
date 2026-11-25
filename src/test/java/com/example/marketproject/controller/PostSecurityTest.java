package com.example.marketproject.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("local")
@TestPropertySource(properties = "spring.sql.init.mode=never")
public class PostSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("비로그인 게시글 삭제 요청은 401")
    void 비로그인_게시글_삭제_401() throws Exception {
        mockMvc.perform(delete("/api/posts/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("비로그인 게시글 목록 조회는 200")
    void 비로그인_게시글_목록_조회_200() throws Exception {
        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk());
    }
}
