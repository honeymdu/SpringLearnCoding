package com.example.SpringLearnH2db.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.SpringLearnH2db.Dto.PostDto;
import com.example.SpringLearnH2db.Entitys.PostEntity;
import com.example.SpringLearnH2db.Exceptions.ResourceNotFoundException;
import com.example.SpringLearnH2db.Repositories.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPosts() {

        return postRepository
                .findAll()
                .stream()
                .map(PostEntity -> modelMapper.map(PostEntity, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto CreateNewPost(PostDto Inputpost) {

        PostEntity postEntity = modelMapper.map(Inputpost, PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity), PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        return postRepository.findById(postId).map(PostEntity -> modelMapper.map(PostEntity, PostDto.class)).orElseThrow(()->new ResourceNotFoundException("Post Not Found"));
    }

    @Override
    public PostDto UpdatePostById(PostDto postDto, Long postId) {
       PostEntity postEntity = postRepository.findById(postId).get();
       modelMapper.map(postDto, postEntity);
       postEntity.setId(postId);
        postRepository.save(postEntity);
        return modelMapper.map(postEntity, PostDto.class);
    }

}
