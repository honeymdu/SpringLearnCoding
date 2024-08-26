package com.example.SpringLearnH2db.Services;

import java.util.List;

import com.example.SpringLearnH2db.Dto.PostDto;

public interface PostService {

    List<PostDto> getAllPosts();

    PostDto CreateNewPost(PostDto postDto);

    PostDto getPostById(Long postId);


}
