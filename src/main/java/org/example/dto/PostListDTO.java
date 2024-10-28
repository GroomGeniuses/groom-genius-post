package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class PostListDTO {
    private String postId;

    // 제목
    private String title;

    // 작성 시간
    private Date postAt;

    // 작성자 ID
    private String userId;

    // 조회 수
    private int views;

    // 관심 수
    private int interests;

}
