package org.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDTO {
    private String postId;

    // 제목
    private String title;

    // 작성 시간
    @JsonFormat(shape = JsonFormat.Shape.SCALAR, pattern="yyyy-MM-dd HH:mm:ss")
    private Date postAt;

    // 작성자 ID
    private String userId;

    // 작성자 이름
    private String userName;

    // 조회 수
    private int views;

    // 관심 수
    private int interests;

    private String content;

}
