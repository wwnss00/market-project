package com.example.marketproject.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePostRequest {

    @Size(max = 100, message = "제목은 100자 이하여야합니다.")
    private String title;

    private String content;

    @Min(value = 0, message = "가격은 0원 이상이어야 합니다")
    private Integer price;

    @Size(max = 100)
    private String location;
}
