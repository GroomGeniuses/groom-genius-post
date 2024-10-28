package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entitiy.Post;

import java.util.List;

@NoArgsConstructor()
@AllArgsConstructor
@Getter
@Setter
public class PostBoardDTO {
    public List<Post> postLists;

    public int totalPage;

    public int pageSize = 20;

    public int total;

    public int currentPage = 0;
}
