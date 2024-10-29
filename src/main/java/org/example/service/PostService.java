package org.example.service;

import jakarta.transaction.Transactional;
import org.example.dto.PostBoardDTO;
import org.example.dto.PostDTO;
import org.example.entitiy.Post;
import org.example.entitiy.User;
import org.example.repository.PostRepository;
import org.example.repository.UserRepository;
import org.example.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

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

    public PostDTO getPostByPostId(String postId) throws Exception {
        Post post = postRepository.findByPostId(postId);
        PostDTO postDTO = new PostDTO();
        if (post != null) {
            postDTO.setPostId(post.getPostId());
            postDTO.setTitle(post.getTitle());
            postDTO.setPostAt(post.getPostAt());
            postDTO.setUserId(post.getUser().getUserId());
            postDTO.setInterests(post.getInterests());
            postDTO.setApplicants(post.getInterests());
            postDTO.setUserName(post.getUser().getUserName());
            postDTO.setViews(post.getViews());
            postDTO.setContent(post.getContent());
        } else {
            throw new Exception("해당 Id 의 포스트가 존재하지 않습니다.");
        }

        return postDTO;
    }

    @Transactional
    public Post createPost(PostDTO postDTO) {
        Post post = new Post();
        User user = userRepository.findById(postDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setUser(user);
        post.setPostAt(postDTO.getPostAt());

        return postRepository.save(post);
    }

    @Transactional
    public Post updatePost(String postId, PostDTO postDTO) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setTitle(postDTO.getTitle());
            // TODO 작성 시간 말고 수정 시간을 추가해서 그걸 바꿔야 함
            post.setContent(postDTO.getContent());
            return postRepository.save(post);
        } else {
            throw new RuntimeException("해당 Post가 없습니다.");
        }
    }

    @Transactional
    public void deletePost(String postId) {
        postRepository.deleteById(postId);
    }

    private int getPostCount() {
        return (int) postRepository.count();
    }

    private List<Post> getPostList(int pageSize, int page) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        return postRepository.findAllByOrderByPostAtDesc(pageable);
    }
}
