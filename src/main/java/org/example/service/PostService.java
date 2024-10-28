package org.example.service;

import org.example.dto.PostBoardDTO;
import org.example.dto.PostDTO;
import org.example.entitiy.Post;
import org.example.repository.PostRepository;
import org.example.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    private PageUtil pageUtil;

    //TODO: content 제거
    public PostBoardDTO getPostBoard(int page, int pageSize) {
        PostBoardDTO postBoardDTO = new PostBoardDTO();

        postBoardDTO.currentPage = page;

        postBoardDTO.pageSize = pageSize;

        postBoardDTO.total = getPostCount();

        postBoardDTO.totalPage = pageUtil.getTotalPage(postBoardDTO.total, postBoardDTO.pageSize);

        postBoardDTO.postLists = getPostList(postBoardDTO.pageSize, postBoardDTO.currentPage);

        return postBoardDTO;
    }

    private int getPostCount() {
        return (int) postRepository.count();
    }

    private List<Post> getPostList(int pageSize, int page) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        return postRepository.findAllByOrderByPostAtDesc(pageable);
    }
}
